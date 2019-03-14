package model;

import java.io.*;
import java.util.*;
import ui.Controller;

/**
 * Class meant to manage the different files to be used around the program's execution.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.0 - March/2019
 */
public class FileManager {
	
	/**File that holds the data of a saved game*/
	private File savedGame;
	
	/**File that holds the data of all scores scored in the game.*/
	private File savedScores;
	
	//Relations
	/**List of all registered scores. Cannot be changed.*/
	public List<Score> scores;
	
	/**
	 * Creates an instance of FileManager.
	 * @param gui Relation with class Controller
	 * @param savedGamePath Path to the file storing the saved game.
	 * @param savedScoresPath Path to the files storing the saved scores.
	 */
	public FileManager(Controller gui, String savedGamePath, String savedScoresPath) {
		//To do
	}
	
	/**
	 * Overwrites the saved game file and stores the current state of the game in it.
	 */
	public void saveGame() {
		//To do
	}
	
	/**
	 * Reads the content of the saved game file and restores the state of the game in it.
	 */
	public void loadGame() {
		//To do
	}
	
	/**
	 * Returns a String holding the recorded scores in Catch the Pacman.
	 * @return A String holding scores.
	 */
	public String getScores() {
		return null; //To do
	}
	
	/**
	 * Records a new score in the game.
	 * @param sc The score to be recorded.
	 */
	public void addScore(Score sc) {
		//To do
	}
	
	/**Modifies the saved scores file.*/
	public void saveScores() {
		//To do
	}
}
