/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

import com.imooc.security.core.social.qq.api.QQ;

/**
 * @author zhailiang
 * 泛型是API的类型
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    /**
     * providerId：服务提供商的唯一标志，通过配置文件配置
     * @param providerId
     * @param appId
     * @param appSecret
     */
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
