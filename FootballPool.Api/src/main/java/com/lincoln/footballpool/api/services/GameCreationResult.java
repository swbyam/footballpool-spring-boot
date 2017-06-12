package com.lincoln.footballpool.api.services;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import com.lincoln.footballpool.domain.entities.Game;

/**
 * Class represents an attempt to create a {@link Game} domain instance from a
 * {@link GameRequest}.
 * <p>
 *  An instance of this class is received from a REST-based web service calls that creates
 *  or deletes game instances from the persistence store.
 */
public class GameCreationResult {

	//Member Variables-------------------------------------------------------------
	
	/**
	 * Game domain instance that was created from a Game request instance.
	 * <p>
	 * If this is null, it meant that creation of the instance failed due to one or more
	 * failed validation reasons.
	 */
	private Game createdGame;
	
	/**
	 * List of validation issues that occurred when attempting to create a Game domain instance.
	 */
	private final Collection<String> validationIssues;
	
	//Constructors-------------------------------------------------------------
	
	/**
	 * Initializes a new instance of the {@code GameCreationResult} class.
	 * 
	 * @param createdGame Game domain instance that was created from a Game request instance
	 * @param validationIssues list of validation issues that occurred when attempted to 
	 * create a Game domain instance
	 */
	public GameCreationResult(final Game createdGame, final Collection<String> validationIssues) {
		
		if (createdGame == null) {
			throw new NullPointerException("createdGame cannot be null");
		}
		
		if (validationIssues == null) {
			throw new NullPointerException("validationIssues cannot be null");
		}
		
		this.createdGame = createdGame;
		this.validationIssues = validationIssues;
	}
	
	/**
	 * Default constructor for the {code GameCreationResult} class.
	 */
	public GameCreationResult() {
		
		this.createdGame = null;
		this.validationIssues = new ArrayList<String>();
	}
	
	//Getters/Setters-------------------------------------------------------------
	
	/**
	 * Gets the domain {@code Game} instance that was created from its corresponding request instance.
	 * 
	 * @return created Game domain instance
	 */
	public final Game getCreatedGame() {
		return createdGame;
	}
	
	/**
	 * Sets the domain {@code Game} instance that was created from its corresponding request instance.
	 * 
	 * @param createdGame created Game domain instance
	 */
	public final void setCreatedGame(final Game createdGame) {
		
		if (createdGame == null) {
			throw new NullPointerException("createdGame cannot be null");
		}
		
		this.createdGame = createdGame;
	}

	/**
	 * Gets a list of validation issues that occurred when attempting to create a Game domain instance
	 * 
	 * @return list of validation issues
	 */
	public Collection<String> getValidationIssues() {
		return validationIssues;
	}
	
	/**
	 * Returns a summary or listing of all validation issues associated with the game creation result instance.
	 * 
	 * @return summary of all validation issues; if there are none, an empty string is returned
	 */
	public String getValidationIssueSummary() {
		
		StringBuilder summary = new StringBuilder();
		summary.append("Game cannot be created for the following reasons:\n");
		
		this.validationIssues.stream().forEach(issue -> summary.append(issue).append("\n"));
		
		return summary.toString();
	}
	
	//Public Methods-------------------------------------------------------------
	
	/**
	 * Adds a validation issues to the list of issues associated with the game creation result.
	 * 
	 * @param validationIssue validation issue to add
	 */
	public void addValidationIssue(String validationIssue) {
		
		 if (StringUtils.isBlank(validationIssue)) {
	            throw new IllegalArgumentException("validationIssue cannot be null or empty string.");
	        }
		 
		 this.validationIssues.add(validationIssue);
	}
	
	/**
	 * Returns a value indicating whether or not there were validation issues associated with
	 * creating the Game domain instance.
	 * 
	 * @return true if there were one or more issues; otherwise, false
	 */
	public boolean hasValidationIssues() {
		
		return this.validationIssues.size() > 0;
	}
}
