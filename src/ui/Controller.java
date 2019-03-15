package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import model.*;
import java.util.*;

public class Controller{
	//Attributes
	/** Board where the current game flows.*/
	@FXML
	private Pane boardPane;
	
	/**Label meant to be used with information, such as current score or new record.*/
	@FXML
	private Label informationLabel;
	
	//Relations
	private FileManager fm;
	
	private List<PacMan> pacmen;
	
	/**
	 * Starts the program and its initial variables.
	 */
	@FXML
	void initialize() {
		try {
			fm = new FileManager(this);
		} catch (ClassNotFoundException e) {
			informationLabel.setText("Error, there are some missing game files.");
			e.printStackTrace();
		} catch (IOException e) {
			informationLabel.setText("An error happened...");
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts a new game for the user to play. As there were no specifications about game's difficulty, it will start with 10 PacMen.
	 * @param ev Event of the menu item being pressed.
	 */
	@FXML
	void newGame(ActionEvent ev) {
		//Change this to modify the number of PacMen
		int number = 10;
		pacmen = new ArrayList<PacMan>(10);
		for (int i = 0; i < number; i++) {
			PacMan p = new PacMan((int)Math.random()*4, Math.random()*boardPane.getWidth(), Math.random()*boardPane.getHeight(), this);
			p.move(1);
			pacmen.add(p);
		}
	}
	
	/**
	 * Draws each PacMan in the interface and sets its method on setOnActionClicked.
	 * @param pacMan The main shape.
	 * @param eye The eye of the PacMan.
	 */
	public void draw(PacMan p) {
		Arc arc = new Arc();
		arc.setCenterX(p.getX()+20);
		arc.setCenterY(p.getY()+20);
		arc.setRadiusX(40.0);
		arc.setRadiusY(40.0);
		arc.setStartAngle(45.0);
		arc.setLength(270.0);
		arc.setType(ArcType.ROUND);
		arc.setFill(Color.YELLOW);
		Circle eye = new Circle();
		eye.setCenterX(p.getEyeX());
		eye.setCenterY(p.getEyeY());
		eye.setRadius(10.0);
		eye.setFill(Color.BLACK);
		boardPane.getChildren().add(arc);
		boardPane.getChildren().add(eye);
	}
	
	@FXML
	void saveGame(ActionEvent e) {
		//TO-DO
	}
	
	@FXML
	void loadGame(ActionEvent e) {
		//TO-DO
	}
	
	@FXML
	void showScores(ActionEvent e) {
		//TO-DO
	}
	
	public List<PacMan> getPacmen(){
		return pacmen;
	}

	public void movePacMan(PacMan pacMan, int direction) {
		pacMan.setX(pacMan.getX()+10);
		pacMan.setX(pacMan.getX()+10);
	}
		
}