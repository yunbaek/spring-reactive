package com.yunbaek.springreactive.webclient;

import java.net.URI;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class WebClientController {
	private final URI slowApiUri = URI.create("http://localhost:8080/slow-api");

	@GetMapping("/call-rest-template")
	public List<String> getTweetsBlocking() {
		log.info("Starting BLOCKING Controller!");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<String>> response = restTemplate.exchange(
			slowApiUri,
			HttpMethod.GET,
			null,
			new ParameterizedTypeReference<>() {
			});

		List<String> result = response.getBody();
		result.forEach(log::info);
		log.info("Exiting BLOCKING Controller!");
		return result;
	}

	@GetMapping("/call-web-client")
	public Flux<String> getTweetsNonBlocking() {
		log.info("Starting NON-BLOCKING Controller!");

		Flux<String> response = WebClient.create()
			.get()
			.uri(slowApiUri)
			.retrieve()
			.bodyToFlux(String.class)
			.doOnNext(log::info);

		log.info("Exiting NON-BLOCKING Controller!");
		return response;
	}
}
