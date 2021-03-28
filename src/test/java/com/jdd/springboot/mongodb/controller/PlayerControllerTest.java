package com.jdd.springboot.mongodb.controller;

import com.jdd.springboot.mongodb.model.Player;
import com.jdd.springboot.mongodb.model.Player.Builder;
import java.net.URL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

  @LocalServerPort private int port;

  @Autowired private TestRestTemplate restTemplate;

  private static final String GET_URL = "/players/read";
  private static final String GET_ALL_URL = "/players/read-all";
  private static final String DELETE_URL = "/players/delete";
  private static final String PLAYER_ID_URL = "/?playerId=";
  private static final String POST_URL = "/players/add";
  private static final String PUT_URL = "/players/update";

  @Test
  public void readPlayersTest() throws Exception {
    ResponseEntity<Iterable> iterableResponseEntity =
        restTemplate.getForEntity(
            new URL("http://localhost:" + port + GET_ALL_URL).toString(), Iterable.class);
    assertEquals(HttpStatus.OK, iterableResponseEntity.getStatusCode());
  }

  @Test
  public void readPlayerTest() throws Exception {
    ResponseEntity<Player> playerResponseEntity =
        restTemplate.getForEntity(
            new URL("http://localhost:" + port + GET_URL + PLAYER_ID_URL + "MatthewStafford9")
                .toString(),
            Player.class);
    assertEquals(HttpStatus.FOUND, playerResponseEntity.getStatusCode());
    assertThat(playerResponseEntity.getBody().getPlayerId().equals("MatthewStafford9"));
  }

  @Test
  public void createPlayerTest() throws Exception {
    Player testPlayer = new Player("TerryMcLaurin17", "TerryMcLaurin", "17");
    ResponseEntity<Player> playerResponseEntity =
        restTemplate.postForEntity(
            new URL("http://localhost:" + port + POST_URL).toString(), testPlayer, Player.class);
    assertEquals(HttpStatus.CREATED, playerResponseEntity.getStatusCode());
    assertThat(playerResponseEntity.getBody().getPlayerId().equals("TerryMcLaurin17"));
  }

  @Test
  public void updatePlayerTest() throws Exception {}

  @Test
  public void deletePlayerTest() throws Exception {}
}
