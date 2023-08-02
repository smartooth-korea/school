package co.smartooth.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * 작성자 : 정주현 
 * 작성일 : 2022. 07. 18
 * 수정일 : 2023. 08. 02
 * 서버분리 : 2023. 08. 01
 * 기능 : Resources Path Mapping
 * */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/static/")
        .setCachePeriod(60*60*24);
	
		registry.addResourceHandler("/test/**")
		.addResourceLocations("classpath:/static/")
          .setCachePeriod(60*60*24);
		
	}

}
