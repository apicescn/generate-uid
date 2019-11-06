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
package com.xueying.seeker.uid.enums;

import com.xueying.seeker.uid.utils.BaseEnumUtils;

/**
 * {@code ValuedEnum} defines an enumeration which is bounded to a value, you
 * may implements this interface when you defines such kind of enumeration, that
 * you can use {@link BaseEnumUtils} to simplify parse and valueOf operation.
 * @param <T>
 * @author yutianbao
 */
public interface ValuedEnum<T> {
    /**
     * 泛型值
     * @return 值
     */
    T value();
}
