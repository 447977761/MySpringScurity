/**
 * 
 */
package com.mysecurity.core.authentication.mobile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import com.mysecurity.core.properties.SecurityConstants;

/**
 * @author Liziyue
 *
 */
public class SmsCodeAuthenticationFilter extends
AbstractAuthenticationProcessingFilter{
	// ~ Static fields/initializers
		// =====================================================================================

		public static final String SPRING_SECURITY_FORM_MOBILE_KEY = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;

		private String mobileParameter = SPRING_SECURITY_FORM_MOBILE_KEY;
		//只处理post请求
		private boolean postOnly = true;

		// ~ Constructors
		// ===================================================================================================

		public SmsCodeAuthenticationFilter() {
			//请求匹配器
			super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
		}

		// ~ Methods
		// ========================================================================================================

		public Authentication attemptAuthentication(HttpServletRequest request,
				HttpServletResponse response) throws AuthenticationException {
			if (postOnly && !request.getMethod().equals("POST")) {
				throw new AuthenticationServiceException(
						"Authentication method not supported: " + request.getMethod());
			}

			String mobile = obtainMobile(request);

			if (mobile == null) {
				mobile = "";
			}

			

			mobile = mobile.trim();

			SmsAuthenticationTooken authRequest = new SmsAuthenticationTooken(mobile);

			// Allow subclasses to set the "details" property
			//将请求信息设入到tooken中
			setDetails(request, authRequest);
			
			return this.getAuthenticationManager().authenticate(authRequest);
		}

		

		/**
		 * 获取手机号方法
		 *
		 * @param request so that request attributes can be retrieved
		 *
		 * @return the username that will be presented in the <code>Authentication</code>
		 * request token to the <code>AuthenticationManager</code>
		 */
		protected String obtainMobile(HttpServletRequest request) {
			return request.getParameter(mobileParameter);
		}

		/**
		 * Provided so that subclasses may configure what is put into the authentication
		 * request's details property.
		 *
		 * @param request that an authentication request is being created for
		 * @param authRequest the authentication request object that should have its details
		 * set
		 */
		protected void setDetails(HttpServletRequest request,
				SmsAuthenticationTooken authRequest) {
			authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
		}

		/**
		 * Sets the parameter name which will be used to obtain the username from the login
		 * request.
		 *
		 * @param usernameParameter the parameter name. Defaults to "username".
		 */
		public void setMobileParameter(String mobileParameter) {
			Assert.hasText(mobileParameter, "Mobile parameter must not be empty or null");
			this.mobileParameter = mobileParameter;
		}

		
		/**
		 * Defines whether only HTTP POST requests will be allowed by this filter. If set to
		 * true, and an authentication request is received which is not a POST request, an
		 * exception will be raised immediately and authentication will not be attempted. The
		 * <tt>unsuccessfulAuthentication()</tt> method will be called as if handling a failed
		 * authentication.
		 * <p>
		 * Defaults to <tt>true</tt> but may be overridden by subclasses.
		 */
		public void setPostOnly(boolean postOnly) {
			this.postOnly = postOnly;
		}

		public final String getMobileParameter() {
			return mobileParameter;
		}

}
