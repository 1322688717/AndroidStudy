package com.example.retrofitapplication;

import java.util.List;

@lombok.NoArgsConstructor
@lombok.Data
public class Bean {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getFxLink() {
        return fxLink;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }

    public NowDTO getNow() {
        return now;
    }

    public void setNow(NowDTO now) {
        this.now = now;
    }

    public ReferDTO getRefer() {
        return refer;
    }

    public void setRefer(ReferDTO refer) {
        this.refer = refer;
    }

    @com.google.gson.annotations.SerializedName("code")
    private String code;
    @com.google.gson.annotations.SerializedName("updateTime")
    private String updateTime;
    @com.google.gson.annotations.SerializedName("fxLink")
    private String fxLink;
    @com.google.gson.annotations.SerializedName("now")
    private NowDTO now;
    @com.google.gson.annotations.SerializedName("refer")
    private ReferDTO refer;

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class NowDTO {
        public String getObsTime() {
            return obsTime;
        }

        public void setObsTime(String obsTime) {
            this.obsTime = obsTime;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(String feelsLike) {
            this.feelsLike = feelsLike;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getWind360() {
            return wind360;
        }

        public void setWind360(String wind360) {
            this.wind360 = wind360;
        }

        public String getWindDir() {
            return windDir;
        }

        public void setWindDir(String windDir) {
            this.windDir = windDir;
        }

        public String getWindScale() {
            return windScale;
        }

        public void setWindScale(String windScale) {
            this.windScale = windScale;
        }

        public String getWindSpeed() {
            return windSpeed;
        }

        public void setWindSpeed(String windSpeed) {
            this.windSpeed = windSpeed;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getPrecip() {
            return precip;
        }

        public void setPrecip(String precip) {
            this.precip = precip;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
        }

        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
        }

        public String getDew() {
            return dew;
        }

        public void setDew(String dew) {
            this.dew = dew;
        }

        @com.google.gson.annotations.SerializedName("obsTime")
        private String obsTime;
        @com.google.gson.annotations.SerializedName("temp")
        private String temp;
        @com.google.gson.annotations.SerializedName("feelsLike")
        private String feelsLike;
        @com.google.gson.annotations.SerializedName("icon")
        private String icon;
        @com.google.gson.annotations.SerializedName("text")
        private String text;
        @com.google.gson.annotations.SerializedName("wind360")
        private String wind360;
        @com.google.gson.annotations.SerializedName("windDir")
        private String windDir;
        @com.google.gson.annotations.SerializedName("windScale")
        private String windScale;
        @com.google.gson.annotations.SerializedName("windSpeed")
        private String windSpeed;
        @com.google.gson.annotations.SerializedName("humidity")
        private String humidity;
        @com.google.gson.annotations.SerializedName("precip")
        private String precip;
        @com.google.gson.annotations.SerializedName("pressure")
        private String pressure;
        @com.google.gson.annotations.SerializedName("vis")
        private String vis;
        @com.google.gson.annotations.SerializedName("cloud")
        private String cloud;
        @com.google.gson.annotations.SerializedName("dew")
        private String dew;
    }

    @lombok.NoArgsConstructor
    @lombok.Data
    public static class ReferDTO {
        @com.google.gson.annotations.SerializedName("sources")
        private List<String> sources;
        @com.google.gson.annotations.SerializedName("license")
        private List<String> license;
    }
}
