/**
 * 
 */
package com.mysecurity.browser.logout;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysecurity.browser.support.SimpleResponse;


/**
 * Ĭ�ϵ��˳��ɹ������������������com.mysecurity.browser.signOutUrl�����������õĵ�ַ�ϣ�
 * ���û���ã��򷵻�json��ʽ����Ӧ��
 * 
 * @author zhailiang
 *
 */
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());

	public MyLogoutSuccessHandler(String signOutSuccessUrl) {
		this.signOutSuccessUrl = signOutSuccessUrl;
	}

	private String signOutSuccessUrl;
	
	private ObjectMapper objectMapper = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.logout.
	 * LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		logger.info("�˳��ɹ�");

		if (StringUtils.isBlank(signOutSuccessUrl)) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("�˳��ɹ�")));
		} else {
			response.sendRedirect(signOutSuccessUrl);
		}

	}

}