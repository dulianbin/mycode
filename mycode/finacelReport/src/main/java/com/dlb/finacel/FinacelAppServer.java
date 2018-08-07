package com.dlb.finacel;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FinacelAppServer extends SpringBootServletInitializer{
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(FinacelAppServer.class).web(true).run(args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(FinacelAppServer.class);
	}
}
