package com.lincoln.footballpool.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Game extends EntityBase  {

	//Member Variables-------------------------------------------------------------
	
	/**
	 * Home team of the game.
	 */
	@OneToOne ()
	@JoinColumn(nullable=false)
	private Team homeTeam;
	
	/**
	 * Visiting team of the game.
	 */
	@OneToOne ()
	@JoinColumn(nullable=false)
	private Team visitingTeam;
	
	/**
	 * Team that is favored to win the game.
	 * <p>
	 * In the event a game has no favorite i.e. the line on the game is even, this will be set to null.
	 */
	@OneToOne ()
	@JoinColumn(nullable=false)
	private Team favoriteTeam;
	
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



	//Constructors-------------------------------------------------------------
	
	/**
	 * 
	 * Initializes a new instance of the {@code Game} class.
	 *
	 @param homeTeam home team of the game
	 @param visitingTeam visiting team of the game
	 @param favoriteTeam team favored to win the game
	 @param startDateTime date and start time of the game
	 @param weekNumber week number in which the game is played
	 @param line betting line of the game
	 @param overUnder Over/Under of the game
	 */
	public Game(final Team homeTeam, final Team visitingTeam, final Team favoriteTeam, 
			final LocalDateTime startDateTime, final int weekNumber, final double line, final double overUnder) {
		
		if (homeTeam == null) {
            throw new NullPointerException("homeTeam cannot be null.");
        }
		if (visitingTeam == null) {
            throw new NullPointerException("visitingTeam cannot be null.");
        }
		if (startDateTime == null) {
            throw new NullPointerException("startDateTime cannot be null.");
        }
		if (weekNumber < 0) {
			throw new IllegalArgumentException("weekNumber must be greater than 0.");
		}
		if (line < 0) {
			throw new IllegalArgumentException("line must be greater than 0.");
		}
		if (overUnder < 0) {
			throw new IllegalArgumentException("overUnder must be greater than 0.");
		}
		
		this.homeTeam = homeTeam;
		this.visitingTeam = visitingTeam;
		this.favoriteTeam = favoriteTeam;
		this.startDateTime = startDateTime;
		this.weekNumber = weekNumber;
		this.line = line;
		this.overUnder = overUnder;
	}
	
	/**
	 * Copy constructor that creates a new instance of the {@code Game} class based on information
	 * contained in the supplied instance.
	 * 
	 * @param game game instance for which to create the new one based on
	 */
	public Game(final Game game) {
		
		if (game == null) {
            throw new NullPointerException("game cannot be null.");
        }
		
		this.homeTeam = game.homeTeam;
		this.visitingTeam = game.visitingTeam;
		this.favoriteTeam = game.favoriteTeam;
		this.startDateTime = game.startDateTime;
		this.weekNumber = game.weekNumber;
		this.line = game.line;
		this.overUnder = game.overUnder;
	}
	
	/**
	 * Default constructor for the {@code Game} class.
	 */
	public Game() {
		
	}
	
	//Getters/Setter-------------------------------------------------------------

	/**
	 * Gets the home team of the game.
	 * 
	 * @return home team of the game
	 */
	public Team getHomeTeam() {
		return homeTeam;
	}

	/**
	 * Sets the home team of the game.
	 * 
	 * @param homeTeam home team of the game
	 */
	public void setHomeTeam(final Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	/**
	 * Gets the visiting team of the game.
	 * 
	 * @return visiting team of the game
	 */
	public Team getVisitingTeam() {
		return visitingTeam;
	}

	/**
	 * Sets the visiting team of the game.
	 * 
	 * @param visitingTeam visiting team of the game
	 */
	public void setVisitingTeam(final Team visitingTeam) {
		this.visitingTeam = visitingTeam;
	}

	/**
	 * Gets the favorite team of the game.
	 * 
	 * @return favorite team of the game
	 */
	public Team getFavoriteTeam() {
		return favoriteTeam;
	}

	/**
	 * Sets the favorite team of the game.
	 * 
	 * @param favoriteTeam favorite team of the game
	 */
	public void setFavoriteTeam(final Team favoriteTeam) {
		this.favoriteTeam = favoriteTeam;
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
	public void setStartDateTime(final LocalDateTime startDateTime) {
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
	public void setWeekNumber(final int weekNumber) {
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
	public void setLine(final double line) {
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
	public void setOverUnder(final double overUnder) {
		this.overUnder = overUnder;
	}
	
	//Public Methods-------------------------------------------------------------
	
	/**
	 * Updates the game instance with information contained in the supplied game instance.
	 * <p>
	 * This method represents a non-side effect free function.  Callers should be aware that
	 * passing in the game instance as a parameter changes the state of the game instance upon
	 * which this method is called.
	 * <p>
	 * This method exists to support this class being used as a JPA entity where generation of 
	 * a value for it's id property is handled by an ORM framework such as Hibernate. 
	 * 
	 * @param game game containing information to pass to the game instance
	 */
	public void updateGame(final Game game) {
		
		if (game == null) {
            throw new NullPointerException("game cannot be null.");
        }
		
		this.homeTeam = game.homeTeam;
		this.visitingTeam = game.visitingTeam;
		this.favoriteTeam = game.favoriteTeam;
		this.startDateTime = game.startDateTime;
		this.weekNumber = game.weekNumber;
		this.line = game.line;
		this.overUnder = game.overUnder;
	}
	
	/**
	 * {@code toString} method implementation.
	 */
	@Override
	public String toString() {
		return "Game [homeTeam=" + homeTeam + ", visitingTeam=" + visitingTeam + ", favoriteTeam=" + favoriteTeam
				+ ", startDateTime=" + startDateTime + ", weekNumber=" + weekNumber + ", line=" + line + ", overUnder="
				+ overUnder + "]";
	}
}
