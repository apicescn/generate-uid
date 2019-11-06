/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UidGeneratorController
 * Author:   Allen
 * Date:     2019/10/9 18:45
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.controller;

import com.xueying.seeker.uid.query.UidGeneratorQuery;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;

import javax.validation.Valid;

/**
 * 〈UID生成器controller〉
 *
 * @author Allen
 * @create 2019/10/9
 * @since 1.0.0
 */
public interface UidGeneratorController {

    /** 预先缓存的UID的请求URL */
    String GET_GENERATOR_CACHED_UID = "/api/generator/cached/uid";

    /** 根据前缀及业务ID生成Uid的请求URL */
    String GET_GENERATOR_CACHED_UID_PREFIX = "/api/generator/cached/uid/prefix";

    /** 生成的UID的请求URL */
    String GET_GENERATOR_DEFAULT_UID = "/api/generator/default/uid";

    /** 根据前缀及业务ID生成Uid的请求URL */
    String GET_GENERATOR_DEFAULT_UID_PREFIX = "/api/generator/default/uid/prefix";

    /**
     * 得到预先缓存的UID-有序UID
     *
     * @return 预先缓存的UID-有序UID
     */
    @GetMapping(value = GET_GENERATOR_CACHED_UID, produces = {MediaType.APPLICATION_JSON_VALUE})
    String getCachedUid();

    /**
     * 根据前缀及业务ID获得缓存生成的Uid-有序UID
     * @param uidGeneratorQuery 入参信息
     * @return 根据前缀及业务ID及组合符号缓存生成的Uid-有序UID(例如XHF-01-123，其中-为符号)
     */
    @GetMapping(value = GET_GENERATOR_CACHED_UID_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
    String getCachedUidByPrefix(@Valid UidGeneratorQuery uidGeneratorQuery);

    /**
     * 得到生成的UID-无序UID
     *
     * @return 生成的UID-无序UID
     */
    @GetMapping(value = GET_GENERATOR_DEFAULT_UID, produces = {MediaType.APPLICATION_JSON_VALUE})
    String getDefaultUid();

    /**
     * 根据前缀及业务ID生成Uid-无序UID
     * @param uidGeneratorQuery 入参信息
     * @return 根据前缀及业务ID生成Uid-无序UID(例如XHF-01-123，其中-为符号)
     */
    @GetMapping(value = GET_GENERATOR_DEFAULT_UID_PREFIX, produces = {MediaType.APPLICATION_JSON_VALUE})
    String getDefaultUidByPrefix(@Valid UidGeneratorQuery uidGeneratorQuery);

}
