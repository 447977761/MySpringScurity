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
 * ����api����
 */
public class QQAdapter implements ApiAdapter<QQ> {

	@Override
	public boolean test(QQ api) {
		// ����api�Ƿ����
		return true;
	}

	@Override
	public void setConnectionValues(QQ api, ConnectionValues values) {
		//��ȡ�û���Ϣ
		QQUserInfo userInfo = api.getUserInfo();
		
		//����Ϣ���õ�value��
		//�û�������    ����Ϣ����ǳ��ó���
		values.setDisplayName(userInfo.getNickname());
		//ͷ��
		values.setImageUrl(userInfo.getFigureurl_qq_1());
		//������ҳ  QQû��
		values.setProfileUrl(null);
		//�����̵��û�id ����openid
		values.setProviderUserId(userInfo.getOpenId());
		
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		
		return null;
	}

	@Override
	public void updateStatus(QQ api, String message) {
		// QQû�д˹��� do nothing
		
	}

	

}