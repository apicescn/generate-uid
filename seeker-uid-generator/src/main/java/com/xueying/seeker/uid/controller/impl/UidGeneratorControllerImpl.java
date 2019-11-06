/**
 * Copyright (C), 2019
 * FileName: UidGeneratorControllerImpl
 * Author:   Allen
 * Date:     2019/10/9 18:45
 * Description: UID生成服务controller实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.controller.impl;

import com.xueying.seeker.uid.controller.UidGeneratorController;
import com.xueying.seeker.uid.query.UidGeneratorQuery;
import com.xueying.seeker.uid.service.UidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈UID生成服务controller实现类〉
 *
 * @author Allen
 * @create 2019/10/9
 * @since 1.0.0
 */
@RestController
public class UidGeneratorControllerImpl implements UidGeneratorController {

    /**
     * 注入UidGeneratorService
     */
    @Autowired
    private UidService uidService;

    @Override
    public String getCachedUid() {
        return String.valueOf(uidService.getCachedUid());
    }

    @Override
     public String getCachedUidByPrefix(UidGeneratorQuery uidGeneratorQuery) {
         return uidService.getCachedUidByPrefix(uidGeneratorQuery);
     }

    @Override
    public String getDefaultUid() {
        return String.valueOf(uidService.getDefaultUid());
    }

    @Override
     public String getDefaultUidByPrefix(UidGeneratorQuery uidGeneratorQuery) {
         return uidService.getDefaultUidByPrefix(uidGeneratorQuery);
     }

}
