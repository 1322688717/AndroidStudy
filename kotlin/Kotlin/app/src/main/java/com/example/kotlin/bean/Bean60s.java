package com.example.kotlin.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bean60s {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("name")
    private String name;
    @SerializedName("time")
    private List<String> time;
    @SerializedName("data")
    private List<String> data;
    @SerializedName("imgUrl")
    private String imgUrl;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
