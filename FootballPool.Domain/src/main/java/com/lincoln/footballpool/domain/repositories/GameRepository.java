/*
 * File: GameRepository.java
 * Package: com.lincoln.footballpool.domain.repositories
 * Copyright (c) 2017 Optum, All rights reserved.
 * This software, accompanying configuration files and documentation
 * is confidential and the intellectual property of Optum.
 */
package com.lincoln.footballpool.domain.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.lincoln.footballpool.domain.entities.Game;

/**
 * Abstraction for Game Repository that handles basic CRUD operations consistent
 * with the Repository pattern regarding {@link Game} entity instances.
 */
public interface GameRepository extends CrudRepository<Game, Integer>  {

	//Methods-------------------------------------------------------------
	
	/**
	 * Retrieves all games from the persistence store that take place during the supplied week number.
	 * 
	 * @param weekNumber week number during which games are to be retrieved
	 * 
	 * @return list of games taking place for the supplied week number.
	 */
	Collection<Game> getGamesByWeekNumber(int weekNumber);
}
