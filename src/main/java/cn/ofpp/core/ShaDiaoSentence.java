package cn.ofpp.core;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.lang3.StringUtils;

public class ShaDiaoSentence {


    private String type;

    private String text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    //https://api.shadiao.pro/pyq  https://api.shadiao.pro/chp https://api.shadiao.pro/du
    public static ShaDiaoSentence  getSentence(String type) {
        String url = null;
        if ("pyq".equals(type)){
            url = "https://api.shadiao.pro/pyq";
        }else if ("chp".equals(type)){
            url = "https://api.shadiao.pro/chp";
        }else if ("du".equals(type)){
            url = "https://api.shadiao.pro/du";
        }
        String res = HttpUtil.get(url, 4000);
        JSONObject resJson = new JSONObject(res);
        JSONObject data = resJson.getJSONObject("data");
        return JSONUtil.parseObj(data).toBean(ShaDiaoSentence.class);
    }



    public static void main(String[] args) {
//        ShaDiaoSentence pyq = getSentence("pyq");
//        System.out.println("type:"+ pyq.getType() +"-----text:" + pyq.getText());
    }


}
