package com.yunbaek.springreactive.webclient;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WebClientController {

	@GetMapping("/call-rest-template")
	public List<String> getTweetsBlocking() {
		final String slowServiceUri = "http://localhost:8080/slow-api";
		URI uri = URI.create(slowServiceUri);
		log.info("Starting BLOCKING Controller!");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<String>> response = restTemplate.exchange(
			uri,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<>() {
			});

		List<String> result = response.getBody();
		result.forEach(log::info);
		log.info("Exiting BLOCKING Controller!");
		return result;
	}
}
