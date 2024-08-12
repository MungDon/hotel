package com.example.demo.mapper;

import com.example.demo.dto.request.banner.ReqBannerAdd;
import com.example.demo.dto.request.banner.ReqBannerImg;
import com.example.demo.dto.response.banner.ResBannerList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BannerMapper {

    int bannerAdd(ReqBannerAdd req);

    void bannerImgSave(ReqBannerImg req);

    List<ResBannerList> findAllBanner();
}
