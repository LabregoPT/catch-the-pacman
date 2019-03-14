package model;

import java.io.Serializable;

/**
 * Class meant to hold every Score registered by the user.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - March/2019
 */
public class Score implements Serializable{
	
	
	/**Unic serial id*/
	private static final long serialVersionUID = -2294215226042580082L;

	/**The name registered by the user. */
	private String name;
	
	/**The score that the user got in a game */
	private long score;
	
	/**
	 * Initializes an instance of Score
	 * @param name The name the user added.
	 * @param sc The score the user got.
	 */
	public Score(String name, long sc) {
		this.name = name;
		score = sc;
	}
	
	public String getScore() {
		return name + ": " + score;
	}
}
