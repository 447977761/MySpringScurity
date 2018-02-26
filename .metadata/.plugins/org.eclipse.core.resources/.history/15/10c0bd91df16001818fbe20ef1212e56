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
	 * 进行身份认证的逻辑
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsAuthenticationTooken authenticationToken = (SmsAuthenticationTooken)authentication;
		//将当时拿的手机号传入用户信息
		UserDetails user = userDetailService.loadUserByUsername((String) authenticationToken.getPrincipal());
		
		if(user == null) {
			throw new InternalAuthenticationServiceException("无法获取用户信息");
		}
		
		//认证完成之后需要给予用户权限 第一个是用户信息 第二个是用户权限
		SmsAuthenticationTooken authenticationResult = new SmsAuthenticationTooken(user,user.getAuthorities());
		
		//将之前未认证里的details放入到认证中去
		authenticationResult.setDetails(authenticationToken.getDetails());
		
		return authenticationResult;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		// 判断传进来的是不是SmsAuthenticationTooken
		return SmsAuthenticationTooken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailService() {
		return userDetailService;
	}

	public void setUserDetailService(UserDetailsService userDetailService) {
		this.userDetailService = userDetailService;
	}
	
	
	
}
