package com.jdd.springboot.mongodb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "Players")
public class Player {

  @Id private String playerId;
  private String playerName;
  private String playerNumber;

  public Player() {}

  public Player(String playerId, String playerName, String playerNumber) {
    this.playerId = playerId;
    this.playerName = playerName;
    this.playerNumber = playerNumber;
  }

  public Player(Builder builder) {
    this.playerId = builder.playerId;
    this.playerName = builder.playerName;
    this.playerNumber = builder.playerNumber;
  }

  public static class Builder {

    private String playerId;
    private String playerName;
    private String playerNumber;

    public Builder(Player player) {
      this.playerId = player.getPlayerId();
      this.playerName = player.getPlayerName();
      this.playerNumber = player.getPlayerNumber();
    }

    public Builder playerId(String playerId) {
      this.playerId = playerId;
      return this;
    }

    public Builder playerName(String playerName) {
      this.playerName = playerName;
      return this;
    }

    public Builder playerNumber(String playerNumber) {
      this.playerNumber = playerNumber;
      return this;
    }

    public Player build() {
      return new Player(this);
    }
  }

  public String getPlayerId() {
    return playerId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public String getPlayerNumber() {
    return playerNumber;
  }
}
