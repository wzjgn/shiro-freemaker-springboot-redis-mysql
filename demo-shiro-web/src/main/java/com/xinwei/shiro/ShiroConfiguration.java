package com.xinwei.shiro;

import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;


@Configuration
public class ShiroConfiguration {

	/**
	 * 注释掉该方法时 ，shiro的登录会话session由ehcache保持。
	 * 打开该方法时，shiro的登录回话session由redis保持。
	 * @param jedisShiroSessionRepository
	 * @return
	 */
	@Bean
	@DependsOn(value = { "jedisShiroSessionRepository" })
	public SessionDAO sessionDAO(JedisShiroSessionRepository jedisShiroSessionRepository) {
		final CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
		customShiroSessionDAO.setShiroSessionRepository(jedisShiroSessionRepository);
		return customShiroSessionDAO;
	}

	@Bean
	@DependsOn(value = { "objectRedisTemplate" })
	public JedisShiroSessionRepository jedisShiroSessionRepository(RedisTemplate<String, Object> objectRedisTemplate) {
		final JedisShiroSessionRepository jedisShiroSessionRepository = new JedisShiroSessionRepository();
		jedisShiroSessionRepository.setObjectRedisTemplate(objectRedisTemplate);
		return jedisShiroSessionRepository;
	}

	 

}