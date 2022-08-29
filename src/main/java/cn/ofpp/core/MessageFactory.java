package cn.ofpp.core;

import cn.hutool.core.util.StrUtil;
import cn.ofpp.Bootstrap;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import static cn.ofpp.core.GaodeUtil.getAdcCode;

/**
 * @author DokiYolo
 * Date 2022-08-22
 */
public class MessageFactory {

    public static WxMpTemplateMessage resolveMessage(Friend friend,ShaDiaoSentence sentence) {
        return WxMpTemplateMessage.builder()
                .url("https://ofpp.cn") // 点击后的跳转链接 可自行修改 也可以不填
                .toUser(friend.getUserId())
                .templateId(StrUtil.emptyToDefault(friend.getTemplateId(), Bootstrap.TEMPLATE_ID))
                .data(buildData(friend,sentence))
                .build();
    }

    /**
     *
     * {@code {{xxxx.DATA}}} xxxx就是一个变量名，消息中设置变量 然后传递时传递变量即可
     * <br/>
     * 色彩取值可以从这里挑选 https://arco.design/palette/list
     *
     *  <p>
     *      你叫{{friendName.DATA}}
     *      今年{{howOld.DATA}}
     *      距离下一次生日{{nextBirthday.DATA}}天
     *      具体我们的下一次纪念日{{nextMemorialDay.DATA}}天
     *      现在在{{province.DATA}}{{city.DATA}}
     *      当前天气{{weather.DATA}}
     *      当前气温{{temperature.DATA}}
     *      风力描述{{winddirection.DATA}}
     *      风力级别{{windpower.DATA}}
     *      空气湿度{{humidity.DATA}}
     *      {{author.DATA}}
     *      {{origin.DATA}}
     *      {{content.DATA}}
     *      ----------------
     *      {{friendName.DATA}}现在在{{province.DATA}}{{city.DATA}}
     * 距离梅宝生日还有{{nextBirthday.DATA}}天
     * 我们在一起了{{nextMemorialDay.DATA}}天
     * 明日天气：{{weather.DATA}}
     * 明日气温：{{temperature.DATA}}
     * 风力描述：{{winddirection.DATA}}
     * 风力级别：{{windpower.DATA}}
     * {{humidity.DATA}}
     * {{notes.DATA}}
     * {{content.DATA}}
     *  </p>
     */
    private static List<WxMpTemplateData> buildData(Friend friend, ShaDiaoSentence sentence) {
        WeatherInfo weather = GaodeUtil.getNowWeatherInfo(getAdcCode(friend.getProvince()+friend.getCity(), friend.getCity()));
        RandomAncientPoetry.AncientPoetry ancientPoetry = RandomAncientPoetry.getNext();
        List<WxMpTemplateData> wxMpTemplateDataList = new ArrayList<>();
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("friendName").value(friend.getFullName()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("howLongLived").value(friend.getHowLongLived()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("nextMemorialDay").value(friend.getNextMemorialDay()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("province").value(friend.getProvince()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("city").value(friend.getCity()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("weather").value(weather.getDayweather()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("temperature").value(weather.getDaytemp()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("winddirection").value(weather.getDaywind()).color("#000000").build());
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("windpower").value(weather.getDaypower()).color("#000000").build());
        if (StringUtils.isNotBlank(weather.getNotes())){
            wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("notes").value(weather.getNotes()).color("#000000").build());
        }
        if (StringUtils.isNotBlank(weather.getTips())){
            wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("humidity").value(weather.getTips()).color("#000000").build());
        }
        wxMpTemplateDataList.add(TemplateDataBuilder.builder().name("content").value(sentence.getText()).color("#000000").build());
        return wxMpTemplateDataList;
    }

    static class TemplateDataBuilder {
        private String name;
        private String value;
        private String color;

        public static TemplateDataBuilder builder() {
            return new TemplateDataBuilder();
        }
        public TemplateDataBuilder name(String name) {
            this.name = name;
            return this;
        }
        public TemplateDataBuilder value(String value) {
            this.value = value;
            return this;
        }
        public TemplateDataBuilder color(String color) {
            this.color = color;
            return this;
        }
        public WxMpTemplateData build() {
            if (StrUtil.hasEmpty(name, value)) {
                throw new IllegalArgumentException("参数不正确");
            }
            WxMpTemplateData data = new WxMpTemplateData();
            data.setName(name);
            data.setValue(value);
            data.setColor(color);
            return data;
        }
    }

}
