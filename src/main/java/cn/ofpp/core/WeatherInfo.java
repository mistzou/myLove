package cn.ofpp.core;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class WeatherInfo {


    private String dayweather;
    private String daytemp;
    private String daywind;
    private String daypower;
    private String tips;
    private String notes;
    private String week;

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getDayweather() {
        return dayweather;
    }

    public void setDayweather(String dayweather) {
        this.dayweather = dayweather;
    }

    public String getDaytemp() {
        return daytemp;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setDaytemp(String daytemp) {
        this.daytemp = daytemp;
    }

    public String getDaywind() {
        return daywind;
    }

    public void setDaywind(String daywind) {
        this.daywind = daywind;
    }

    public String getDaypower() {
        return daypower;
    }

    public void setDaypower(String daypower) {
        this.daypower = daypower;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }
}
