package com.mysecurity.core.properties;




public class BrowserProperties {	
	
	private SessionProperties session = new SessionProperties();
	
	private String singOutUrl = "/Mylogout.html";
	
	private String singUpUrl = "/MysignUp.html";
	
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	
	private LoginResponseType loginType = LoginResponseType.JSON;
	
	private int rememberMeSeconds = 3600;

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public LoginResponseType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginResponseType loginType) {
		this.loginType = loginType;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public String getSingUpUrl() {
		return singUpUrl;
	}

	public void setSingUpUrl(String singUpUrl) {
		this.singUpUrl = singUpUrl;
	}

	public SessionProperties getSession() {
		return session;
	}

	public void setSession(SessionProperties session) {
		this.session = session;
	}

	public String getSingOutUrl() {
		return singOutUrl;
	}

	public void setSingOutUrl(String singOutUrl) {
		this.singOutUrl = singOutUrl;
	}
	
	
	
	
}
