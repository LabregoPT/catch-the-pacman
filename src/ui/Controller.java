package ui;

import threads.*;
import model.*;

import java.io.IOException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Controller class for the GUI
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.2 March/2019
 */
public class Controller {
	
	//Attributes
	/**The pane where the game will take place.*/
	@FXML
	private Pane boardPane;
	
	/**Label meant to show information throughout the game.*/
	@FXML
	private Label informationLabel;
	
	//Relations
	/**The group of GUIPacman that will graphically represent each PacMan.*/
	private List<GUIPacman> shapes;
	
	/**The group of modeled PacMan that will be represented in the GUI.*/
	private List<PacMan> pacmen;
	
	/**The set of MovingThread that controls the movement of the Pacmen.*/
	private List<MovingThread> mts;
	
	/**The FileManager that will handle all the persistence methods.*/
	private FileManager sfm;
	
	/**Tells whether there's a game currently running or not.*/
	private boolean runningGame;
	
	
	/**
	 * Creates all the lists and initializes the relation with the FileManager
	 */
	@FXML
	void initialize() {
		shapes = new ArrayList<GUIPacman>();
		pacmen = new ArrayList<PacMan>();
		try {
			sfm = new FileManager(this);
		}catch(IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		mts = new ArrayList<MovingThread>();
		runningGame = false;
		GUIUpdaterThread gut = new GUIUpdaterThread(this);
		gut.setDaemon(true);
		gut.start();
	}
	
	
	/**
	 * Simply calls the startGame method.
	 * @param e The action of clicking the New Game option.
	 */
	@FXML
	void newGame(ActionEvent e) {
		try{
			runningGame = true;
			startGame();
		}catch(IllegalArgumentException ex) {
			Alert t = new Alert(AlertType.INFORMATION, "There's currently a game on board.");
			t.setContentText("Please finish the current game first.");
			t.show();
		}
	}
	
	/**
	 * Starts a new Game with random positions.
	 */
	public void startGame() {
		runningGame = true;
		Random rnd = new Random();
		//Change this number to modify the number of PacMen in screen.
		int numberOfPacMen = 10;
		for(int i = 0; i<numberOfPacMen; i++) {
			double size = 40.0+(Math.random()*20);
			double posX = ((Math.random()*640));
			double posY = ((Math.random()*400));
			int dir = rnd.nextInt(3);
			Directions dire;
			if(dir == 1) {
				dire = Directions.BACKWARD;
			}else if(dir == 2) {
				dire = Directions.FORWARD;
			}else if(dir == 3) {
				dire = Directions.UPWARD;
			}else {
				dire = Directions.DOWNWARD;
			}
			int id = i;
			long sleepTime = 30 + (long)(Math.random()*10);
			PacMan p = new PacMan(dire, posX, posY, size, id, sleepTime);
			pacmen.add(p);
			MovingThread mt = new MovingThread(p, this);
			mts.add(mt);
			mt.setDaemon(true);
			mt.start();
			GUIPacman gp = new GUIPacman(p);
			shapes.add(gp);
		}
		for(GUIPacman g : shapes) {
			boardPane.getChildren().add(g.getBody());
		}
	}
	
	/**
	 * Starts a game given an ArrayList of PacMen.
	 * @param pm The given ArrayList of PacMen.
	 */
	public void startGame(ArrayList<PacMan> pm) {
		finishGame();
		runningGame = true;
		pacmen = pm;
		for(PacMan p : pacmen) {
			MovingThread mt = new MovingThread(p, this);
			mts.add(mt);
			mt.setDaemon(true);
			mt.start();
			GUIPacman gp = new GUIPacman(p);
			shapes.add(gp);
		}
		for(int i = 0; i<shapes.size(); i++) {
			if(!pacmen.get(i).isCaught()) {
			boardPane.getChildren().add(shapes.get(i).getBody());
			}
		}
	}

	/**
	 * Stores the state of the current game.
	 * @param e The action of clicking the Save option.
	 */
	@FXML
	void saveGame(ActionEvent e) {
		try {
			sfm.saveGame();
			runningGame = false;
			finishGame();
		}catch(IOException ex) {
			Alert t = new Alert(AlertType.ERROR, ex.getLocalizedMessage());
			t.setContentText("An error ocurred during the saving of the game.");
			t.show();
		}
	}
	
	/**
	 * Restores the state of the last saved game.
	 * @param e The action of clicking the Load option.
	 */
	@FXML
	void loadGame(ActionEvent e) {
		try {
			runningGame = true;
			sfm.loadGame();
		}catch(IOException ex) {
			Alert t = new Alert(AlertType.ERROR, ex.getLocalizedMessage());
			t.setContentText("An error ocurred during the loading of the game.");
			t.show();
		}
	}
	
	
	/**
	 * Displays the hall of fame in a new window.
	 * @param e The action of clicking the Show Scores option.
	 */
	@FXML
	void showScores(ActionEvent e) {
		runningGame = false;
			try {
				String hof = sfm.getHOF();
				Stage scoresWindow = new Stage();
				VBox content = new VBox();
				content.getChildren().add(new Text(hof));
				content.setPadding(new Insets(14, 14, 14, 14));
				Scene scoreSc = new Scene(content, 200,200);
				scoresWindow.setScene(scoreSc);
				scoresWindow.setTitle("Hall of Fame");
				scoresWindow.show();
			}catch(IndexOutOfBoundsException ex) {
				Alert t = new Alert(AlertType.ERROR, "asdasd");
				t.setContentText("There are no registered scores.");
				t.show();	
			}
	}
	
	/**
	 * Updates the GUI by moving every shape contained within the Pane acting as game board.
	 */
	public void updateShapes() {
		for(GUIPacman g : shapes) {
			g.move();
		}
	}
	
	/**
	 * Returns the game's board height.
	 * @return Game's board height.
	 */
	public double getBoardHeight() {
		return boardPane.getHeight();
	}

	/**
	 * Returns the game's board width.
	 * @return Game's board width.
	 */
	public double getBoardWidth() {
		return boardPane.getWidth();
	}
	
	/**
	 * Returns the list of PacMen.
	 * @return The list of PacMen.
	 */
	public List<PacMan> getPacmen(){
		return pacmen;
	}

	/**
	 * Restores the PacMen in this GUI
	 * @param pm the PacMen to be restored.
	 */
	public void setPacmen(ArrayList<PacMan> pm) {
		pacmen = pm;
	}

	/**
	 * Checks every PacMan with the one received as parameter to see if they are crashing between them. 
	 * @param pacman The PacMan that is going to be checked.
	 */
	public void checkCrash(PacMan pacman) {
		for(int i = 0; i<pacmen.size(); i++) {
			PacMan p = pacmen.get(i);
			if(!pacman.isCaught() && !p.isCaught()) {
				double distance= Math.sqrt((p.getX() - pacman.getX())*(p.getX() - pacman.getX())+(p.getY() - pacman.getY())*(p.getY() - pacman.getY()));
				if (distance < (p.getSize()/2)+(pacman.getSize()/2)) {
					p.changeDirection();
					pacman.changeDirection();
					p.addBounce();
				}
			}
		}
	}
	
	/**
	 * Checks if the game has already finished, and if so, deactivates all the processes in it.
	 */
	public boolean checkFinished() {
		boolean finished = false;
		int counter = 0;
		for(PacMan p : pacmen) {
			if(p.isCaught()) {
				counter++;
			}
		}
		if(counter == pacmen.size()) {
			finished = true;
			finishGame();
		}
		return finished;
	}
	
	/**
	 * Simply closes all the processes that have been started during the execution of the game.
	 */
	public void finishGame() {
		boardPane.getChildren().clear();
		pacmen.clear();
		shapes.clear();
		informationLabel.setText("Catch the PacMan!");
		for(MovingThread mt : mts) {
			mt.deactivate();
		}
	}
	
	/**
	 * Gets the total amount of bounces that have been performed throughout the game and checks if it will be added as a new score in the hall of fame
	 */
	public void registerScore() {
		runningGame = false;
		int totalBounces = 0;
		for(PacMan p : pacmen) {
			totalBounces += p.getBounces();
		}
		if(sfm.mayBeAdded(totalBounces)) {
			try{
				TextInputDialog tid = new TextInputDialog("Your name here");
				tid.setContentText("Please type your name to add you in the Hall of Fame.");
				tid.show();
				String name = tid.getEditor().getText();
				sfm.addScore(new Score(totalBounces, name));
			}catch(IOException ex) {
				Alert t = new Alert(AlertType.ERROR, ex.getLocalizedMessage());
				t.setContentText("Error: There are some missing files.");
				t.show();
			}
		}
	}
	
	/**
	 * Returns the value of the field gameRunning.
	 * @return Whether if there's a game running or not.
	 */
	public boolean isRunning() {
		return runningGame;
	}
}
