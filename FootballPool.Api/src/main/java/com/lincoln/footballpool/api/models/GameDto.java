/**
 * 
 */
package com.lincoln.footballpool.api.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author sbyam101
 *
 */
public class GameDto extends ResourceSupport {

	//Member Variables-------------------------------------------------------------
	
	/**
	 * City of the home team.
	 */
	private String homeTeamCity;
	
	/**
	 * Name of the home team.
	 */
	private String homeTeamName;
	
	/**
	 * City of the visiting team.
	 */
	private String visitingTeamCity;
	
	/**
	 * Name of the visiting team.
	 */
	private String visitingTeamName;
	
	/**
	 * City of the team favored to win the game.
	 */
	private String favoriteTeamCity;
	
	/**
	 * Name of the team favored to win the game.
	 */
	private String favoriteTeamName;
	
	/**
	 * The date and start time of the game.
	 */
	private LocalDateTime startDateTime; 

	/**
	 * Week number in which the game is played.
	 */
	private int weekNumber;
	
	/**
	 * The betting line of the game.
	 */
	private double line;
	
	/**
	 * Over/Under of the game.
	 */
	private double overUnder;
	
	//Getters/Setter-------------------------------------------------------------

	/**
	 * Gets the city of the home team.
	 * 
	 * @return city of the home team
	 */
	public String getHomeTeamCity() {
		return homeTeamCity;
	}

	/**
	 * Sets the city of the home team.
	 * 
	 * @param homeTeamCity city of the home team
	 */
	public void setHomeTeamCity(String homeTeamCity) {
		this.homeTeamCity = homeTeamCity;
	}

	/**
	 * Gets the name of the home team.
	 * 
	 * @return name of the home team
	 */
	public String getHomeTeamName() {
		return homeTeamName;
	}

	/**
	 * Sets the name of the home team.
	 * 
	 * @param homeTeamName name of the home team
	 */
	public void setHomeTeamName(String homeTeamName) {
		this.homeTeamName = homeTeamName;
	}

	/**
	 * Gets the city of the visiting team.
	 * 
	 * @return city of the visiting team
	 */
	public String getVisitingTeamCity() {
		return visitingTeamCity;
	}

	/**
	 * Sets the city of the visiting team.
	 * 
	 * @param visitingTeamCity city of the visiting team
	 */
	public void setVisitingTeamCity(String visitingTeamCity) {
		this.visitingTeamCity = visitingTeamCity;
	}

	/**
	 * Gets the name of the visiting team.
	 * 
	 * @return name of the visiting team
	 */
	public String getVisitingTeamName() {
		return visitingTeamName;
	}

	/**
	 * Sets the name of the visiting team.
	 * 
	 * @param visitingTeamName name of the visiting team
	 */
	public void setVisitingTeamName(String visitingTeamName) {
		this.visitingTeamName = visitingTeamName;
	}

	/**
	 * Gets the city of the favorite team.
	 * 
	 * @return city of the favorite team
	 */
	public String getFavoriteTeamCity() {
		return favoriteTeamCity;
	}

	/**
	 * Sets the city of the favorite team.
	 * 
	 * @param favoriteTeamCity city of the favorite team
	 */
	public void setFavoriteTeamCity(String favoriteTeamCity) {
		this.favoriteTeamCity = favoriteTeamCity;
	}

	/**
	 * Gets the name of the favorite team.
	 * 
	 * @return name of the favorite team
	 */
	public String getFavoriteTeamName() {
		return favoriteTeamName;
	}

	/**
	 * Sets the name of the favorite team.
	 * 
	 * @param favoriteTeamName name of the favorite team
	 */
	public void setFavoriteTeamName(String favoriteTeamName) {
		this.favoriteTeamName = favoriteTeamName;
	}

	/**
	 * Gets the date and start time of the game.
	 * 
	 * @return date and start time of the game
	 */
	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	/**
	 * Sets the date and start time of the game.
	 * 
	 * @param startDateTime date and start time of the game
	 */
	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	/**
	 * Gets the week number that the game is played.
	 * 
	 * @return week number that the game is played
	 */
	public int getWeekNumber() {
		return weekNumber;
	}

	/**
	 * Sets the week number that the game is played.
	 * 
	 * @param weekNumber week number that the game is played
	 */
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}

	/**
	 * Gets the line of the game.
	 * 
	 * @return line of the game
	 */
	public double getLine() {
		return line;
	}

	/**
	 * Sets the line of the game
	 * 
	 * @param line line of the game
	 */
	public void setLine(double line) {
		this.line = line;
	}

	/**
	 * Gets the Over/Under of the game.
	 * 
	 * @return Over/Under of the game
	 */
	public double getOverUnder() {
		return overUnder;
	}

	/**
	 * Sets the Over/Under of the game.
	 * 
	 * @param overUnder Over/Under of the game
	 */
	public void setOverUnder(double overUnder) {
		this.overUnder = overUnder;
	}
}
