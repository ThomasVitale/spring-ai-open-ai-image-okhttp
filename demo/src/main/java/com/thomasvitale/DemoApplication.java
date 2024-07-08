package com.thomasvitale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.ClientHttpRequestFactories;
import org.springframework.boot.web.client.ClientHttpRequestFactorySettings;
import org.springframework.boot.web.client.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

import java.time.Duration;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

//	@Bean
//	RestClientCustomizer restClientCustomizer1() {
//		return builder -> builder.requestFactory(ClientHttpRequestFactories.get(SimpleClientHttpRequestFactory.class, ClientHttpRequestFactorySettings.DEFAULTS));
//	}

//	@Bean
//	RestClientCustomizer restClientCustomizer2() {
//		return restClientBuilder -> {
//			restClientBuilder
//					.requestFactory(new BufferingClientHttpRequestFactory(
//							ClientHttpRequestFactories.get(ClientHttpRequestFactorySettings.DEFAULTS
//									.withConnectTimeout(Duration.ofSeconds(10))
//									.withReadTimeout(Duration.ofSeconds(60)))));
//		};
//	}

}
