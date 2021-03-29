package com.jdd.springboot.mongodb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdd.springboot.mongodb.model.Player;
import com.jdd.springboot.mongodb.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean private PlayerServiceImpl playerService;

  private static final String APPLICATION_JSON = "application/json";
  private static final String DELETE_URL = "/players/delete";
  private static final String GET_URL = "/players/read";
  private static final String GET_ALL_URL = "/players/read-all";
  private static final String PLAYER_ID_URL = "/?playerId=";
  private static final String POST_URL = "/players/add";
  private static final String PUT_URL = "/players/update";

  @Test
  public void readPlayersTest() throws Exception {
    mockMvc.perform(get(GET_ALL_URL).contentType(APPLICATION_JSON)).andExpect(status().isOk());
  }

  @Test
  public void readPlayerTest() throws Exception {
    mockMvc
        .perform(get(GET_URL + PLAYER_ID_URL + "MatthewStafford9").contentType(APPLICATION_JSON))
        .andExpect(status().isFound());
  }

  @Test
  public void createPlayerTest() throws Exception {
    Player player = new Player("RussellWilson3", "RussellWilson", "3");
    mockMvc
        .perform(
            post(POST_URL)
                .content(objectMapper.writeValueAsString(player))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void updatePlayerTest() throws Exception {
    Player player = new Player("RussellWilson3", "RussWilson", "3");
    mockMvc
        .perform(
            put(PUT_URL)
                .content(objectMapper.writeValueAsString(player))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }

  @Test
  public void deletePlayerTest() throws Exception {
    Player player = new Player("RussellWilson3", "RussWilson", "3");
    mockMvc
        .perform(
            delete(DELETE_URL)
                .content(objectMapper.writeValueAsString(player))
                .contentType(APPLICATION_JSON))
        .andExpect(status().isAccepted());
  }
}
