/*
 * Copyright (c) 2017 Baidu, Inc. All Rights Reserve.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xueying.seeker.uid.utils;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * BaseDateUtils provides date formatting, parsing
 *
 * @author yutianbao
 */
public abstract class BaseDateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * Patterns
     */
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 时间格式
     */
    public static final String DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 默认时间
     */
    public static final Date DEFAULT_DATE = BaseDateUtils.parseByDayPattern("1970-01-01");

    /**
     * Parse date by 'yyyy-MM-dd' pattern
     *
     * @param str 时间字符串
     * @return 时间
     */
    public static Date parseByDayPattern(String str) {
        return parseDate(str, DAY_PATTERN);
    }

    /**
     * Parse date by 'yyyy-MM-dd HH:mm:ss' pattern
     *
     * @param str 时间字符串
     * @return 时间
     */
    public static Date parseByDateTimePattern(String str) {
        return parseDate(str, DATETIME_PATTERN);
    }

    /**
     * Parse date without Checked exception
     *
     * @param str 时间字符串
     * @param pattern 时间格式
     * @return 时间
     * @throws RuntimeException when ParseException occurred
     */
    public static Date parseDate(String str, String pattern) {
        try {
            return parseDate(str, new String[]{pattern});
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Format date into string
     *
     * @param date 时间
     * @param pattern 时间格式
     * @return 时间字符串
     */
    public static String formatDate(Date date, String pattern) {
        return DateFormatUtils.format(date, pattern);
    }

    /**
     * Format date by 'yyyy-MM-dd' pattern
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String formatByDayPattern(Date date) {
        if (date != null) {
            return DateFormatUtils.format(date, DAY_PATTERN);
        } else {
            return null;
        }
    }

    /**
     * Format date by 'yyyy-MM-dd HH:mm:ss' pattern
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String formatByDateTimePattern(Date date) {
        return DateFormatUtils.format(date, DATETIME_PATTERN);
    }

    /**
     * Get current day using format date by 'yyyy-MM-dd HH:mm:ss' pattern
     *
     * @return 时间字符串
     * @author yebo
     */
    public static String getCurrentDayByDayPattern() {
        Calendar cal = Calendar.getInstance();
        return formatByDayPattern(cal.getTime());
    }

}
