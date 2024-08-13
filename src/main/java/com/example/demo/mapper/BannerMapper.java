package com.example.demo.mapper;

import com.example.demo.dto.request.banner.ReqBannerAdd;
import com.example.demo.dto.request.banner.ReqBannerImg;
import com.example.demo.dto.request.banner.ReqBannerUpdate;
import com.example.demo.dto.response.banner.ResBannerList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BannerMapper {

    int bannerAdd(ReqBannerAdd req);

    void bannerImgSave(ReqBannerImg req);

    List<ResBannerList> findAllBanner();

    int deleteImg(@Param(value = "banner_sid")Long banner_sid);

    int bannerUpdate(ReqBannerUpdate req);

    int bannerDelete(@Param(value = "banner_sid")Long banner_sid);
}
