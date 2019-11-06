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
package com.xueying.seeker.uid.worker;

import com.xueying.seeker.uid.impl.DefaultUidGenerator;

/**
 * Represents a worker uniqueId assigner for {@link DefaultUidGenerator}
 * 
 * @author yutianbao
 */
public interface WorkerIdAssigner {

    /**
     * Assign worker uniqueId for {@link DefaultUidGenerator}
     *
     * @return assigned worker uniqueId
     */
    long assignWorkerId();

    /**
     * Assign fake worker uniqueId
     *
     * @return assigned fake worker uniqueId
     */
    long assignFakeWorkerId();
}