package co.smartooth.app.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import java.nio.charset.StandardCharsets;

/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 04. 28
 * 서버분리 : 2023. 08. 01
 */
@Configuration
public class PropertyConfig {

	@Bean
	public PropertiesFactoryBean encoding() throws Exception {

		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		ClassPathResource classPathResource = new ClassPathResource("application.yml");
		propertiesFactoryBean.setLocation(classPathResource);
		propertiesFactoryBean.setFileEncoding(StandardCharsets.UTF_8.toString());

		return propertiesFactoryBean;

	}
}
