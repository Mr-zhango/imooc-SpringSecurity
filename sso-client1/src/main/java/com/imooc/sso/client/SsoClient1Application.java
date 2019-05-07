/**
 * 
 */
package com.imooc.sso.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhailiang
 *
 */
@SpringBootApplication
@RestController
@EnableOAuth2Sso // 开启SSO
public class SsoClient1Application {

    /**
     * 获取当前登录的Authentication信息，SpringSecurity会自动注入
     * @param user
     * @return
     */
	@GetMapping("/user")
	public Authentication user(Authentication user) {
		return user;
	}

	public static void main(String[] args) {
		SpringApplication.run(SsoClient1Application.class, args);
	}
	
}
