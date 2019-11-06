package com.xueying.seeker.uid.config;

import com.xueying.seeker.uid.impl.CachedUidGenerator;
import com.xueying.seeker.uid.impl.DefaultUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * UID 的自动配置
 * 
 * @author tangyz
 *
 */
@Configuration
@ConditionalOnClass({CachedUidGenerator.class, DefaultUidGenerator.class})
@EnableConfigurationProperties({UidGeneratorProperties.class, CachedUidGenerator.class})
public class UidAutoConfigure {

	/**
	 * 注入UidGeneratorProperties
	 */
	@Autowired
	private UidGeneratorProperties uidGeneratorProperties;

	@Bean
	@ConditionalOnMissingBean
	DefaultUidGenerator defaultUidGenerator() {
		return new DefaultUidGenerator(uidGeneratorProperties);
	}

	@Bean
	@ConditionalOnMissingBean
	CachedUidGenerator cachedUidGenerator() {
		return new CachedUidGenerator(uidGeneratorProperties);
	}

}
