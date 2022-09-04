package br.com.gomes.movie.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		
		return restTemplateBuilder.setConnectTimeout(Duration.ofMillis(500000)).setReadTimeout(Duration.ofMillis(500000)).build();
		
	}

}
