/**
 * 
 */
package com.mysecurity.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.mysecurity.core.validate.code.ValidateCode;

/**
 * @author Liziyue
 * 图片验证码 继承了短信验证码
 */
public class ImageCode extends ValidateCode {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6905318287291654498L;
	
	private BufferedImage image; 
	
	public ImageCode(BufferedImage image, String code, int expireIn){
		super(code, expireIn);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime){
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
