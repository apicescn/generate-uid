/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: SystemClock
 * Author:   Allen
 * Date:     2019年11月6日
 * Description: 系统时钟
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.twitter;


import com.xueying.seeker.uid.utils.NamingThreadFactory;

import java.sql.Timestamp;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈SystemClock〉
 * 高并发场景下System.currentTimeMillis()的性能问题的优化
 * <p>
 * <p>
 * System.currentTimeMillis()的调用比new一个普通对象要耗时的多（参加https://www.jianshu.com/p/3fbe607600a5）
 * <p>
 * System.currentTimeMillis()之所以慢是因为去跟系统打了一次交道
 * <p>
 * 后台定时更新时钟，JVM退出时，线程自动回收
 * <p>
 * 10亿：43410,206,210.72815533980582%
 * <p>
 * 1亿：4699,29,162.0344827586207%
 * <p>
 * 1000万：480,12,40.0%
 * <p>
 * 100万：50,10,5.0%
 * <p>
 * @author Allen
 * @create 2019/11/6
 * @since 1.0.0
 */
public final class SystemClock {
    /**
     * 线程名--系统时钟
     */
    public static final String THREAD_CLOCK_NAME = "System Clock";

    /**
     * period
     */
    private final long period;

    /**
     * now时间
     */
    private final AtomicLong now;

    /**
     * 构造器
     * @param period 入参
     */
    private SystemClock(long period) {
        this.period = period;
        this.now = new AtomicLong(System.currentTimeMillis());
        scheduleClockUpdating();
    }

    /**
     * 实例化
     */
    private static class InstanceHolder {
        /**
         * 实例化
         */
        public static final SystemClock INSTANCE = new SystemClock(1);
    }

    private static SystemClock instance() {
        return InstanceHolder.INSTANCE;
    }

    /**
     * 更新时钟
     */
    private void scheduleClockUpdating() {
        ScheduledExecutorService scheduledpool = new ScheduledThreadPoolExecutor(1,
                new NamingThreadFactory(THREAD_CLOCK_NAME, true));
        scheduledpool.scheduleAtFixedRate(() -> {
            now.set(System.currentTimeMillis());
        }, period, period, TimeUnit.MILLISECONDS);
    }

    /**
     * 得到时间
     * @return 得到时间
     */
    private long currentTimeMillis() {
        return now.get();
    }

    /**
     * 得到Long现在时间
     * @return 得到现在时间
     */
    public static long now() {
        return instance().currentTimeMillis();
    }

    /**
     * 字符串的时间
     * @return 字符串的时间
     */
    public static String nowDate() {
        return new Timestamp(instance().currentTimeMillis()).toString();
    }
}
