/**
 * 
 */
package com.mysecurity.core.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author Liziyue
 * ���ö�����֤�밲ȫ��֤����
 */
@Component("smsCodeAuthenticationSecurityConfig")
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	//�Զ�ע�������Լ�д��AuthenticationFailureHandler
	@Autowired
	private AuthenticationFailureHandler MyAuthenticationFailureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler MyAuthenticationSuccessHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		
		//���ù�����
		//������֤������
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(MyAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(MyAuthenticationFailureHandler);
		
		//����provider
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailService(userDetailsService);
	
		//����springSecurity�����ȥ     ��smsCodeAuthenticationFilter�ӵ�UsernamePasswordAuthenticationFilter����
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	
	}
}
