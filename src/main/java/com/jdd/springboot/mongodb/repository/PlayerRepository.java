package com.jdd.springboot.mongodb.repository;

import com.jdd.springboot.mongodb.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {}
