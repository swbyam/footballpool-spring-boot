package com.lincoln.footballpool.api.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Controller;

import com.lincoln.footballpool.api.models.GameDto;
import com.lincoln.footballpool.api.models.GameResourceAssembler;
import com.lincoln.footballpool.domain.entities.Game;
import com.lincoln.footballpool.domain.repositories.GameRepository;

@Controller
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GamesController {

	// Member
	// Variables-------------------------------------------------------------

	/**
	 * Game Repository that handles basic CRUD operations consistent with the
	 * Repository pattern regarding {@link Game} entity instances.
	 */
	private final GameRepository gameRepository;
	
	private final GameResourceAssembler resourceAssembler;
	
	//Constructors-------------------------------------------------------------
	
	/**
	 * Initializes a new instance of the {@code GamesController} class.
	 * 
	 * @param gameRepository game repository instance that handles CRUD operations related to game instances
	 */
	public GamesController(final GameRepository gameRepository, final GameResourceAssembler resourceAssembler) {
		
		if (gameRepository == null) {
            throw new NullPointerException("gameRepository cannot be null.");
        }
		
		if (resourceAssembler == null) {
            throw new NullPointerException("resourceAssembler cannot be null.");
        }
		
		this.gameRepository = gameRepository;
		this.resourceAssembler = resourceAssembler;
	}
	
	//Resource Methods-------------------------------------------------------------
	
	/**
	 * Processes GET requests that returns all games that take place during the supplied week number.
	 * 
	 * @param week week number for which games are to be retrieved
	 * 
	 * @return response containing games taking place during the specified week
	 */
	@GET
	@Path("week/{weekNumber}")
	public Response getGames(@PathParam("weekNumber") final int weekNumber) {
		
		Collection<Game> games = this.gameRepository.getGamesByWeekNumber(weekNumber);
		
		if (games.isEmpty()) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("Games played during week %d were not found.", weekNumber)).build();
		}
		
		//Convert game domain instances to their DTO/resource counterpart.
		List<GameDto> gameResources = this.resourceAssembler.toResources(games);
		
		//Create wrapper for resources.
		Resources<GameDto> wrappedGameResources = new Resources<>(gameResources);
		wrappedGameResources.add(
                JaxRsLinkBuilder
                .linkTo(GamesController.class)
                .withSelfRel());
		
		return Response.ok(wrappedGameResources).build();
	}
	
	@GET
	@Path("{gameId}")
	public Response getGameById(@PathParam("gameId") final int gameId) {
		
		Game game = this.gameRepository.findOne(gameId);
		
		//If game was not found in persistence store, return corresponding status.
		if (game == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("A game with id %d could not be found.", gameId)).build();
		}
		
		GameDto gameResource = this.resourceAssembler.toResource(game);
		Resource<GameDto> wrappedGameResource = new Resource<>(gameResource);
		
		return Response.ok(wrappedGameResource).build();
	}
	
	/**
	 * Processes GET requests and returns all games that are contained in the persistence store.
	 * 
	 * @return list of games contained in the persistence store
	 */
	@GET
	public Response getAllGames() {
		
		Collection<Game> games = new ArrayList<Game>();
		this.gameRepository.findAll().forEach(game -> games.add(game));
		
		//Convert game domain instances to their DTO/resource counterpart.
		List<GameDto> gameResources = this.resourceAssembler.toResources(games);
		
		//Create wrapper for resources.
		Resources<GameDto> wrappedGameResources = new Resources<>(gameResources);
		
		return Response.ok(wrappedGameResources).build();
	}
}
