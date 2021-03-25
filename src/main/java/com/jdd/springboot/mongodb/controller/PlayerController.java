package com.jdd.springboot.mongodb.controller;

import com.jdd.springboot.mongodb.model.Player;
import com.jdd.springboot.mongodb.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

  @Autowired private PlayerServiceImpl playerServiceImpl;

  @RequestMapping(path = "/players/read")
  public ResponseEntity<Player> readPlayer(@RequestParam String playerId) {
    try {
      return new ResponseEntity<>(playerServiceImpl.readPlayer(playerId), HttpStatus.FOUND);
    } catch (RuntimeException runtimeException) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @RequestMapping(path = "/players/read-all")
  public ResponseEntity<Iterable> readPlayers() {
    try {
      return new ResponseEntity<>(playerServiceImpl.listPlayers(), HttpStatus.OK);
    } catch (RuntimeException runtimeException) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "/players/add", consumes = "application/json")
  public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
    try {
      return new ResponseEntity<>(playerServiceImpl.createPlayer(player), HttpStatus.CREATED);
    } catch (RuntimeException runtimeException) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(path = "/players/update", consumes = "application/json")
  public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
    try {
      return new ResponseEntity<>(playerServiceImpl.updatePlayer(player), HttpStatus.OK);
    } catch (RuntimeException runtimeException) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(path = "/players/delete")
  public ResponseEntity<Player> deletePlayer(@RequestBody Player player) {
    try {
      return (new ResponseEntity<>(playerServiceImpl.deletePlayer(player), HttpStatus.OK));
    } catch (Exception exception) {
      return (new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
  }
}
