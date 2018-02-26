 /**
 * 
 */
package com.mysecurity.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author Liziyue
 *
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
	
	private UserDetailsService userDetailService;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 * ���������֤���߼�
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAuthenticationTooken authenticationToken = (SmsAuthenticationTooken)authentication;
		//����ʱ�õ��ֻ��Ŵ����û���Ϣ
		UserDetails user = userDetailService.loadUserByUsername((String) authenticationToken.getPrincipal());
		
		if(user == null) {
			throw new InternalAuthenticationServiceException("�޷���ȡ�û���Ϣ");
		}
		
		//��֤���֮����Ҫ�����û�Ȩ�� ��һ�����û���Ϣ �ڶ������û�Ȩ��
		SmsAuthenticationTooken authenticationResult = new SmsAuthenticationTooken(user,user.getAuthorities());
		
		//��֮ǰδ��֤���details���뵽��֤��ȥ
		authenticationResult.setDetails(authenticationToken.getDetails());
		
		return authenticationResult;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		// �жϴ��������ǲ���SmsAuthenticationTooken
		return SmsAuthenticationTooken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailsService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
	
	
}
