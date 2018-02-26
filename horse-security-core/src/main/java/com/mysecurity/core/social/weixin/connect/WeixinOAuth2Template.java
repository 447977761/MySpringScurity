/**
 * 
 */
package com.mysecurity.core.social.weixin.connect;

import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * ���΢�ŵ�OAuth2��֤���̵�ģ���ࡣ���ڳ���ʵ�ֵ�OAuth2ÿ������ͬ, springĬ���ṩ��OAuth2Template��Ӧ���ˣ�ֻ�����ÿ�������Լ�΢����
 * 
 * @author Liziyue
 *
 */
public class WeixinOAuth2Template extends OAuth2Template {
	
	private String clientId;
	
	private String clientSecret;

	private String accessTokenUrl;
	
	private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	public WeixinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		setUseParametersForClientAuthentication(true);
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.accessTokenUrl = accessTokenUrl;
	}
	
	/* (non-Javadoc)
	 * @see org.springframework.social.oauth2.OAuth2Template#exchangeForAccess(java.lang.String, java.lang.String, org.springframework.util.MultiValueMap)
	 */
	@Override
	public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri,
			MultiValueMap<String, String> parameters) {
		
		StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);
		
		accessTokenRequestUrl.append("?appid="+clientId);
		accessTokenRequestUrl.append("&secret="+clientSecret);
		accessTokenRequestUrl.append("&code="+authorizationCode);
		accessTokenRequestUrl.append("&grant_type=authorization_code");
		accessTokenRequestUrl.append("&redirect_uri="+redirectUri);
		
		return getAccessToken(accessTokenRequestUrl);
	}
	
	public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
		
		StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);
		
		refreshTokenUrl.append("?appid="+clientId);
		refreshTokenUrl.append("&grant_type=refresh_token");
		refreshTokenUrl.append("&refresh_token="+refreshToken);
		
		return getAccessToken(refreshTokenUrl);
	}

	@SuppressWarnings("unchecked")
	private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
		
		logger.info("��ȡaccess_token, ����URL: "+accessTokenRequestUrl.toString());
		
		String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);
		
		logger.info("��ȡaccess_token, ��Ӧ����: "+response);
		
		Map<String, Object> result = null;
		try {
			result = new ObjectMapper().readValue(response, Map.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//���ش�����ʱֱ�ӷ��ؿ�
		if(StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))){
			String errcode = MapUtils.getString(result, "errcode");
			String errmsg = MapUtils.getString(result, "errmsg");
			throw new RuntimeException("��ȡaccess tokenʧ��, errcode:"+errcode+", errmsg:"+errmsg);
		}
		
		WeixinAccessGrant accessToken = new WeixinAccessGrant(
				MapUtils.getString(result, "access_token"), 
				MapUtils.getString(result, "scope"), 
				MapUtils.getString(result, "refresh_token"), 
				MapUtils.getLong(result, "expires_in"));
		
		accessToken.setOpenId(MapUtils.getString(result, "openid"));
		
		return accessToken;
	}
	
	/**
	 * ������ȡ��Ȩ�������Ҳ���������û���ת��΢�ŵĵ�ַ��
	 */
	public String buildAuthenticateUrl(OAuth2Parameters parameters) {
		String url = super.buildAuthenticateUrl(parameters);
		url = url + "&appid="+clientId+"&scope=snsapi_login";
		return url;
	}
	
	public String buildAuthorizeUrl(OAuth2Parameters parameters) {
		return buildAuthenticateUrl(parameters);
	}
	
	/**
	 * ΢�ŷ��ص�contentType��html/text�������Ӧ��HttpMessageConverter������
	 */
	protected RestTemplate createRestTemplate() {
		RestTemplate restTemplate = super.createRestTemplate();
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
		return restTemplate;
	}

}
