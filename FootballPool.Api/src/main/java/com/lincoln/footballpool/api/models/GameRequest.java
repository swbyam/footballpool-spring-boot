package com.lincoln.footballpool.api.models;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;

/**
 * Class represents a Request object containing information needed to either
 * create a new or update an existing {@link Game} domain instance when issued
 * via a call to a REST-based web service.
 * <p>
 * Request classes are created so that the internal implementation of
 * corresponding domain objects are not exposed to consumers of REST-based web
 * services.
 *
 */
public class GameRequest {

	// Member
	// Variables-------------------------------------------------------------

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

	// Constructors-------------------------------------------------------------

	/**
	 * Initializes a new instance of the {@code GameRequest} class.
	 * 
	 * @param homeTeamCity
	 *            city of the home team
	 * @param homeTeamName
	 *            name of the home team
	 * @param visitingTeamCity
	 *            city of the visiting team
	 * @param visitingTeamName
	 *            name of the visiting team
	 * @param favoriteTeamCity
	 *            city of the team favored to win the game
	 * @param favoriteTeamName
	 *            name of the team favored to win the game
	 * @param startDateTime
	 *            date and start time of the game
	 * @param weekNumber
	 *            week number in which the game is played
	 * @param line
	 *            betting line of the game
	 * @param overUnder
	 *            Over/Under of the game
	 */
	public GameRequest(final String homeTeamCity, final String homeTeamName, final String visitingTeamCity,
			final String visitingTeamName, final String favoriteTeamCity, final String favoriteTeamName,
			final LocalDateTime startDateTime, final int weekNumber, final double line, final double overUnder) {

		if (StringUtils.isBlank(homeTeamCity)) {
			throw new IllegalArgumentException("homeTeamCity cannot be null or empty string.");
		}
		if (StringUtils.isBlank(homeTeamName)) {
			throw new IllegalArgumentException("homeTeamName cannot be null or empty string.");
		}
		if (StringUtils.isBlank(visitingTeamCity)) {
			throw new IllegalArgumentException("visitingTeamCity cannot be null or empty string.");
		}
		if (StringUtils.isBlank(visitingTeamName)) {
			throw new IllegalArgumentException("visitingTeamName cannot be null or empty string.");
		}
		if (StringUtils.isBlank(favoriteTeamCity)) {
			throw new IllegalArgumentException("favoriteTeamCity cannot be null or empty string.");
		}
		if (StringUtils.isBlank(favoriteTeamName)) {
			throw new IllegalArgumentException("favoriteTeamName cannot be null or empty string.");
		}

		this.homeTeamCity = homeTeamCity;
		this.homeTeamName = homeTeamName;
		this.visitingTeamCity = visitingTeamCity;
		this.visitingTeamName = visitingTeamName;
		this.favoriteTeamCity = favoriteTeamCity;
		this.favoriteTeamName = favoriteTeamName;
		this.startDateTime = startDateTime;
		this.weekNumber = weekNumber;
		this.line = line;
		this.overUnder = overUnder;
	}

	/**
	 * Default constructor for the {@code GameRequest} class.
	 */
	public GameRequest() {
	}

	// Getters/Setter-------------------------------------------------------------

	/**
	 * Gets the city of the home team.
	 * 
	 * @return city of the home team
	 */
	public String getHomeTeamCity() {
		return homeTeamCity;
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
	 * Gets the city of the visiting team.
	 * 
	 * @return city of the visiting team
	 */
	public String getVisitingTeamCity() {
		return visitingTeamCity;
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
	 * Gets the city of the favorite team.
	 * 
	 * @return city of the favorite team
	 */
	public String getFavoriteTeamCity() {
		return favoriteTeamCity;
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
	 * Gets the date and start time of the game.
	 * 
	 * @return date and start time of the game
	 */
	public LocalDateTime getStartDateTime() {
		return startDateTime;
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
	 * Gets the line of the game.
	 * 
	 * @return line of the game
	 */
	public double getLine() {
		return line;
	}

	/**
	 * Gets the Over/Under of the game.
	 * 
	 * @return Over/Under of the game
	 */
	public double getOverUnder() {
		return overUnder;
	}
}
