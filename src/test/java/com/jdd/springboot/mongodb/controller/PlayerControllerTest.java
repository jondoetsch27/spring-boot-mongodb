package com.jdd.springboot.mongodb.controller;

import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void readPlayersTest() throws Exception {

    ResponseEntity<Iterable> iterableResponseEntity =
        restTemplate.getForEntity(
            new URL("http://localhost:" + port + "/players/read-all").toString(), Iterable.class);
    assertEquals(HttpStatus.OK, iterableResponseEntity.getStatusCode());
  }
}
