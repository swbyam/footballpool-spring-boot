package com.lincoln.footballpool.api.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Controller;

import com.lincoln.footballpool.api.models.GameDto;
import com.lincoln.footballpool.api.models.GameRequest;
import com.lincoln.footballpool.api.models.GameResourceAssembler;
import com.lincoln.footballpool.api.services.GameCreationResult;
import com.lincoln.footballpool.api.services.GameService;
import com.lincoln.footballpool.domain.entities.Game;
import com.lincoln.footballpool.domain.repositories.GameRepository;

@Controller
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GamesController {

	// Member Variables-------------------------------------------------------------

	/**
	 * Game Repository that handles basic CRUD operations consistent with the
	 * Repository pattern regarding {@link Game} entity instances.
	 */
	private final GameRepository gameRepository;
	
	/**
	 * Assembler class that converts Game domain instances to their DTO counterpart.
	 */
	private final GameResourceAssembler resourceAssembler;
	
	/**
	 * Service that provides various operations for {@code Game} domain instances
	 * that are managed by a REST-based web service.
	 * 
	 */
	private final GameService gameService;
	
	/**
	 * Message covering the scenario where a game is requested that does not exist.
	 */
	private static final String GAME_NOT_FOUND_MSG = "A game with id \"%s\" does not exist.";
	
	//Constructors-------------------------------------------------------------
	
	/**
	 * Initializes a new instance of the {@code GamesController} class.
	 * 
	 * @param gameRepository game repository instance that handles CRUD operations related to game instances
	 * @param resourceAssembler assembler class that converts Game domain instances to their DTO counterpart
	 */
	public GamesController(final GameRepository gameRepository, final GameResourceAssembler resourceAssembler,
			final GameService gameService) {
		
		if (gameRepository == null) {
            throw new NullPointerException("gameRepository cannot be null.");
        }
		
		if (resourceAssembler == null) {
            throw new NullPointerException("resourceAssembler cannot be null.");
        }
		
		if (gameService == null) {
            throw new NullPointerException("gameService cannot be null.");
        }
		
		this.gameRepository = gameRepository;
		this.resourceAssembler = resourceAssembler;
		this.gameService = gameService;
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
	
	/**
	 * Processes GET requests that returns a game based on its unique ID (gameId).
	 * 
	 * @param gameId unique ID of the game that is to be returned
	 * 
	 * @return game with the supplied ID
	 */
	@GET
	@Path("{gameId}")
	public Response getGameById(@PathParam("gameId") final long gameId) {
		
		Game game = this.gameRepository.findOne(gameId);
		
		//If game was not found in persistence store, return corresponding status.
		if (game == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity(String.format("A game with id %d could not be found.", gameId)).build();
		}
		
		GameDto gameResource = this.resourceAssembler.toResource(game);
		Resource<GameDto> wrappedGameResource = new Resource<>(gameResource);
		/*wrappedGameResource.add(
				JaxRsLinkBuilder
                .linkTo(GamesController.class)
                .withSelfRel());*/
		
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
	
	/**
	 * Processes GET requests that return  a paged list of games contained in the persistence store according to 
	 * the supplied paging parameters.
	 * 
	 * @param page number of the page of games
	 * @param size size or number of games on a page
	 * @param sort field of a game by which games on a page are to be sorted
	 * @param direction direction or order of the sort field (possible values are: "asc" or "desc")
	 * 
	 * @return paged list of games
	 */
	@GET
	@Path("/paged")
	public Response getAllGamesPaged(@QueryParam("page") @DefaultValue("0") int page,
            @QueryParam("size") @DefaultValue("20") int size,
            @QueryParam("sort") @DefaultValue("weekNumber") List<String> sort,
            @QueryParam("direction") @DefaultValue("asc") String direction) {
		
		Page<Game> games = this.gameRepository.findAll(
                new PageRequest(
                        page,
                        size,
                        Sort.Direction.fromString(direction),
                        sort.toArray(new String[0])
                ));
		
		//TODO: There is no "rel": "self" link for each of the games in the page collection!
		PagedResources<Game> resources = new PagedResources<>(
				games.getContent(),
                new PagedResources.PageMetadata(
                		games.getSize(),
                		games.getNumber(),
                		games.getTotalElements(),
                		games.getTotalPages()));
		
        resources.add(JaxRsLinkBuilder.linkTo(GamesController.class).withSelfRel());

        return Response.ok(resources).build();
	}
	
	/**
	 * Processes POST requests that create a new instance of a game based on information contained in the
	 * supplied game request and saves it to the persistence store.
	 * 
	 * @param game game request instance containing information needed to create a game
	 * 
	 * @return a response containing the URI of the newly created game;
	 */
	@POST
	public Response save(GameRequest game) {
		
		//Map GameRequest to Game domain instance.
		GameCreationResult result = this.gameService.createGameFromRequest(game);
		
		//If Game instance could not be created, return response indicating the request was bad
		//along with a list of issues incorrect in the request.
		if (result.hasValidationIssues()) {
			return Response.status(Status.BAD_REQUEST).entity(result.getValidationIssueSummary()).build();
		}
		
		//Save game to persistence store.
		Game savedGame = this.gameRepository.save(result.getCreatedGame());
		
		//Convert game to its resource/DTO counterpart.
		Link createdResourceLink = this.resourceAssembler.toResource(savedGame).getLink("self");
		
		return Response.created(URI.create(createdResourceLink.getHref())).build();
	}
	
	/**
	 * Processes PUT requests that modify an existing instance of game with the supplied game id
	 * based on information contained in the supplied game request.
	 * <p>
	 * Since this method is searching for games based on their id, it will not support creating a new game
	 * with that id if it does not already exist in the persistence store.  This is typically the
	 * behavior of PUT requests.
	 * 
	 * @param gameId unique id of the game that is to be updated
	 * @param game game request instance containing information needed to update an existing game
	 * 
	 * @return response containing the URI of the updated game
	 */
	@PUT
	@Path("{gameId}")
	public Response update(@PathParam("gameId") final long gameId, final GameRequest game) {
		
		//Check to see if game exists in the persistence store.
		Game existingGame = this.gameRepository.findOne(gameId); 
		
		if (existingGame == null) {
			return Response.status(Status.NOT_FOUND).entity(String.format(GamesController.GAME_NOT_FOUND_MSG, gameId)).build();
		}
		
		//Create a new game instance based on information contained in the supplied game request.
		GameCreationResult result = this.gameService.createGameFromRequest(game);
		
		//If Game instance could not be created, return response indicating the request was bad
		//along with a list of issues incorrect in the request.
		if (result.hasValidationIssues()) {
			return Response.status(Status.BAD_REQUEST).entity(result.getValidationIssueSummary()).build();
		}
		
		//Update existing game in persistence store with information from game passed to service method.
		existingGame.updateGame(result.getCreatedGame());
		Game savedGame = this.gameRepository.save(existingGame);
		
		//Convert game to its resource/DTO counterpart.
		Link createdResourceLink = this.resourceAssembler.toResource(savedGame).getLink("self");
		
		return Response.created(URI.create(createdResourceLink.getHref())).build();
	}
}
