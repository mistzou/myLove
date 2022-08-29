package cn.ofpp;

import cn.ofpp.core.*;

/**
 * 启动类
 *
 * 这个理论上只能用测试号 正式的号 个人认证是不支持模板消息的 企业认证的又必须使用微信模板库里的模板 只有测试的可以自定义模板内容
 * <a href="https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index">申请公众号测试应用地址</a>
 *
 * @author DokiYolo
 * Date 2022-08-22
 */
public class Application {

    /**
     * <li>创建配置</li>
     * <li>创建几个 男/女 朋友</li>
     * <li>发消息</li>
     */
    public static void main(String[] args) {
        // load and init
        Bootstrap.init();

        // new 一个 女友  oPYS36H7vays1A03owdfR_Y1HQYw oPYS36AoWgHgcSchYdlRvt12kkT4
        GirlFriend girlFriend = new GirlFriend("肖宝",
                "江西省", "南昌市", "2000-12-31", "2020-11-27", "oPYS36CxPfmt7C4UB0sxKbqVOuao","2023-12-31");
        //彩虹屁随机句子，可自定义chp（彩虹屁）、pyq（朋友圈文案）、du（毒鸡汤）
        ShaDiaoSentence sentence = ShaDiaoSentence.getSentence("chp");
        Wx.sendTemplateMessage(MessageFactory.resolveMessage(girlFriend,sentence));

        // new 一个 男友 也可单独针对一个friend设置模板ID 以达到不同人不同消息
        BoyFriend boyFriend = new BoyFriend("某男友",
                "江苏省", "南京市", "1999-08-08", "2011-04-16", "oQFk-5qtXv2uGNCu9oiCiV85KWD8", "5t7-Ksy8_rw-QmUkxf8J7Pe-QLQ2rBc7RWJi_pSmeh4");
        //Wx.sendTemplateMessage(MessageFactory.resolveMessage(boyFriend));
    }

}
