package cn.ofpp.core;

/**
 * 你可以new一个女朋友
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class GirlFriend extends Friend {

    public GirlFriend(String fullName, String province, String city, String birthday, String loveTime, String userId,String nextBirthday) {
        super(fullName, province, city, userId, birthday, loveTime, "女",nextBirthday);
    }

    public GirlFriend(String fullName, String province, String city, String birthday, String loveTime, String userId, String templateId,String nextBirthday) {
        super(fullName, province, city, userId, birthday, loveTime, "女", templateId,nextBirthday);
    }

}
