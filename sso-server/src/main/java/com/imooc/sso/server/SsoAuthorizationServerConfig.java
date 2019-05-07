/**
 * 
 */
package com.imooc.sso.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author zhailiang
 *
 */
@Configuration
@EnableAuthorizationServer // 有了这个注解，当前应用就是一个标准的OAuth2认证服务器
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// 配置认证服务器需要给哪些应用发令牌，我们的例子中是imooc1和imooc2
		clients.inMemory()
				.withClient("imooc1") // clientId
				.secret("imoocsecrect1") // clientSecret
				.authorizedGrantTypes("authorization_code", "refresh_token") // 支持的授权模式
				.scopes("all")
				.and()
				.withClient("imooc2")
				.secret("imoocsecrect2")
				.authorizedGrantTypes("authorization_code", "refresh_token")
				.scopes("all");
	}
	
	// 配置使用JWT生产令牌
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.tokenStore(jwtTokenStore()).accessTokenConverter(jwtAccessTokenConverter());
	}
	
	// 认证服务器的安全配置，配置的 isAuthenticated() 是SpringSecurity的授权表达式，默认是 denyAll()，拒绝所有访问
	// 它的意思是访问访问认证服务器的tokenKey的时候，需要进行认证
	// tokenKey 就是对 JWT进行签名的key，即imooc
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()");
	}

    /**
     * 下面两个Bean是配置JWT令牌的
     * @return
     */
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter(){
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        // 对JWT进行前面的key
		converter.setSigningKey("imooc");
        return converter;
	}

}
