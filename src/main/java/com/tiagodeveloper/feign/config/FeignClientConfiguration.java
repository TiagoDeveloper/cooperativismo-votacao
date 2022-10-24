package com.tiagodeveloper.feign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tiagodeveloper.feign.client.UserClient;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

@Configuration
public class FeignClientConfiguration {

	@Value("${usuario.client.url}")
	private String url;

    @Bean
    public UserClient usuarioClient() {
        return  Feign.builder()
        		.contract(new SpringMvcContract())
        		.decoder(new GsonDecoder())
				.encoder(new GsonEncoder())
				.target(UserClient.class, url);
    }
}
