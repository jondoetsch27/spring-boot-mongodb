package com.jdd.springboot.mongodb.service;

import com.jdd.springboot.mongodb.model.Player;

public interface PlayerService {

    public Iterable<Player> listPlayers();
    public Player createPlayer(Player player);
    public Player readPlayer(String playerId);
    public Player updatePlayer(Player player);
    public Player deletePlayer(Player player);

}
