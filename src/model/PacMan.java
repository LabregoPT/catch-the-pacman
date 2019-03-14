package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import ui.*;

/**
 * Class meant to act as the pacmen to be caught throughout the game.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - March/2019
 */
public class PacMan extends Arc {
	
	private int direction;
	private boolean alreadyCaught;
	private int bounces;
	private Circle eye;
	private double posX;
	private double posY;
	
	//Relations
	private Controller gui;
	
	/**
	 * Creates a new PacMan and its design.
	 * @param dir Initial movement direction of the PacMan
	 * @param x Initial x position of the pacman.
	 * @param y Initial y position of the pacman.
	 */
	public PacMan(int dir, int x, int y, Controller c) {
		super(x, y, 40, 40, 45, 270);
		super.setFill(Color.YELLOW);
		eye = new Circle(x+15, y-15, 10, Color.BLACK);
		direction = dir;
		posX = getLayoutX();
		posY = getLayoutY();
		gui = c;
		gui.draw(this, eye);
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
	 * Returns the horizontal size of the PacMan, as it is a semi circle, it should be always the same as two times its radius.
	 * @return
	 */
	public int getSize() {
		return (int)getRadiusX()*2;
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
}
