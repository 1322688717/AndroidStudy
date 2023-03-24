package com.example.kotlin.bean;

import com.google.gson.annotations.SerializedName;

public class WeatherBean {

    @SerializedName("success")
    private Boolean success;
    @SerializedName("city")
    private String city;
    @SerializedName("info")
    private InfoDTO info;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InfoDTO getInfo() {
        return info;
    }

    public void setInfo(InfoDTO info) {
        this.info = info;
    }

    public static class InfoDTO {
        @SerializedName("date")
        private String date;
        @SerializedName("week")
        private String week;
        @SerializedName("type")
        private String type;
        @SerializedName("high")
        private String high;
        @SerializedName("low")
        private String low;
        @SerializedName("fengxiang")
        private String fengxiang;
        @SerializedName("fengli")
        private String fengli;
        @SerializedName("night")
        private NightDTO night;
        @SerializedName("air")
        private AirDTO air;
        @SerializedName("tip")
        private String tip;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }

        public String getFengxiang() {
            return fengxiang;
        }

        public void setFengxiang(String fengxiang) {
            this.fengxiang = fengxiang;
        }

        public String getFengli() {
            return fengli;
        }

        public void setFengli(String fengli) {
            this.fengli = fengli;
        }

        public NightDTO getNight() {
            return night;
        }

        public void setNight(NightDTO night) {
            this.night = night;
        }

        public AirDTO getAir() {
            return air;
        }

        public void setAir(AirDTO air) {
            this.air = air;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }

        public static class NightDTO {
            @SerializedName("type")
            private String type;
            @SerializedName("fengxiang")
            private String fengxiang;
            @SerializedName("fengli")
            private String fengli;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getFengxiang() {
                return fengxiang;
            }

            public void setFengxiang(String fengxiang) {
                this.fengxiang = fengxiang;
            }

            public String getFengli() {
                return fengli;
            }

            public void setFengli(String fengli) {
                this.fengli = fengli;
            }
        }

        public static class AirDTO {
            @SerializedName("aqi")
            private Integer aqi;
            @SerializedName("aqi_level")
            private Integer aqiLevel;
            @SerializedName("aqi_name")
            private String aqiName;
            @SerializedName("co")
            private String co;
            @SerializedName("no2")
            private String no2;
            @SerializedName("o3")
            private String o3;
            @SerializedName("pm10")
            private String pm10;
            @SerializedName("pm2.5")
            private String _$Pm25263;// FIXME check this code
            @SerializedName("so2")
            private String so2;

            public Integer getAqi() {
                return aqi;
            }

            public void setAqi(Integer aqi) {
                this.aqi = aqi;
            }

            public Integer getAqiLevel() {
                return aqiLevel;
            }

            public void setAqiLevel(Integer aqiLevel) {
                this.aqiLevel = aqiLevel;
            }

            public String getAqiName() {
                return aqiName;
            }

            public void setAqiName(String aqiName) {
                this.aqiName = aqiName;
            }

            public String getCo() {
                return co;
            }

            public void setCo(String co) {
                this.co = co;
            }

            public String getNo2() {
                return no2;
            }

            public void setNo2(String no2) {
                this.no2 = no2;
            }

            public String getO3() {
                return o3;
            }

            public void setO3(String o3) {
                this.o3 = o3;
            }

            public String getPm10() {
                return pm10;
            }

            public void setPm10(String pm10) {
                this.pm10 = pm10;
            }

            public String get_$Pm25263() {
                return _$Pm25263;
            }

            public void set_$Pm25263(String _$Pm25263) {
                this._$Pm25263 = _$Pm25263;
            }

            public String getSo2() {
                return so2;
            }

            public void setSo2(String so2) {
                this.so2 = so2;
            }
        }
    }
}
