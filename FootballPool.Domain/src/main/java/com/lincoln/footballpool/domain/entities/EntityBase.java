package com.lincoln.footballpool.domain.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntityBase {

	//Member Variables-------------------------------------------------------------
	
	/**
	 * The unique id of the entity.
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//TODO: Add member variable involved with versioning the entity.  
	//Research how this can be done with JPA!!
	
	//Getters/Setter-------------------------------------------------------------
	
	/**
	 * Gets the unique id of the entity.
	 * 
	 * @return unique id of the entity.
	 */
	public int getId() {
		return this.id;
	}
}
