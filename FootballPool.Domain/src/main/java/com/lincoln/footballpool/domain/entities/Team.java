package com.lincoln.footballpool.domain.entities;

import javax.persistence.Entity;

import org.apache.commons.lang3.StringUtils;

@Entity
public class Team extends EntityBase {
	
	//Member Variables-------------------------------------------------------------

	/**
	 * The city where the team is from.
	 */
	private String city;
	
	/**
	 * The name of the team.
	 */
	private String teamName;
	
	//Constructors-------------------------------------------------------------

	/**
	 * Initializes a new instance of the {@code Team} class.
	 * 
	 * @param city city where the team is from
	 * @param teamName name of the team
	 */
	public Team(String city, String teamName) {
		
		if (StringUtils.isBlank(city)) {
			throw new IllegalArgumentException("city cannot be null or empty string.");
		}
		
		if (StringUtils.isBlank(teamName)) {
			throw new IllegalArgumentException("teamName cannot be null or empty string.");
		}
		
		this.city = city;
		this.teamName = teamName;
	}
	
	/**
	 * 
	 * Default constructor for the {@code Team} class.
	 */
	public Team() {
		
	}

	//Getters/Setter-------------------------------------------------------------
	
	/**
	 * Gets the city where the team is from.
	 * 
	 * @return name of the city where the team is from
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city where the team is from
	 * 
	 * @param city where the team is from
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the name of the team.
	 * 
	 * @return name of the team
	 */
	public String getTeamName() {
		return this.teamName;
	}

	/**
	 * Sets the name of the team.
	 * 
	 * @param teamName name of the team
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * Gets the full name of the team consisting of the team name qualified by its city.
	 * <p> 
	 * For example, the New England Patriots.
	 * 
	 * @return team name qualified with its city
	 */
	public String getFullTeamName() {
		return this.city + " " + this.teamName;
	}
	
	//Public Methods-------------------------------------------------------------
	
	/**
	 * {@code toString} method implementation.
	 * <p>
	 * Calls the {@code getFullTeamName} method on the class.
	 */
	@Override
	public String toString() {
		return this.getFullTeamName();
	}
}
