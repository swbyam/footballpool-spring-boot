/**
 * 
 */
package com.lincoln.footballpool.api.services;

import org.springframework.stereotype.Service;

import com.lincoln.footballpool.api.models.GameRequest;
import com.lincoln.footballpool.domain.entities.Game;
import com.lincoln.footballpool.domain.entities.Team;
import com.lincoln.footballpool.domain.repositories.TeamRepository;

/**
 * Implementation of {@link GameService} interface that provides functionality
 * related to {@link Game} domain instances related to REST-based web services.
 * <p>
 * Such functionality includes creating domain {@code Game} instances from their
 * corresponding {@link GameRequest} counterparts as well as executing
 * validation rules against request instances.
 */
@Service
public class GameServiceImpl implements GameService {

	// Member
	// Variables-------------------------------------------------------------

	/**
	 * Repository that provides basic CRUD operations for Team instances.
	 */
	private final TeamRepository teamRepository;

	/**
	 * Message that indicates a team supplied in a request object does not exist
	 * in the persistence store.
	 */
	private static final String TEAMDOESNOTEXISTMSG = "A team with name \"%s\" does not exist.";

	// Constructors-------------------------------------------------------------

	/**
	 * Initializes a new instance of the {@link GameServiceImpl} class.
	 * 
	 * @param teamRepository
	 *            repository that provides basic CRUD operations for Team
	 *            instances
	 */
	public GameServiceImpl(final TeamRepository teamRepository) {

		if (teamRepository == null) {
			throw new NullPointerException("teamRepository cannot be null");
		}

		this.teamRepository = teamRepository;
	}

	// Public
	// Methods-------------------------------------------------------------

	/**
	 * Creates a {@code Game} domain instance from the supplied
	 * {@code GameRequest} instance.
	 * <p>
	 * A {@code GameCreationResult} instance is returned that contains the
	 * created Game instance along with a set of validation issues that may have
	 * occurred.
	 * 
	 * @param gameRequest
	 *            request instance from which a Game instance is to be created
	 * 
	 * @return game creation result instance
	 */
	public final GameCreationResult createGameFromRequest(final GameRequest gameRequest) {

		if (gameRequest == null) {
			throw new NullPointerException("gameRequest cannot be null");
		}

		GameCreationResult creationResult = new GameCreationResult();

		// Verify that home, visiting, and favorite teams are real teams that
		// exist in the persistence store.

		// Home team:
		Team homeTeam = this.teamRepository.getTeamByTeamName(gameRequest.getHomeTeamName());

		if (homeTeam == null) {

			creationResult.addValidationIssue(
					String.format(GameServiceImpl.TEAMDOESNOTEXISTMSG, gameRequest.getHomeTeamName()));
		}

		// Visiting team:
		Team visitingTeam = this.teamRepository.getTeamByTeamName(gameRequest.getVisitingTeamName());

		if (visitingTeam == null) {

			creationResult.addValidationIssue(
					String.format(GameServiceImpl.TEAMDOESNOTEXISTMSG, gameRequest.getVisitingTeamName()));
		}

		// Favorite team:
		Team favoriteTeam = this.teamRepository.getTeamByTeamName(gameRequest.getFavoriteTeamName());

		if (favoriteTeam == null) {

			creationResult.addValidationIssue(
					String.format(GameServiceImpl.TEAMDOESNOTEXISTMSG, gameRequest.getFavoriteTeamName()));
		}

		// TODO: Add more validation with teams i.e. same team can't be home and
		// visiting team etc.

		// If validation rules have passed, create Game domain instance and
		// pass back in game creation result instance.
		if (!creationResult.hasValidationIssues()) {
			creationResult.setCreatedGame(new Game(homeTeam, visitingTeam, favoriteTeam, gameRequest.getStartDateTime(),
					gameRequest.getWeekNumber(), gameRequest.getLine(), gameRequest.getOverUnder()));
		}

		return creationResult;
	}
}
