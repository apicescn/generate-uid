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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseDockerUtils
 * 
 * @author yutianbao
 */
public abstract class BaseDockerUtils {
    /**
     * Logger配置
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseDockerUtils.class);

    /** Environment param keys */
    private static final String ENV_KEY_HOST = "JPAAS_HOST";
    /**
     * JPAAS_HTTP_PORT
     */
    private static final String ENV_KEY_PORT = "JPAAS_HTTP_PORT";
    /**
     * ENV_KEY_PORT_ORIGINAL
     */
    private static final String ENV_KEY_PORT_ORIGINAL = "JPAAS_HOST_PORT_8080";

    /** Docker host & port */
    private static String dockerHost = "";
    /**
     * dockerPort
     */
    private static String dockerPort = "";

    /** Whether is docker */
    private static boolean isDocker;

    static {
        retrieveFromEnv();
    }

    /**
     * Retrieve docker host
     * 
     * @return empty string if not a docker
     */
    public static String getDockerHost() {
        return dockerHost;
    }

    /**
     * Retrieve docker port
     * 
     * @return empty string if not a docker
     */
    public static String getDockerPort() {
        return dockerPort;
    }

    /**
     * Whether a docker
     * 
     * @return isDocker
     */
    public static boolean isDocker() {
        return isDocker;
    }

    /**
     * Retrieve host & port from environment
     */
    private static void retrieveFromEnv() {
        // retrieve host & port from environment
        dockerHost = System.getenv(ENV_KEY_HOST);
        dockerPort = System.getenv(ENV_KEY_PORT);

        // not found from 'JPAAS_HTTP_PORT', then try to find from 'JPAAS_HOST_PORT_8080'
        if (StringUtils.isBlank(dockerPort)) {
            dockerPort = System.getenv(ENV_KEY_PORT_ORIGINAL);
        }

        boolean hasEnvHost = StringUtils.isNotBlank(dockerHost);
        boolean hasEnvPort = StringUtils.isNotBlank(dockerPort);

        // docker can find both host & port from environment
        if (hasEnvHost && hasEnvPort) {
            isDocker = true;

            // found nothing means not a docker, maybe an actual machine
        } else if (!hasEnvHost && !hasEnvPort) {
            isDocker = false;

        } else {
            LOGGER.error("Missing host or port from env for Docker. host:{}, port:{}", dockerHost, dockerPort);
            throw new RuntimeException(
                    "Missing host or port from env for Docker. host:" + dockerHost + ", port:" + dockerPort);
        }
    }

}
