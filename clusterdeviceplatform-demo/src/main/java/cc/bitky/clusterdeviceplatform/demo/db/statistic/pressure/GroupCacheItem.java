package cc.bitky.clusterdeviceplatform.demo.db.statistic.pressure;

import cc.bitky.clusterdeviceplatform.demo.config.DeviceSetting;
import cc.bitky.clusterdeviceplatform.demo.tcp.statistic.channel.ChannelItem;
import cc.bitky.clusterdeviceplatform.demo.web.spa.data.random.MsgCountRandom;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class GroupCacheItem {
    private static MsgCountRandom countRandom;
    /**
     * 通道对象集合
     */
    private List<ChannelItem> channelItems;
    /**
     * 消息数量分类统计
     */
    private MsgCount msgCount;
    /**
     * 负载正常的界限
     */
    private int normalLimit;
    /**
     * 负载异常的界限
     */
    private int exceptionLimit;

    public GroupCacheItem(List<ChannelItem> channelItems, MsgCount msgCount) {
        this.channelItems = channelItems;
        this.msgCount = msgCount;
    }

    /**
     * Web 演示模式，随机生成该对象
     *
     * @return 随机生成的该对象，用于 Web 演示模式
     */
    public static GroupCacheItem randomInstance() {
        Random random = new Random();
        List<ChannelItem> items = new ArrayList<>(DeviceSetting.MAX_GROUP_ID);
        for (int i = 1; i <= DeviceSetting.MAX_GROUP_ID; i++) {
            items.add(new ChannelItem(i, true, random.nextInt(600)));
        }
        countRandom = new MsgCountRandom(countRandom);
        return new GroupCacheItem(items, countRandom);
    }

    /**
     * 设置前端界面报警的限定
     *
     * @param normalLimit    负载正常的界限
     * @param exceptionLimit 负载异常的界限
     */
    public void setAlarmLimit(int normalLimit, int exceptionLimit) {
        this.normalLimit = normalLimit;
        this.exceptionLimit = exceptionLimit;
    }
}
