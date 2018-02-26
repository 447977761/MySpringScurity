/**
 * 
 */
package com.mysecurity.core.properties;

import com.mysecurity.core.properties.ImageCodeProperties;
import com.mysecurity.core.properties.SmsCodeProperties;

/**
 * @author Liziyue
 *
 */
public class ValidateCodeProperties {
	
	private ImageCodeProperties image = new ImageCodeProperties();
	
	private SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image; 
	}

	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}

	public SmsCodeProperties getSms() {
		return sms;
	}

	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}
	
}
