package com.ms.caseStudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableOAuth2Client
@EnableFeignClients
@EnableResourceServer
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrix
@EnableBinding(HelloBinding.class)
public class EcartPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcartPortalApplication.class, args);
	}

	/*
	 * @Bean public OAuth2RestTemplate oauth2RestTemplate() {
	 * ResourceOwnerPasswordResourceDetails resource = new
	 * ResourceOwnerPasswordResourceDetails();
	 * 
	 * resource.setAccessTokenUri("http://localhost:9999/oauth/token");
	 * resource.setId("microservice-test"); resource.setClientId("myclient");
	 * resource.setClientSecret("secret"); resource.setGrantType("password");
	 * resource.setScope(Arrays.asList("read"));
	 * 
	 * resource.setUsername("Santanu"); resource.setPassword("Paul");
	 * 
	 * OAuth2RestTemplate template = new OAuth2RestTemplate(resource);
	 * AccessTokenProvider accessTokenProvider = new
	 * AccessTokenProviderChain(Arrays.<AccessTokenProvider>asList( new
	 * ResourceOwnerPasswordAccessTokenProvider()));
	 * template.setAccessTokenProvider(accessTokenProvider);
	 * System.out.println("---------------------------starting---------------------"
	 * ); System.out.println("get token :::::"+template.getAccessToken()+"::");
	 * 
	 * return template; }
	 */
	
	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
	   return ServerCodecConfigurer.create();
	}
	
}
