package com.example.demo.service;

import com.example.demo.Exception.ErrorCode;
import com.example.demo.dto.request.banner.ReqBannerAdd;
import com.example.demo.dto.request.banner.ReqBannerDelete;
import com.example.demo.dto.request.banner.ReqBannerImg;
import com.example.demo.dto.request.banner.ReqBannerUpdate;
import com.example.demo.dto.response.ResponseDTO;
import com.example.demo.dto.response.banner.ResBannerList;
import com.example.demo.mapper.BannerMapper;
import com.example.demo.util.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class BannerService {

    private final BannerMapper bannerMapper;

    @Value("${file.Upimg}")
    private String path;
    private final int TARGET_WIDTH = 1024;
    private final int TARGET_HEIGHT = 450;

    private boolean isResizing(BufferedImage bufferImg){
       return TARGET_WIDTH != bufferImg.getWidth() || TARGET_HEIGHT != bufferImg.getHeight();

    }

    @Transactional
    public void bannerImgSave(MultipartFile bannerImg, Long banner_sid) throws IOException {
        String originalName = bannerImg.getOriginalFilename();
        String uuid = String.valueOf(UUID.randomUUID());
        String extension = originalName.substring(originalName.lastIndexOf("."));
        String savaName = uuid + extension;
        File converFile = new File(path, savaName);
        bannerImg.transferTo(converFile);
        BufferedImage bufferImg = ImageIO.read(converFile);
        // 리사이징이 필요한지 확인
        if (isResizing(bufferImg)) {
            log.info("리사이징");
            Thumbnails.of(converFile)
                    .size(TARGET_WIDTH, TARGET_HEIGHT)
                    .keepAspectRatio(false)
                    .toFile(converFile);
        }
        ReqBannerImg reqBannerImg = ReqBannerImg.builder()
                .banner_sid(banner_sid)
                .original_name(originalName)
                .img_name(savaName)
                .extension(extension)
                .build();
        bannerMapper.bannerImgSave(reqBannerImg);
    }


    private ReqBannerAdd createReqBannerAdd(Map<String, String> param,int index){
        String banner_name = param.get("bannerAdd["+index+"].banner_name");
        String banner_url = param.get("bannerAdd["+index+"].banner_url");
        ReqBannerAdd roomTypeAdd = ReqBannerAdd.builder()
                .banner_name(banner_name)
                .banner_url(banner_url)
                .build();
        return roomTypeAdd;
    }
    @Transactional
    public ResponseDTO bannerAdd(Map<String, String> param, Map<String, MultipartFile> files) throws IOException {
        int index = 0;
        int result = 0;
        while(param.containsKey("bannerAdd["+index+"].banner_name")){
            ReqBannerAdd bannerAdd = createReqBannerAdd(param,index);
            result += bannerMapper.bannerAdd(bannerAdd);
            MultipartFile bannerImg = files.get("bannerAdd["+index+"].banner_img");
            bannerImgSave(bannerImg,bannerAdd.getBanner_sid());
            index ++;
        }
        return CommonUtils.successResponse(result, "배너 등록 성공", ErrorCode.INSERT_OPERATION_FAILED);
    }

    @Transactional
    public List<ResBannerList> findAllBanner(){
        return bannerMapper.findAllBanner();
    }

    @Transactional
    public ResponseDTO bannerUpdate(ReqBannerUpdate req) throws IOException {
        int result = bannerMapper.bannerUpdate(req);
        if(!CommonUtils.isEmpty(req.getBanner_img())){
            CommonUtils.deleteImg(path,req.getCurrent_img());
            bannerMapper.deleteImg(req.getBanner_sid());
            bannerImgSave(req.getBanner_img(), req.getBanner_sid());
        }
        return CommonUtils.successResponse(result, "배너 수정 성공",ErrorCode.UPDATE_OPERATION_FAILED);
    }

    @Transactional
    public ResponseDTO bannerDelete(ReqBannerDelete req){
        int result = bannerMapper.bannerDelete(req.getBanner_sid());

        if(result > 0){
            CommonUtils.deleteImg(path,req.getCurrent_img());
        }
        return CommonUtils.successResponse(result,"배너 삭제 성공",ErrorCode.DELETE_OPERATION_FAILED);
    }
}
