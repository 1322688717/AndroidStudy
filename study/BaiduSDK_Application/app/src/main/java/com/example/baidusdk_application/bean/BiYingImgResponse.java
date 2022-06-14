package com.example.baidusdk_application.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BiYingImgResponse {
    public List<ImagesDTO> getImages() {
        return images;
    }

    public void setImages(List<ImagesDTO> images) {
        this.images = images;
    }

    public TooltipsDTO getTooltips() {
        return tooltips;
    }

    public void setTooltips(TooltipsDTO tooltips) {
        this.tooltips = tooltips;
    }

    @SerializedName("images")
    private List<ImagesDTO> images;
    @SerializedName("tooltips")
    private TooltipsDTO tooltips;

    @NoArgsConstructor
    @Data
    public static class TooltipsDTO {
        public String getLoading() {
            return loading;
        }

        public void setLoading(String loading) {
            this.loading = loading;
        }

        public String getPrevious() {
            return previous;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public String getWalle() {
            return walle;
        }

        public void setWalle(String walle) {
            this.walle = walle;
        }

        public String getWalls() {
            return walls;
        }

        public void setWalls(String walls) {
            this.walls = walls;
        }

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
        public String getStartdate() {
            return startdate;
        }

        public void setStartdate(String startdate) {
            this.startdate = startdate;
        }

        public String getFullstartdate() {
            return fullstartdate;
        }

        public void setFullstartdate(String fullstartdate) {
            this.fullstartdate = fullstartdate;
        }

        public String getEnddate() {
            return enddate;
        }

        public void setEnddate(String enddate) {
            this.enddate = enddate;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlbase() {
            return urlbase;
        }

        public void setUrlbase(String urlbase) {
            this.urlbase = urlbase;
        }

        public String getCopyright() {
            return copyright;
        }

        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        public String getCopyrightlink() {
            return copyrightlink;
        }

        public void setCopyrightlink(String copyrightlink) {
            this.copyrightlink = copyrightlink;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getQuiz() {
            return quiz;
        }

        public void setQuiz(String quiz) {
            this.quiz = quiz;
        }

        public Boolean getWp() {
            return wp;
        }

        public void setWp(Boolean wp) {
            this.wp = wp;
        }

        public String getHsh() {
            return hsh;
        }

        public void setHsh(String hsh) {
            this.hsh = hsh;
        }

        public Integer getDrk() {
            return drk;
        }

        public void setDrk(Integer drk) {
            this.drk = drk;
        }

        public Integer getTop() {
            return top;
        }

        public void setTop(Integer top) {
            this.top = top;
        }

        public Integer getBot() {
            return bot;
        }

        public void setBot(Integer bot) {
            this.bot = bot;
        }

        public List<?> getHs() {
            return hs;
        }

        public void setHs(List<?> hs) {
            this.hs = hs;
        }

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
