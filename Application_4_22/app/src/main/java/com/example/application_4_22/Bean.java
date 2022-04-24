package com.example.application_4_22;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class Bean {

    @SerializedName("code")
    private String code;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("fxLink")
    private String fxLink;
    @SerializedName("now")
    private NowDTO now;
    @SerializedName("refer")
    private ReferDTO refer;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class NowDTO {
        @SerializedName("obsTime")
        private String obsTime;
        @SerializedName("temp")
        private String temp;
        @SerializedName("feelsLike")
        private String feelsLike;
        @SerializedName("icon")
        private String icon;
        @SerializedName("text")
        private String text;
        @SerializedName("wind360")
        private String wind360;
        @SerializedName("windDir")
        private String windDir;
        @SerializedName("windScale")
        private String windScale;
        @SerializedName("windSpeed")
        private String windSpeed;
        @SerializedName("humidity")
        private String humidity;
        @SerializedName("precip")
        private String precip;
        @SerializedName("pressure")
        private String pressure;
        @SerializedName("vis")
        private String vis;
        @SerializedName("cloud")
        private String cloud;
        @SerializedName("dew")
        private String dew;
    }

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ReferDTO {
        @SerializedName("sources")
        private List<String> sources;
        @SerializedName("license")
        private List<String> license;
    }
}
