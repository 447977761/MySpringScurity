package com.mysecurity.core;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import com.mysecurity.core.properties.SecurityProperties;

/**
 * 
 * @author Liziyue
 *
 */
//让SecurityProperties生效
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
	
}
