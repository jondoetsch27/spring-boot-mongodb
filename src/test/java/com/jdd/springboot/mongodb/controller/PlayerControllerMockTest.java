package com.jdd.springboot.mongodb.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jdd.springboot.mongodb.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(controllers = PlayerController.class)
public class PlayerControllerMockTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private PlayerServiceImpl playerService;

  @Test
  public void readPlayersTest() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/players/read-all").contentType("application/json"))
        .andExpect(status().isOk());
  }
}
