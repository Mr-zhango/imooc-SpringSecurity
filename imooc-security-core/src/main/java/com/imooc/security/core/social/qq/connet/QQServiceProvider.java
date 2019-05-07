/**
 * 
 */
package com.imooc.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.imooc.security.core.social.qq.api.QQ;
import com.imooc.security.core.social.qq.api.QQImpl;

/**
 * @author zhailiang
 *
 * 抽象类AbstractOAuth2ServiceProvider的泛型是指Api接口的实现类
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

	private String appId;

    /**
     * 流程中的第一步：导向认证服务器的url
     */
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    /**
     * 第四步：申请令牌的url
     */
	private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    /**
     * 因为每个应用的AppId和AppSecret都不一样
     * @param appId
     * @param appSecret
     */
	public QQServiceProvider(String appId, String appSecret) {
		super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
		this.appId = appId;
	}
	

	@Override
	public QQ getApi(String accessToken) {
		// QQImpl必须是多例的，不能把QQImpl声明为@Component组件（这是视频中的原话，不是可以指明@Scope吗？）
		// accessToken抽象类会直接传进来
		return new QQImpl(accessToken, appId);
	}

}
