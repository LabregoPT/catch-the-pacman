package threads;

import ui.*;
import javafx.application.*;

/**
 * Thread that will update the gui's components
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.1 March/2019
 */
public class GUIUpdaterThread extends Thread {
	
	private final static long UPDATE_SLEEP_TIME = 5;
	
	//Relation
	/**GUI to be updated.*/
	private Controller gui;
	
	/**Tells whether this thread is active or not.*/
	private boolean active;
	
	/**
	 * Initializes an instance of this thread.
	 * @param gui The GUI to be updated
	 */
	public GUIUpdaterThread(Controller gui) {
		this.gui = gui;
		active = true;
	}
	
	/**
	 * Updates the GUI by calling the updateShapes method. 
	 */
	public void move() {
		gui.updateShapes();
		if(gui.checkFinished() && gui.isRunning()) {
			gui.registerScore();
			gui.finishGame();
		}
	}
	
	/**
	 * Runs the updating method while the thread is still alive.
	 */
	public void run(){
		while(active){
			try {
				Platform.runLater(new Runnable() {
					public void run(){
						move();
					}
				});
				Thread.sleep(UPDATE_SLEEP_TIME);
			}catch(InterruptedException e) {
				e.printStackTrace();
				}
			}
		}

}
