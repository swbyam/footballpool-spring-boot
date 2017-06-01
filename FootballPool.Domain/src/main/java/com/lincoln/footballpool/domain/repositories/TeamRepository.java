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
}
