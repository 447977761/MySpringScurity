/**
 * 
 */
package com.mysecurity.core.social.weixin.api;

/**
 * ΢��API���ýӿ�
 * 
 * @author Liziyue
 *
 */
public interface Weixin {

	/* (non-Javadoc)
	 * @see com.ymt.pz365.framework.security.social.api.SocialUserProfileService#getUserProfile(java.lang.String)
	 */
	WeixinUserInfo getUserInfo(String openId);
	
}