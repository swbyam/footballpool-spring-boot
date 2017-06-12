/**
 * 
 */
package com.lincoln.footballpool.api.services;

import org.springframework.stereotype.Service;

import com.lincoln.footballpool.api.models.GameRequest;
import com.lincoln.footballpool.domain.entities.Game;

/**
 * Abstraction for {@link GameService} interface that defines operations related to {@link Game} domain
 * instances that are relevant when they are used in REST-based web services.
 *<p>
 * Such operations include creating domain {@code Game} instances from their corresponding {@link GameRequest}
 * counterparts as well as executing validation rules against request instances.
 */
public interface GameService {

	//Methods-------------------------------------------------------------
	
	/**
	 * Creates a {@code Game} domain instance from the supplied {@code GameRequest} instance.
	 * <p>
	 * A {@code GameCreationResult} instance is returned that contains the created Game instance
	 * along with a set of validation issues that may have occurred.
	 * 
	 * @param gameRequest request instance from which a Game instance is to be created
	 * 
	 * @return game creation result instance
	 */
	GameCreationResult createGameFromRequest(final GameRequest gameRequest);
	
	/**
	 * Executes a series of validation rules against the supplied game request instance and returns 
	 * them as a string.
	 * 
	 * @param gameRequest game request upon which validation is to take place
	 * 
	 * @return string containing list of validation issues; if there are no issues, an empty string is returned
	 * 
	 */
	//String getValidationIssuesForRequest(GameRequest gameRequest);
}
