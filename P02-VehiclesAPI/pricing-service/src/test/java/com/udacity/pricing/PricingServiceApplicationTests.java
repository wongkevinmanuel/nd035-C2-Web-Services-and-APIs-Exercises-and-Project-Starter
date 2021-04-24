package com.udacity.pricing;

import com.udacity.pricing.domain.price.Price;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.net.URISyntaxException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PricingServiceApplicationTests {

	@LocalServerPort
	private int puerto;

	@Autowired
	private TestRestTemplate restTem;

	@Test
	public void contextLoads() {
	}

	@Test
	public void obtenerPrecio() throws Exception {
		String idPrecio="1";
		URI uri = new URI("/prices/"+idPrecio);
		ResponseEntity<Price> respuesta = restTem.getForEntity("http://localhost:"+ puerto +uri, Price.class);
		assertThat(respuesta.getStatusCode(),equalTo(HttpStatus.OK));
	}
}
