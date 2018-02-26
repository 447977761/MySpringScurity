/**
 * 
 */
package com.mysecurity.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import com.mysecurity.browser.logout.MyLogoutSuccessHandler;
import com.mysecurity.browser.session.MyExpiredSessionStrategy;
import com.mysecurity.browser.session.MyInvalidSessionStrategy;
import com.mysecurity.core.properties.SecurityProperties;



/**
 * �������������չ�����ã������������bean��ҵ��ϵͳ������ͨ������ͬ���ͻ�ͬ����bean�����ǰ�ȫ
 * ģ��Ĭ�ϵ����á�
 * 
 * @author zhailiang
 *
 */
@Configuration
public class BrowserSecurityBeanConfig {

	@Autowired
	private SecurityProperties securityProperties;
	
	/**
	 * sessionʧЧʱ�Ĵ�����������
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(InvalidSessionStrategy.class)
	public InvalidSessionStrategy invalidSessionStrategy(){
		return new MyInvalidSessionStrategy(securityProperties);
	}
	
	/**
	 * ������¼����ǰһ��sessionʧЧʱ�Ĵ�����������
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
	public SessionInformationExpiredStrategy sessionInformationExpiredStrategy(){
		return new MyExpiredSessionStrategy(securityProperties);
	}
	

	/**
	 * �˳�ʱ�Ĵ�����������
	 * 
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean(LogoutSuccessHandler.class)
	public LogoutSuccessHandler logoutSuccessHandler(){
		return new MyLogoutSuccessHandler(securityProperties.getBrowser().getSingOutUrl());
	}
	
}