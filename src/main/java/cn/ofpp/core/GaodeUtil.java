package cn.ofpp.core;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static cn.hutool.json.JSONUtil.parseObj;

/**
 * <a href="https://lbs.amap.com">使用的高德</a>
 * @author DokiYolo
 * Date 2022-08-22
 */
public class GaodeUtil {
    // 保留了没删 大家也可以用 一天5000限额
    private static final String key = "1b41782ed2cd4c33ba961c1c77627071";

    private static final String GEO_API = "https://restapi.amap.com/v3/geocode/geo?key=%s&address=%s&city=%s";
    private static final String WEATHER_API = "https://restapi.amap.com/v3/weather/weatherInfo?key=%s&city=%d&extensions=%s";

    public static Integer getAdcCode(String province, String city) {
        HttpResponse response = HttpUtil.createGet(String.format(GEO_API, key, province, city))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        return parseObj(parseObj(response.body()).getJSONArray("geocodes").get(0)).getInt("adcode");
    }

    public static WeatherInfo getNowWeatherInfo(Integer adcCode) {
        HttpResponse response = HttpUtil.createGet(String.format(WEATHER_API, key, adcCode, "all"))
                .setConnectionTimeout(3000)
                .setReadTimeout(4000)
                .execute();
        System.out.println(response.body());
        JSONArray forecasts = parseObj(response.body()).getJSONArray("forecasts");
        Object forecast = forecasts.get(0);
        List<WeatherInfo> casts =
        parseObj(forecast).getJSONArray("casts").toList(WeatherInfo.class);
        //下标为1时返回明日天气
        WeatherInfo weatherInfo = casts.get(1);
        //天气
        String dayweather = weatherInfo.getDayweather();
        //温度
        Integer daytemp = Integer.parseInt(weatherInfo.getDaytemp());
        if (daytemp>36){
            Random r = new Random();
            Integer ints = r.nextInt(4);
            switch (ints){
                case 0:
                    weatherInfo.setTips("晒死啦晒死啦，肖宝记得明天出门涂防晒！");
                    break;
                case 1:
                    weatherInfo.setTips("小邹温馨提示：明天会很热！");
                    break;
                case 2:
                    weatherInfo.setTips("妈耶，明天"+ daytemp +"度，肖宝贝要注意啦~");
                    break;
                case 3:
                    weatherInfo.setTips("呼呼呼~给你吹吹，吹走明天的热气~");
            }
        }
        Integer flag  = dayweather.indexOf("雨");
        if (flag>=0) {
            Random r = new Random();
            Integer ints = r.nextInt(4);
            switch (ints) {
                case 0:
                    weatherInfo.setTips("明天有雨，要记得带伞哟宝贝！");
                    break;
                case 1:
                    weatherInfo.setTips("小邹温馨提示：明天要下雨！");
                    break;
                case 2:
                    weatherInfo.setTips("要下雨啦，要下雨啦，不要忘记带伞肖宝~");
                    break;
                case 3:
                    weatherInfo.setTips("如果明天下雨的话，说明我在想你~");
            }
        }
        if (Integer.parseInt(weatherInfo.getWeek())!=1){
            Random r = new Random();
            Integer ints = r.nextInt(4);
            switch (ints) {
                case 0:
                    weatherInfo.setNotes("小笨蛋，不要忘记写日志哟~");
                    break;
                case 1:
                    weatherInfo.setNotes("oh~又到了提醒宝贝写日志时间~");
                    break;
                case 2:
                    weatherInfo.setNotes("辛苦的一天又快结束了，记得写日志肖宝~");
                    break;
                case 3:
                    weatherInfo.setNotes("来自肖宝的提示：记得写日志~");
            }
        }

        return weatherInfo;
    }


    public static void main(String[] args) {
        Random r = new Random();
        Integer ints = r.nextInt(4);
        System.out.println(ints);
    }

}
