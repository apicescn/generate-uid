/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UidGeneratorService
 * Author:   Allen
 * Date:     2019/10/9 18:45
 * Description: 预先缓存UID的生成器服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.worker.service;

import com.xueying.seeker.uid.UidGenerator;
import com.xueying.seeker.uid.worker.DisposableWorkerIdAssigner;
import com.xueying.seeker.uid.worker.WorkerIdAssigner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * UID的生成服务类，用于实现Uid的生成
 *
 * @author Allen
 * @create 2019/10/9
 * @since 1.0.0
 */
@Service
public class UidGeneratorService {

    /**
     * 注入cachedUidGenerator
     */
    @Resource(name = "cachedUidGenerator")
    protected UidGenerator cachedUidGenerator;

    /**
     * 注入defaultUidGenerator
     */
    @Resource(name = "defaultUidGenerator")
    protected UidGenerator defaultUidGenerator;

    /**
     * 初始化DisposableWorkerIdAssigner
     * @return WorkerIdAssigner对象
     */
    @Bean
    @ConditionalOnMissingBean
    WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

    /**
     * 得到预先缓存的Uid
     * @return 预先缓存的Uid
     */
    public long getCachedUid() {
        return cachedUidGenerator.getUID();
    }

    /**
     * 得到生成的Uid
     * @return 生成的Uid
     */
    public long getDefaultUid() {
        return defaultUidGenerator.getUID();
    }
}
