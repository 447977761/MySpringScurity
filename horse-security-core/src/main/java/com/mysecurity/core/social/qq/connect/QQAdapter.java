/**
 * 
 */
package com.mysecurity.core.social.qq.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mysecurity.core.social.qq.api.QQ;
import com.mysecurity.core.social.qq.api.QQUserInfo;

/**
 * @author Liziyue
 * 泛型api类型
 */
public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		// 测试api是否可用
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		//获取用户信息
		QQUserInfo userInfo = api.getUserInfo();
		
		//将信息设置到value中
		//用户的名字    将信息里的昵称拿出来
		values.setDisplayName(userInfo.getNickname());
		//头像
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		//个人主页  QQ没有
		values.setProfileUrl(null);
		//服务商的用户id 就是openid
		values.setProviderUserId(userInfo.getOpenId());
		
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// QQ没有此功能 do nothing
		
	}

	

}
