/**
 * 
 */
package com.mysecurity.core.validate.code.sms;

/**
 * @author Liziyue
 *
 */
public interface SmsCodeSender {
	
	void send(String mobile, String code);

}
