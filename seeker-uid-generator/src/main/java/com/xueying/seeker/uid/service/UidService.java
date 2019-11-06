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
package com.xueying.seeker.uid.service;

import com.xueying.seeker.uid.query.UidGeneratorQuery;
import com.xueying.seeker.uid.worker.service.UidGeneratorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * UID的生成服务类，用于实现Uid的生成
 *
 * @author Allen
 * @create 2019/10/9
 * @since 1.0.0
 */
@Service
public class UidService extends UidGeneratorService {

    /**
     * 根据前缀及业务ID生成Uid
     *
     * @param uidGeneratorQuery 入参信息
     * @desc 业务前缀符号业务ID符号uid
     * @return  前缀及业务ID以符合方式生成的Uid
     */
    public String getCachedUidByPrefix(UidGeneratorQuery uidGeneratorQuery) {
        StringBuffer uidValue = getUidPrefix(uidGeneratorQuery);
        uidValue.append(cachedUidGenerator.getUID());
        return uidValue.toString();
    }

    /**
     * 根据前缀及业务ID生成Uid
     *
     * @param uidGeneratorQuery 入参信息
     * @desc 业务前缀符号业务ID符号uid
     * @return  前缀及业务ID及符号生成的Uid
     */
    public String getDefaultUidByPrefix(UidGeneratorQuery uidGeneratorQuery) {
        StringBuffer uidValue = getUidPrefix(uidGeneratorQuery);
        uidValue.append(defaultUidGenerator.getUID());
        return uidValue.toString();
    }

    /**
     * 得到产生UID的前缀部分
     * @param uidGeneratorQuery 入参信息
     * @return 产生UID的前缀部分
     */
    protected StringBuffer getUidPrefix(UidGeneratorQuery uidGeneratorQuery) {
        StringBuffer uidValue = new StringBuffer();
        uidValue.append(uidGeneratorQuery.getPrefix()).append(!StringUtils.isBlank(uidGeneratorQuery.getSign())
                ? uidGeneratorQuery.getSign() : "");
        if (!StringUtils.isBlank(uidGeneratorQuery.getServerId())) {
            uidValue.append(uidGeneratorQuery.getServerId()).append(!StringUtils.isBlank(uidGeneratorQuery.getSign())
                    ? uidGeneratorQuery.getSign() : "");
        }
        return uidValue;
    }
}
