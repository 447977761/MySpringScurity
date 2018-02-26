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
		//在QQ的格式要求中必须有client_id client_secret
		//因此要将setUseParametersForClientAuthentication设置为true让他传递这两个参数
		setUseParametersForClientAuthentication(true);
	}
	
	
	//将请求的格式按照QQ的格式进行自定义排列
	@Override
	protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
		String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
		String [] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
		String accessToken = StringUtils.substringAfterLast(items[0], "=");
		Long expireIn = new Long(StringUtils.substringAfterLast(items[1], "="));
		//刷新令牌变量
		String refrashToken = StringUtils.substringAfterLast(items[2], "=");
		return new AccessGrant(accessToken,null,refrashToken,expireIn);
	}
	
	
	@Override
	protected RestTemplate createRestTemplate() {
		//拿到父类的创建结果
		RestTemplate restTemplate = super.createRestTemplate();
		//添加一个新的converters   让它能够处理html/text contenttype的请求
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
