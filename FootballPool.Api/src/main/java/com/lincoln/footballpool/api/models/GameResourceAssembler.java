/**
 * 
 */
package com.lincoln.footballpool.api.models;

import org.springframework.stereotype.Component;

import com.lincoln.footballpool.api.controllers.GamesController;
import com.lincoln.footballpool.domain.entities.Game;

/**
 * @author sbyam101
 *
 */
@Component
public class GameResourceAssembler extends JaxRsResourceAssemblerSupport<Game, GameDto> {

	 public GameResourceAssembler() {
	        super(GamesController.class, GameDto.class);
	    }

	    @Override
	    public GameDto toResource(Game entity) {
	    	GameDto resource = this.createResourceWithId(
	            entity.getId(),
	            entity
	        );
	        
	    	resource.setHomeTeamCity(entity.getHomeTeam().getCity());
	    	resource.setHomeTeamName(entity.getHomeTeam().getTeamName());
	    	resource.setVisitingTeamCity(entity.getVisitingTeam().getCity());
	    	resource.setVisitingTeamName(entity.getVisitingTeam().getTeamName());
	    	resource.setFavoriteTeamCity(entity.getFavoriteTeam().getCity());
	    	resource.setFavoriteTeamName(entity.getFavoriteTeam().getTeamName());
	    	resource.setStartDateTime(entity.getStartDateTime());
	    	resource.setWeekNumber(entity.getWeekNumber());
	    	resource.setLine(entity.getLine());
	    	resource.setOverUnder(entity.getOverUnder());
	    	
	        return resource;
	    }
}
