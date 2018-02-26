/**
 * 
 */
package com.mysecurity.core.social.qq.connect;

import java.nio.charset.Charset;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Liziyue
 *
 */
public class QQAuth2Template extends OAuth2Template {

	public QQAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		//��QQ�ĸ�ʽҪ���б�����client_id client_secret
		//���Ҫ��setUseParametersForClientAuthentication����Ϊtrue������������������
		setUseParametersForClientAuthentication(true);
	}
	
	
	//������ĸ�ʽ����QQ�ĸ�ʽ�����Զ�������
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		String [] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expireIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		//ˢ�����Ʊ���
		String refrashToken = StringUtils.substringAfterLast(items[2], "=");
		return new AccessGrant(accessToken,null,refrashToken,expireIn);
	}
	
	
	@Override
	protected RestTemplate createRestTemplate() {
		//�õ�����Ĵ������
		RestTemplate restTemplate = super.createRestTemplate();
		//����һ���µ�converters   �����ܹ�����html/text contenttype������
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}