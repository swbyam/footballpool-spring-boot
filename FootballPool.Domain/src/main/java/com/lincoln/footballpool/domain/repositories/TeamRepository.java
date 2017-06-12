/*
 * File: TeamRepository.java
 * Package: com.lincoln.footballpool.domain.repositories
 * Copyright (c) 2017 Optum, All rights reserved.
 * This software, accompanying configuration files and documentation
 * is confidential and the intellectual property of Optum.
 */
package com.lincoln.footballpool.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lincoln.footballpool.domain.entities.Team;

/**
 * Abstraction for Team Repository that handles basic CRUD operations consistent with the
 * Repository pattern regarding {@link Team} entity instances.
 */
public interface TeamRepository extends CrudRepository<Team, Integer> {

	//Methods-------------------------------------------------------------
	
	/**
	 * Retrieves a team from the persistence store that is from the supplied city.
	 * 
	 * @param city city from which the team belongs
	 * 
	 * @return team belonging to the specified city
	 */
	Team getTeamByCity(String city);
	
	/**
	 * Retrieves a team from the persistence store with the supplied team name.
	 * 
	 * @param teamName name of the team
	 * 
	 * @return team with the specified team name
	 */
	Team getTeamByTeamName(String teamName);
	
	/**
	 * Determines whether or not a team with the supplied name exists in the persistence store.
	 * 
	 * @param teamName name of the team to check for
	 * 
	 * @return true if the team exists; otherwise false
	 */
	//boolean exists(String teamName);
}
