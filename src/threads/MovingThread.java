package threads;

import model.*;
import ui.*;

public class MovingThread extends Thread {

	//Relations
	/**The PacMan to be moved.*/
	private PacMan pacman;
	
	/**The GUI that contains each PacMan*/
	private Controller gui;

	/**Tells whether this thread is active or not.*/
	private boolean active;
	
	//Methods
	
	/**
	 * Initializes a new instance of the class
	 * @param pm The PacMan to be moved
	 * @param gui The GUI holding all of the PacMan
	 */
	public MovingThread(PacMan pm, Controller gui) {
		pacman = pm;
		this.gui = gui;
		active = true;
	}
	
	/**
	 * Updates the positionX and positionY of the PacMan.
	 */
	private void move() {
		double maxX = gui.getBoardWidth();
		double maxY = gui.getBoardHeight();
		pacman.move(maxX, maxY);
	}
	
	/**
	 * Stops the execution of this thread.
	 */
	public void deactivate() {
		active = false;
	}
	
	/**
	 * Updates the position of the PacMan at a rate of its speed while this thread is alive.
	 */
	public void run() {
		while(active) {
			try{
				gui.checkCrash(pacman);
				move();
				Thread.sleep(pacman.getSleep());
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
