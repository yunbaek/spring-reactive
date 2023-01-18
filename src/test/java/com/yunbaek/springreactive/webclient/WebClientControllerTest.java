package com.yunbaek.springreactive.webclient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@WebAppConfiguration
@WebMvcTest
class WebClientControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@DisplayName("RestTemplate 을 이용한 호출은 blocking 으로 동작")
	@Test
	void restTemplateApiCallTest() throws Exception {
		mockMvc.perform(get("/call-rest-template"))
			.andExpect(status().isOk());
	}

	@DisplayName("WebClient 를 이용한 호출은 non-blocking 으로 동작")
	@Test
	void webClientApiCallTest() throws Exception {
		mockMvc.perform(get("/call-web-client"))
			.andExpect(status().isOk());
	}
}
