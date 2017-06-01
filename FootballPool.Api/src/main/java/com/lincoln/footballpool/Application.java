package com.lincoln.footballpool;

import java.time.LocalDateTime;
import java.time.Month;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lincoln.footballpool.domain.entities.Game;
import com.lincoln.footballpool.domain.entities.Team;
import com.lincoln.footballpool.domain.repositories.GameRepository;
import com.lincoln.footballpool.domain.repositories.TeamRepository;

@SpringBootApplication
//NOTE: Adding this annotation is the same as adding @Configuration @EnableAutoConfiguration @ComponentScan

@EnableJpaRepositories(basePackages = {"com.lincoln.footballpool.domain.repositories"})
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(TeamRepository teamRepository, GameRepository gameRepository) {
		return (args) -> {
			
			Team homeTeam = new Team("New England", "Patriots");
			Team visitingTeam = new Team("Atlanta", "Falcons");
			int superBowlWeekNumber = 19;
			
			/*homeTeam = teamRepository.save(homeTeam);
			visitingTeam = teamRepository.save(visitingTeam);
			
			teamRepository.findAll().forEach(team -> log.info("team namme: " + team.getTeamName()));
			teamRepository.findAll().forEach(team -> log.info("%s is in the SuperBowl", team.getTeamName()));*/
			
			gameRepository.save(new Game(
					homeTeam, visitingTeam, homeTeam, 
					LocalDateTime.of(2017, Month.FEBRUARY, 5, 5, 18), 
					superBowlWeekNumber, 3.0, 56.5));
			
			gameRepository.getGamesByWeekNumber(superBowlWeekNumber).
			stream().forEach(game -> log.info(game.toString()));
		};
	}
}
