/**
 * 
 */
package com.mysecurity.browser.support;

/**
 * @author Liziyue
 *
 */
public class SocialUserInfo {
	
	private String providerId;
	
	//openid
	private String providerUserId;
	
	//�û��ǳ�
	private String nickname;
	
	//�û�ͷ��
	private String headimg;

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public String getProviderUserId() {
		return providerUserId;
	}

	public void setProviderUserId(String providerUserId) {
		this.providerUserId = providerUserId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	
	
	
	
}
