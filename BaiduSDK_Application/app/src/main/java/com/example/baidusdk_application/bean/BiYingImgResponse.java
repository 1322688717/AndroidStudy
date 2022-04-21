package com.example.baidusdk_application.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BiYingImgResponse {

    @SerializedName("images")
    private List<ImagesDTO> images;
    @SerializedName("tooltips")
    private TooltipsDTO tooltips;

    @NoArgsConstructor
    @Data
    public static class TooltipsDTO {
        @SerializedName("loading")
        private String loading;
        @SerializedName("previous")
        private String previous;
        @SerializedName("next")
        private String next;
        @SerializedName("walle")
        private String walle;
        @SerializedName("walls")
        private String walls;
    }

    @NoArgsConstructor
    @Data
    public static class ImagesDTO {
        @SerializedName("startdate")
        private String startdate;
        @SerializedName("fullstartdate")
        private String fullstartdate;
        @SerializedName("enddate")
        private String enddate;
        @SerializedName("url")
        private String url;
        @SerializedName("urlbase")
        private String urlbase;
        @SerializedName("copyright")
        private String copyright;
        @SerializedName("copyrightlink")
        private String copyrightlink;
        @SerializedName("title")
        private String title;
        @SerializedName("quiz")
        private String quiz;
        @SerializedName("wp")
        private Boolean wp;
        @SerializedName("hsh")
        private String hsh;
        @SerializedName("drk")
        private Integer drk;
        @SerializedName("top")
        private Integer top;
        @SerializedName("bot")
        private Integer bot;
        @SerializedName("hs")
        private List<?> hs;
    }
}
