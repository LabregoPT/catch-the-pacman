package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.*;
import model.*;

public class Controller{
	/** Board where the current game flows.*/
	@FXML
	private Pane boardPane;
	
	/**Label meant to be used with information, such as current score or new record.*/
	@FXML
	private Label informationLabel;
	
	/**
	 * Starts the program and its initial variables.
	 */
	@FXML
	void initialize() {
		
	}
	
	
	/**
	 * Draws each PacMan in the interface and sets its method on setOnActionClicked.
	 * @param pacMan The main shape.
	 * @param eye The eye of the PacMan.
	 */
	public void draw(PacMan pacMan, Circle eye) {
		
	}
		
}