/**
 * 
 */
package com.mysecurity.core.validate.code.image;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;


import com.mysecurity.core.validate.code.Impl.AbstractValidateCodeProcessor;





/**
 * ͼƬ��֤�봦����
 * 
 * @author Liziyue
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

	/**
	 * ����ͼ����֤�룬����д����Ӧ��
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}

}