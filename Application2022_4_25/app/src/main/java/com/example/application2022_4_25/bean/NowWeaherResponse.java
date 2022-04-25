package com.example.application2022_4_25.bean;

import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class NowWeaherResponse {

    @com.fasterxml.jackson.annotation.JsonProperty("reason")
    private String reason;
    @com.fasterxml.jackson.annotation.JsonProperty("result")
    private ResultDTO result;
    @com.fasterxml.jackson.annotation.JsonProperty("error_code")
    private Integer errorCode;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ResultDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("city")
        private String city;
        @com.fasterxml.jackson.annotation.JsonProperty("realtime")
        private ResultDTO.RealtimeDTO realtime;
        @com.fasterxml.jackson.annotation.JsonProperty("future")
        private List<FutureDTO> future;

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class RealtimeDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("temperature")
            private String temperature;
            @com.fasterxml.jackson.annotation.JsonProperty("humidity")
            private String humidity;
            @com.fasterxml.jackson.annotation.JsonProperty("info")
            private String info;
            @com.fasterxml.jackson.annotation.JsonProperty("wid")
            private String wid;
            @com.fasterxml.jackson.annotation.JsonProperty("direct")
            private String direct;
            @com.fasterxml.jackson.annotation.JsonProperty("power")
            private String power;
            @com.fasterxml.jackson.annotation.JsonProperty("aqi")
            private String aqi;
        }

        @lombok.NoArgsConstructor
        @lombok.Data
        public static class FutureDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("date")
            private String date;
            @com.fasterxml.jackson.annotation.JsonProperty("temperature")
            private String temperature;
            @com.fasterxml.jackson.annotation.JsonProperty("weather")
            private String weather;
            @com.fasterxml.jackson.annotation.JsonProperty("wid")
            private ResultDTO.FutureDTO.WidDTO wid;
            @com.fasterxml.jackson.annotation.JsonProperty("direct")
            private String direct;

            @lombok.NoArgsConstructor
            @lombok.Data
            public static class WidDTO {
                @com.fasterxml.jackson.annotation.JsonProperty("day")
                private String day;
                @com.fasterxml.jackson.annotation.JsonProperty("night")
                private String night;
            }
        }
    }
}
