package model;

import java.io.Serializable;

/**
 * Class that will hold each individual score and the player's name.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.2 - March/2019
 */
public class Score implements Serializable{
	//Constants
	private static final long serialVersionUID = 1L;
	
	//Attributes
	/**The number of bounces reached in this Score.*/
	private int bounces;
	
	/**The name of the player who scored this Score.*/
	private String name;
	
	/**
	 * Instantiates a new object of class Score.
	 * @param b The number of bounces reached in this Score.
	 * @param n The name of the player who scored this Score.
	 */
	public Score(int b, String n) {
		bounces = b;
		name = n;
	}
	
	/**
	 * Returns the number of bounces reached in this score.
	 * @return Number of bounces reached in this score.
	 */
	public int getBounces() {
		return bounces;
	}
	
	/**
	 * Returns the name of the player who scored this Score.
	 * @return Name of the scorer player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns a String object representing the Score
	 * @return A String holding the name and the number of bounces of this score, separated by a tab ("\t").
	 */
	public String toString() {
		return name + "\t" + bounces;
	}
}
