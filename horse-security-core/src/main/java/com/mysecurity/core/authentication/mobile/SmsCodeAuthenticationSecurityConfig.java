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
 * 配置短信验证码安全认证配置
 */
@Component("smsCodeAuthenticationSecurityConfig")
public class SmsCodeAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>{
	//自动注入我们自己写的AuthenticationFailureHandler
	@Autowired
	private AuthenticationFailureHandler MyAuthenticationFailureHandler;
	
	@Autowired
	private AuthenticationSuccessHandler MyAuthenticationSuccessHandler;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		
		//配置过滤器
		//设置认证管理器
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(MyAuthenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(MyAuthenticationFailureHandler);
		
		//配置provider
		SmsCodeAuthenticationProvider smsCodeAuthenticationProvider = new SmsCodeAuthenticationProvider();
		smsCodeAuthenticationProvider.setUserDetailService(userDetailsService);
	
		//加入springSecurity框架中去     将smsCodeAuthenticationFilter加到UsernamePasswordAuthenticationFilter后面
		http.authenticationProvider(smsCodeAuthenticationProvider)
			.addFilterAfter(smsCodeAuthenticationFilter,UsernamePasswordAuthenticationFilter.class);
	
	}
}
