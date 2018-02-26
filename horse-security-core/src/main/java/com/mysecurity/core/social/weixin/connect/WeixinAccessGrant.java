/**
 * 
 */
package com.mysecurity.core.social.weixin.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * ΢�ŵ�access_token��Ϣ�����׼OAuth2Э�鲻ͬ��΢���ڻ�ȡaccess_tokenʱ��ͬʱ����openId,��û�е�����ͨ��accessToke��ȡopenId�ķ���
 * 
 * ����������̳��˱�׼AccessGrant�������openId�ֶΣ���Ϊ��΢��access_token��Ϣ�ķ�װ��
 * 
 * @author Liziyue
 *
 */
public class WeixinAccessGrant extends AccessGrant {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7243374526633186782L;
	
	private String openId;
	
	public WeixinAccessGrant() {
		super("");
	}

	public WeixinAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
		super(accessToken, scope, refreshToken, expiresIn);
	}

	/**
	 * @return the openId
	 */
	public String getOpenId() {
		return openId;
	}

	/**
	 * @param openId the openId to set
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
}
