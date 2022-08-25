package cn.ofpp.core;

import cn.hutool.core.date.*;
import org.checkerframework.checker.units.qual.C;

import java.util.Calendar;
import java.util.Date;

import static cn.hutool.core.date.DateUtil.age;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Friend {

    private final String fullName;

    private final Integer howOld;

    private final String province;

    private final String city;

    private final String userId;

    private final String birthday;

    private final String nextBirthday;

    private final String loveTime;

    private final String sex;

    private final String templateId;

    public Friend(String fullName, String province, String city, String userId, String birthday, String loveTime, String sex,String nextBirthday) {
        this(fullName, province, city, userId, birthday, loveTime, sex, null,nextBirthday);
    }

    public Friend(String fullName, String province, String city, String userId, String birthday, String loveTime, String sex, String templateId,String nextBirthday) {
        this.fullName = fullName;
        this.howOld = age(DateUtil.parse(birthday), new Date());
        this.province = province;
        this.city = city;
        this.userId = userId;
        this.birthday = birthday;
        this.loveTime = loveTime;
        this.sex = sex;
        this.templateId = templateId;
        this.nextBirthday = nextBirthday;
    }

    public String getFullName() {
        return fullName;
    }

    public Integer getHowOld() {
        return howOld;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getUserId() {
        return userId;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getLoveTime() {
        return loveTime;
    }

    public String getSex() {
        return sex;
    }

    public String getTemplateId() {
        return templateId;
    }

    public String getHowLongLived() {
        return String.valueOf(DateUtil.between(new Date(), DateUtil.parse(birthday), DateUnit.DAY));
    }

    public String getNextBirthdayDays() {
        return getChineseYear(nextBirthday);
    }

    public String getNextMemorialDay() {
        return getBetweenDays(loveTime);
    }

    public static String getNextDay(DateTime dateTime) {
        dateTime = DateUtil.beginOfDay(dateTime);
        DateTime now = DateUtil.beginOfDay(new Date());
        dateTime.offset(DateField.YEAR, now.year() - dateTime.year());
        if (now.isAfter(dateTime)) {
            return String.valueOf(dateTime.offset(DateField.YEAR, 1).between(now, DateUnit.DAY));
        }
        return String.valueOf(dateTime.between(now, DateUnit.DAY));
    }

    public static String getChineseYear(String nextBirthday){
        DateTime dateTime = new DateTime(nextBirthday);
        ChineseDate date = new ChineseDate(dateTime);
        Calendar gregorianCalendar = date.getGregorianCalendar();
        DateTime calendarToDateTime = new DateTime(gregorianCalendar);
        return getBetweenDays(calendarToDateTime.toString());
    }

    public static String getBetweenDays(String date){
        DateTime dateTime = new DateTime(date);
        String now = DateUtil.now();
        DateTime nowDateTime = new DateTime(now);
        long between = DateUtil.between(dateTime, nowDateTime, DateUnit.DAY);
        return Long.toString(between);
    }

    public static void main(String[] args) {
        String betweenDays = getBetweenDays("2022-08-20");
        System.out.println(betweenDays);
    }

}
