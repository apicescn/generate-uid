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

import com.xueying.seeker.uid.contants.UIDContants;
import com.xueying.seeker.uid.utils.BaseDockerUtils;
import com.xueying.seeker.uid.worker.entity.WorkerNodeEntity;
import com.xueying.seeker.uid.utils.BaseNetUtils;
import com.xueying.seeker.uid.worker.dao.WorkerNodeDAO;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Represents an implementation of {@link WorkerIdAssigner},
 * the worker uniqueId will be discarded after assigned to the UidGenerator
 *
 * @author yutianbao
 */
public class DisposableWorkerIdAssigner implements WorkerIdAssigner {

    /**
     * Logger配置
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(DisposableWorkerIdAssigner.class);

    /**
     * 注入WorkerNodeDAO
     */
    @Resource
    private WorkerNodeDAO workerNodeDAO;

    /**
     * Assign worker uniqueId base on database.<p>
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker uniqueId
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long assignWorkerId() {
        // build worker node entity
        WorkerNodeEntity workerNodeEntity = buildWorkerNode();

        // add worker node for new (ignore the same IP + PORT)
        workerNodeDAO.insert(workerNodeEntity);
        LOGGER.info("Add worker node:" + workerNodeEntity);

        return workerNodeEntity.getUniqueId();
    }

    /**
     * Assign Fake worker uniqueId base on database
     * @return assigned Fake worker uniqueId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public long assignFakeWorkerId() {
        return buildFakeWorkerNode().getUniqueId();
    }

    /**
     * Build worker node entity by IP and PORT
     * @return WorkerNodeEntity实体对象
     */
    private WorkerNodeEntity buildWorkerNode() {
        WorkerNodeEntity workerNodeEntity = new WorkerNodeEntity();
        if (BaseDockerUtils.isDocker()) {
            workerNodeEntity.setType(WorkerNodeType.CONTAINER.value());
            workerNodeEntity.setHostName(BaseDockerUtils.getDockerHost());
            workerNodeEntity.setPort(BaseDockerUtils.getDockerPort());
        } else {
            workerNodeEntity.setType(WorkerNodeType.ACTUAL.value());
            workerNodeEntity.setHostName(BaseNetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(1,
                    UIDContants.LAKH_CONSTANT_LONG));
        }
        workerNodeEntity.setModified(new Date());
        workerNodeEntity.setCreated(new Date());
        return workerNodeEntity;
    }

    /**
     * Build worker node entity by IP and PORT
     * @return WorkerNodeEntity实体对象
     */
    private WorkerNodeEntity buildFakeWorkerNode() {
        WorkerNodeEntity workerNodeEntity = new WorkerNodeEntity();
        workerNodeEntity.setType(WorkerNodeType.FAKE.value());
        if (BaseDockerUtils.isDocker()) {
            workerNodeEntity.setHostName(BaseDockerUtils.getDockerHost());
            workerNodeEntity.setPort(BaseDockerUtils.getDockerPort() + "-" + RandomUtils.nextInt(1,
                    UIDContants.LAKH_CONSTANT_LONG));
        } else {
            workerNodeEntity.setHostName(BaseNetUtils.getLocalAddress());
            workerNodeEntity.setPort(System.currentTimeMillis() + "-" + RandomUtils.nextInt(1,
                    UIDContants.LAKH_CONSTANT_LONG));
        }
        workerNodeEntity.setModified(new Date());
        workerNodeEntity.setCreated(new Date());
        return workerNodeEntity;
    }
}
