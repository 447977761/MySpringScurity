/**
 * 
 */
package com.mysecurity.core.social.weixin.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.mysecurity.core.social.weixin.api.Weixin;
import com.mysecurity.core.social.weixin.api.WeixinImpl;



/**
 * 
 * ΢�ŵ�OAuth2���̴��������ṩ������spring social��connect��ϵ����
 * 
 * @author zhailiang
 *
 */
public class WeixinServiceProvider extends AbstractOAuth2ServiceProvider<Weixin> {
	
	/**
	 * ΢�Ż�ȡ��Ȩ���url
	 */
	private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
	/**
	 * ΢�Ż�ȡaccessToken��url
	 */
	private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeixinServiceProvider(String appId, String appSecret) {
		super(new WeixinOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
	}


	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.AbstractOAuth2ServiceProvider#getApi(java.lang.String)
	 */
	@Override
	public Weixin getApi(String accessToken) {
		return new WeixinImpl(accessToken);
	}

}
