/**
 * Copyright (C), 2019, 安徽雪影实业有限公司
 * FileName: UIDContants
 * Author:   Allen
 * Date:     2019/10/10 9:49
 * Description: UID常量类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xueying.seeker.uid.contants;

/**
 * 〈UID常量类〉
 *
 * @author Allen
 * @date 2019/10/10
 * @since 1.0.0
 */
public class UIDContants {

    /**
     * 数字常量-1
     */
    public static final Long MINUS_ONE_CONSTANT_LONG = -1L;

    /**
     * 数字常量1
     */
    public static final Integer ONE_CONSTANT_INT = 1;

    /**
     * 数字常量2
     */
    public static final Integer TWO_CONSTANT_INT = 2;

    /**
     * 数字常量5
     */
    public static final Integer FIVE_CONSTANT_INT = 5;

    /**
     * 数字常量6
     */
    public static final Integer SIX_CONSTANT_INT = 6;

    /**
     * 数字常量7
     */
    public static final Long SEVEN_CONSTANT_LONG = 7L;
    /**
     * 数字常量7
     */
    public static final Long FORTY_ONE_CONSTANT_LONG = 41L;
    /**
     * 数字常量7
     */
    public static final Long SIXTY_FOUR_CONSTANT_LONG = 64L;
    /**
     * 数字常量1558281600000L
     */
    public static final Long INFINITY_CONSTANT_LONG = 1558281600000L;

    /**
     * 数字常量100000
     */
    public static final Integer LAKH_CONSTANT_LONG = 100000;

    /**
     * 数字常量100
     */
    public static final Integer HUNDRED_CONSTANT_LONG = 100;

    /**
     * 数字常量28
     */
    public static final Integer TWENTY_EIGHT_CONSTANT_LONG = 28;
    /**
     * 数字常量22
     */
    public static final Integer TWENTY_TWO_CONSTANT_LONG = 22;
    /**
     * 数字常量13
     */
    public static final Integer TEN_THREE_CONSTANT_LONG = 13;

    /**
     * 单次操作产生100个uid
     */
    public static final int STEP_SIZE = 100;

    /**
     * 批量的uid ==> key
     */
    public static final String UID_GENERATOR_LIST = "seeker:uid:generator:list";

    /**
     * 分布式锁,flag位
     */
    public static final String FLAG_BIT = "seeker:uid:generator";

    /**
     * 分布式锁,flag值
     */
    public static final String FLAG_BIT_VALUE = "1";

    /**
     * 设置 uid ==> key list的失效时间：10 分钟
     */
    public static final Integer UID_GENERATOR_LIST_EXPIRE = 60 * 10;
}
