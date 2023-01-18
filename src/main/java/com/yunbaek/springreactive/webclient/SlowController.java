package com.yunbaek.springreactive.webclient;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SlowController {

	@GetMapping("/slow-api")
	public List<String> slowApi() throws InterruptedException {
		Thread.sleep(2000);
		return List.of(
			"slow-api-1",
			"slow-api-2",
			"slow-api-3"
		);
	}

}
