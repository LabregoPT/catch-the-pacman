package model;

import ui.*;

/**
 * Class meant to act as the pacmen to be caught throughout the game.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - March/2019
 */
public class PacMan{
	
	private int direction;
	private boolean alreadyCaught;
	private int bounces;
	private double eyeX;
	private double eyeY;
	private double posX;
	private double posY;
	
	//Relations
	private Controller gui;
	
	/**
	 * Creates a new PacMan and its design.
	 * @param dir Initial movement direction of the PacMan
	 * @param x Initial x position of the PacMan.
	 * @param y Initial y position of the PacMan.
	 */
	public PacMan(int dir, double x, double y, Controller c){
		direction = dir;
		eyeX = x+15;
		eyeY = y-15;
		gui = c;
		gui.draw(this);
	}
	
	/**
	 * Returns the direction in which this PacMan is moving. 
	 * @return the movement direction of this PacMan.
	 */
	public int getDirection() {
		return direction;
	}
	
	/**
	 * Turns the PacMan to the opposite movement direction.
	 */
	public void turnAround() {
		//To create and change constants. Use enum
		if(direction == 1) {
			direction = 2;
		}if(direction == 2) {
			direction = 1;
		}if(direction == 3) {
			direction = 4;
		}if(direction == 4) {
			direction = 3;
		}
	}
	
	/**
	 * Returns the state of the alreadyCaught field.
	 * @return
	 */
	public boolean getCaught() {
		return alreadyCaught;
	}
	
	/**
	 * Changes the state of the alreadyCaught field to true.
	 */
	public void catchIt() {
		alreadyCaught = true;
	}
	
	/**
	 * Return the number of times this instance of PacMan has bounced.
	 * @return The total bounces of this PacMan
	 */
	public int getBounces() {
		return bounces;
	}
	
	/**
	 * Adds one bounce to this PacMan.
	 */
	public void addBounce() {
		bounces++;
	}
	
	/**
	 * Returns the X position of this PacMan
	 * @return The Horizontal position of this PacMan
	 */
	public double getX() {
		return posX;
	}
	
	
	/**
	 * Returns the Y position of this PacMan
	 * @return The Vertical position of this PacMan
	 */
	public double getY() {
		return posY;
	}
	
	/**
	 * Returns the X position of the eye of this PacMan.
	 * @return X position + 15 units
	 */
	public double getEyeX() {
		return eyeX;
	}
	
	/**
	 * Returns the Y position of the eye of this PacMan
	 * @return Y Position -15 units
	 */
	public double getEyeY() {
		return eyeY;
	}
	
	/**
	 * Updates the position of this PacMan in the gui
	 */
	public void move(int direction) {
		gui.movePacMan(this, direction);
		gui.draw(this);
	}
	
	/**
	 * Updates the value of the x position in this PacMan
	 * @param x New horizontal position.
	 */
	public void setX(double x) {
		posX = x;
	}
	
	/**
	 * Updates the value of the y position in this PacMan
	 * @param y New vertical position.
	 */
	public void setY(double y) {
		posY = y;
	}
}