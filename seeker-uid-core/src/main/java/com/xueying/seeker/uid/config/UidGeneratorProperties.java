package com.xueying.seeker.uid.config;

import com.xueying.seeker.uid.contants.UIDContants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * UID 的配置
 *
 * @author tangyz
 *
 */
@Data
@ConfigurationProperties(prefix = "seeker.uid")
public class UidGeneratorProperties {

    /** 时间增量值占用位数。当前时间相对于时间基点的增量值，单位为秒 */
    private int timeBits = UIDContants.TWENTY_EIGHT_CONSTANT_LONG;

    /** 工作机器ID占用的位数 */
    private int workerBits = UIDContants.TWENTY_TWO_CONSTANT_LONG;

    /** 序列号占用的位数 */
    private int seqBits = UIDContants.TEN_THREE_CONSTANT_LONG;

	/** 时间基点. 例如 2018-11-26 (毫秒: 1543161600000) */
    private String epochStr = "2019-05-20";

    /** 时间基点对应的毫秒数 */
    private long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(UIDContants.INFINITY_CONSTANT_LONG);

    /** 时钟回拨最长容忍时间（秒）*/
    private long maxBackwardSeconds = 1L;

    /** 是否容忍时钟回拨, 默认:true */
    private boolean enableBackward = true;
}
