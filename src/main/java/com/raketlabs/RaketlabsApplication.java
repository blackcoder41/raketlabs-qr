package com.raketlabs;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@SpringBootApplication
public class RaketlabsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
	    
	    SpringApplication.run(RaketlabsApplication.class, args);
	    
	    System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
	    
	    Path currentRelativePath = Paths.get("");
	    String s = currentRelativePath.toAbsolutePath().toString();
	    System.out.println("Current relative path is: " + s);
	    
	}
	
	public static void testString (String str) {
	    System.out.println("String: " + str);
        System.out.println("Substring: " + str.substring(0, 2));
        System.out.println("Length: " + str.length());
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RaketlabsApplication.class);
	}
	
	@Bean
    public HttpMessageConverter<BufferedImage> createImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

}
