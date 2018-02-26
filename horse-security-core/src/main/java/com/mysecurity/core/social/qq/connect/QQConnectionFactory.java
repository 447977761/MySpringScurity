/**
 * 
 */
package com.mysecurity.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;

import com.mysecurity.core.social.qq.api.QQ;

/**
 * @author Liziyue
 *
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
	
	//providerId�ṩ�̵�Ψһ��ʶ
	public QQConnectionFactory(String providerId, String appId,String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}

}
