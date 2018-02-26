/**
 * 
 */
package com.mysecurity.core.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import com.mysecurity.core.social.weixin.api.Weixin;



/**
 * ΢�����ӹ���
 * 
 * @author zhailiang
 *
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<Weixin> {
	
	/**
	 * @param appId
	 * @param appSecret
	 */
	public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new WeixinServiceProvider(appId, appSecret), new WeixinAdapter());
	}
	
	/**
	 * ����΢�ŵ�openId�Ǻ�accessTokenһ�𷵻صģ�����������ֱ�Ӹ���accessToken����providerUserId���ɣ�������QQ����ͨ��QQAdapter����ȡ
	 */
	@Override
	protected String extractProviderUserId(AccessGrant accessGrant) {
		if(accessGrant instanceof WeixinAccessGrant) {
			return ((WeixinAccessGrant)accessGrant).getOpenId();
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.oauth2.AccessGrant)
	 */
	public Connection<Weixin> createConnection(AccessGrant accessGrant) {
		return new OAuth2Connection<Weixin>(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(),
				accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
	}

	/* (non-Javadoc)
	 * @see org.springframework.social.connect.support.OAuth2ConnectionFactory#createConnection(org.springframework.social.connect.ConnectionData)
	 */
	public Connection<Weixin> createConnection(ConnectionData data) {
		return new OAuth2Connection<Weixin>(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
	}
	
	private ApiAdapter<Weixin> getApiAdapter(String providerUserId) {
		return new WeixinAdapter(providerUserId);
	}
	
	private OAuth2ServiceProvider<Weixin> getOAuth2ServiceProvider() {
		return (OAuth2ServiceProvider<Weixin>) getServiceProvider();
	}

	
}