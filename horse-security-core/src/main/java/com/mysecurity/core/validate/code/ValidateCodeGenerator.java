/**
 * 
 */
package com.mysecurity.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @author Liziyue
 *
 */
public interface ValidateCodeGenerator {

	ValidateCode generate(ServletWebRequest request);
	
}
