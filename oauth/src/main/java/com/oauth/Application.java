package com.oauth;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		return modelMapper;
	}
	
	@Bean
	public TokenStore tokenStore(RedisConnectionFactory redisConnectionFactory) {
	    return new RedisTokenStore(redisConnectionFactory);
	}

}
