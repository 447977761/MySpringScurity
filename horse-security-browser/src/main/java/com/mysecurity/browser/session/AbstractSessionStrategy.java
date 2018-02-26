/**
 * 
 */
package com.mysecurity.browser.session;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysecurity.browser.support.SimpleResponse;
import com.mysecurity.core.properties.SecurityProperties;

/**
 * �����sessionʧЧ������
 * 
 * @author Liziyue
 *
 */
public class AbstractSessionStrategy {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * ��ת��url
	 */
	private String destinationUrl;
	/**
	 * ϵͳ������Ϣ
	 */
	private SecurityProperties securityPropertie;
	/**
	 * �ض������
	 */
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	/**
	 * ��תǰ�Ƿ񴴽��µ�session
	 */
	private boolean createNewSession = true;

	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @param invalidSessionUrl
	 * @param invalidSessionHtmlUrl
	 */
	public AbstractSessionStrategy(SecurityProperties securityPropertie) {
		String invalidSessionUrl = securityPropertie.getBrowser().getSession().getSessionInvalidUrl();
		Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
		Assert.isTrue(StringUtils.endsWithIgnoreCase(invalidSessionUrl, ".html"), "url must end with '.html'");
		this.destinationUrl = invalidSessionUrl;
		this.securityPropertie = securityPropertie;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#
	 * onInvalidSessionDetected(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {

		logger.info("sessionʧЧ");
		
		if (createNewSession) {
			request.getSession();
		}

		String sourceUrl = request.getRequestURI();
		String targetUrl;

		if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
			if(StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSingUpUrl())
					|| StringUtils.equals(sourceUrl, securityPropertie.getBrowser().getSingOutUrl())){
				targetUrl = sourceUrl;
			}else{
				targetUrl = destinationUrl;
			}
			logger.info("��ת��:"+targetUrl);
			redirectStrategy.sendRedirect(request, response, targetUrl);
		} else {
			Object result = buildResponseContent(request);
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(result));
		}

	}

	/**
	 * @param request
	 * @return
	 */
	protected Object buildResponseContent(HttpServletRequest request) {
		String message = "session��ʧЧ";
		if (isConcurrency()) {
			message = message + "���п����ǲ�����¼���µ�";
		}
		return new SimpleResponse(message);
	}

	/**
	 * sessionʧЧ�Ƿ��ǲ������µ�
	 * 
	 * @return
	 */
	protected boolean isConcurrency() {
		return false;
	}

	/**
	 * Determines whether a new session should be created before redirecting (to
	 * avoid possible looping issues where the same session ID is sent with the
	 * redirected request). Alternatively, ensure that the configured URL does
	 * not pass through the {@code SessionManagementFilter}.
	 *
	 * @param createNewSession
	 *            defaults to {@code true}.
	 */
	public void setCreateNewSession(boolean createNewSession) {
		this.createNewSession = createNewSession;
	}

}
