package com.example.baidusdk_application.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LifeIndexResponse {

    @SerializedName("code")
    private String code;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("fxLink")
    private String fxLink;
    @SerializedName("daily")
    private List<DailyDTO> daily;
    @SerializedName("refer")
    private ReferDTO refer;

    @NoArgsConstructor
    @Data
    public static class ReferDTO {
        @SerializedName("sources")
        private List<String> sources;
        @SerializedName("license")
        private List<String> license;
    }

    @NoArgsConstructor
    @Data
    public static class DailyDTO {
        @SerializedName("date")
        private String date;
        @SerializedName("type")
        private String type;
        @SerializedName("name")
        private String name;
        @SerializedName("level")
        private String level;
        @SerializedName("category")
        private String category;
        @SerializedName("text")
        private String text;
    }
}
