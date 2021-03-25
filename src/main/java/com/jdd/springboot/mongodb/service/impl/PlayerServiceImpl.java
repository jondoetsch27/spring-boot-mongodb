package com.jdd.springboot.mongodb.service.impl;

import com.jdd.springboot.mongodb.model.Player;
import com.jdd.springboot.mongodb.repository.PlayerRepository;
import com.jdd.springboot.mongodb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerServiceImpl implements PlayerService {

  @Autowired private PlayerRepository playerRepository;

  @Override
  public Iterable<Player> listPlayers() {
    try {
      return (playerRepository.findAll());
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public Player readPlayer(String playerId) {
    try {
      return (playerRepository.findById(playerId)).get();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public Player createPlayer(Player player) {
    try {
      return (playerRepository.insert(player));
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }

  @Override
  public Player updatePlayer(Player player) {
    Player oldPlayer = playerRepository.findById(player.getPlayerId()).get();
    Player.Builder playerBuilder = new Player.Builder(oldPlayer);
    Player newPlayer =
        playerBuilder
            .playerId(player.getPlayerId())
            .playerName(player.getPlayerName())
            .playerNumber(player.getPlayerNumber())
            .build();
    playerRepository.deleteById(oldPlayer.getPlayerId());
    return playerRepository.save(newPlayer);
  }

  @Override
  public Player deletePlayer(Player player) {
    try {
      playerRepository.deleteById(player.getPlayerId());
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
    return player;
  }
}
