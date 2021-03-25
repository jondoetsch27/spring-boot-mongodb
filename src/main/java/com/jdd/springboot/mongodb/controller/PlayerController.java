package com.jdd.springboot.mongodb.controller;

import com.jdd.springboot.mongodb.model.Player;
import com.jdd.springboot.mongodb.service.impl.PlayerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerController {

  private PlayerServiceImpl playerServiceImpl;

  Logger logger = LoggerFactory.getLogger(PlayerController.class);

  public PlayerController(PlayerServiceImpl playerServiceImpl) {
    this.playerServiceImpl = playerServiceImpl;
  }

  @GetMapping(path = "/players/read")
  public ResponseEntity<Player> readPlayer(@RequestParam String playerId) {
    Player player;
    ResponseEntity<Player> playerResponseEntity;
    logger.debug("Received GET request at /players/read/ for playerId: " + playerId);
    try {
      logger.debug("Initating MongoDB process: findById");
      player = playerServiceImpl.readPlayer(playerId);
      logger.debug("MongoDB findById process completed successfully");
      playerResponseEntity = new ResponseEntity<>(player, HttpStatus.FOUND);
    } catch (RuntimeException runtimeException) {
      logger.error("MongoDB findById process failed with exception: " + runtimeException);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

  @GetMapping(path = "/players/read-all")
  public ResponseEntity<Iterable> readPlayers() {
    Iterable<Player> playerIterable;
    ResponseEntity<Iterable> iterableResponseEntity;
    logger.debug("Received GET request at /players/read-all");
    try {
      logger.debug("Initiating MongoDB process: findAll");
      playerIterable = playerServiceImpl.listPlayers();
      logger.debug("MongoDB findAll process completed successfully");
      iterableResponseEntity = new ResponseEntity<>(playerIterable, HttpStatus.OK);
    } catch (RuntimeException runtimeException) {
      logger.error("MongoDB findAll process failed with exception: " + runtimeException);
      iterableResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return iterableResponseEntity;
  }

  @PostMapping(path = "/players/add", consumes = "application/json")
  public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
    Player newPlayer;
    ResponseEntity<Player> playerResponseEntity;
    logger.debug("Received POST request at /players/add for Player: " + player.toString());
    try {
      logger.debug("Initiating MongoDB process: insert");
      newPlayer = playerServiceImpl.createPlayer(player);
      logger.debug("MongoDB insert process completed successfully");
      playerResponseEntity = new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
    } catch (RuntimeException runtimeException) {
      logger.error("MongoDB insert process failed with exception: " + runtimeException);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

  @PutMapping(path = "/players/update", consumes = "application/json")
  public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
    Player newPlayer;
    ResponseEntity<Player> playerResponseEntity;
    logger.debug("Received PUT request at /players/update for Player: " + player.toString());
    try {
      logger.debug("Initiating MongoDB process: save");
      newPlayer = playerServiceImpl.updatePlayer(player);
      logger.debug("MongoDB save process completed successfully");
      playerResponseEntity =
          new ResponseEntity<>(playerServiceImpl.updatePlayer(player), HttpStatus.ACCEPTED);
    } catch (RuntimeException runtimeException) {
      logger.error("MongoDB save process failed with exception: " + runtimeException);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }

  @DeleteMapping(path = "/players/delete")
  public ResponseEntity<Player> deletePlayer(@RequestBody Player player) {
    Player oldPlayer;
    ResponseEntity<Player> playerResponseEntity;
    logger.debug("Received DELETE request at /players/delete for Player: " + player.toString());
    try {
      logger.debug("Initiating MongoDB process: deleteById");
      oldPlayer = playerServiceImpl.deletePlayer(player);
      logger.debug("MongoDB deleteById process completed successfully");
      playerResponseEntity =
          new ResponseEntity<>(playerServiceImpl.deletePlayer(player), HttpStatus.ACCEPTED);
    } catch (RuntimeException runtimeException) {
      logger.debug("MongoDB deleteById process failed with exception: " + runtimeException);
      playerResponseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return playerResponseEntity;
  }
}
