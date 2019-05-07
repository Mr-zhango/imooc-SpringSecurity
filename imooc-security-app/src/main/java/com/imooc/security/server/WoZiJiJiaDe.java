package com.imooc.security.server;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * smsCodeAuthenticationSecurityConfig类中需要一个PersistentTokenRepository
 * 而app没有这个Bean，于是自己加了一个
 * @author Administrator
 *
 */
@Configuration
public class WoZiJiJiaDe {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
		tokenRepository.setDataSource(dataSource);
		// 在JdbcTokenRepositoryImpl中有一个常亮CREATE_TABLE_SQL，就是用来创建表的sql，可以在数据库中执行这段sql
		// 也可以使用下面的方式创建，但是如果表已经存在了，下面的代码会报错
//		tokenRepository.setCreateTableOnStartup(true);
		return tokenRepository;
	}

}
