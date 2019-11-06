/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UidGeneratorQuery
 * Author:   Allen
 * Date:     2019/10/10 14:51
 * Description: 获得UID的入参
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.query;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 〈获得UID的入参〉
 *
 * @author Allen
 * @date 2019/10/10
 * @since 1.0.0
 */
@Data
public class UidGeneratorQuery implements Serializable {
    /**
     * 业务前缀
     */
    @Size(max = 5, message = "业务前缀字符长度范围在0-5")
    private String prefix;

    /**
     * 业务ID
     */
    @Size(max = 5, message = "业务ID字符长度范围在0-5")
    private String serverId;

    /**
     * 符号
     */
    @Size(max = 1, message = "符号长度范围在0-1")
    private String sign;

}
