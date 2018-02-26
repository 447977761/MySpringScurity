/**
 * 
 */
package com.mysecurity.core.social.qq.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Template;

import com.mysecurity.core.social.qq.api.QQ;
import com.mysecurity.core.social.qq.api.QQImpl;

/**
 * @author Liziyue
 * 泛型为api的接口
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	
	private String appId;
	
	//将用户导向认证服务器的url
	private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	
	//向服务商申请令牌的url
	private static final String URL_ACCESSTOKEN = "https://graph.qq.com/oauth2.0/token";
	
	//在QQ互联上注册应用连接的服务的时候 qq会给每一个注册提供一个appid 和appsecret
	public QQServiceProvider(String appId,String appSecret) {
		super(new QQAuth2Template(appId, appSecret , URL_AUTHORIZE, URL_ACCESSTOKEN));
		this.appId = appId;
	}

	@Override
	public QQ getApi(String accessToken) {		
		return new QQImpl(accessToken,appId);
	}

}
