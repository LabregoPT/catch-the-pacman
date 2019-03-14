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
	
	/**Path to the saved game file*/
	private String savedGamePath;
	
	/**File that holds the data of all scores scored in the game.*/
	private File savedScores;
	
	/**File to the saved scores file*/
	private String savedScoresPath;
	
	//Relations
	/**List of all registered scores. Cannot be changed.*/
	public List<Score> scores;
	
	/**
	 * Creates an instance of FileManager.
	 * @param gui Relation with class Controller
	 * @param sgp Path to the file storing the saved game.
	 * @param ssp Path to the files storing the saved scores.
	 * @throws IOException when there's an error loading the files.
	 * @throws ClassNotFoundException when there's an error casting the class in the files.
	 */
	@SuppressWarnings("unchecked")
	public FileManager(Controller gui, String sgp, String ssp) throws IOException, ClassNotFoundException{
		savedGamePath = sgp;
		savedScoresPath = ssp;
		savedGame = new File(savedGamePath);
		savedScores = new File(savedScoresPath);
		
		//Verifies if there is already a file with the saved scores.
		if(!savedScores.exists()) {
			scores = new ArrayList<Score>();
		}else {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedScores));
			scores = (ArrayList<Score>)ois.readObject();
			ois.close();
		}
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
		String message = "";
		if(scores.isEmpty()) {
			message = "There are no currently registered scores.";
		}else {
			int j = 1;
			for (Score i : scores) {
				message += (j) + i.getScore() + "\n";
				j++;
			}
		}
		return message;
	}
	
	/**
	 * Records a new score in the game.
	 * @param sc The score to be recorded.
	 */
	public void addScore(Score sc) {
		scores.add(sc);
	}
	
	/**Modifies the saved scores file.*/
	public void saveScores() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (savedScores));
		oos.writeObject(scores);
		oos.close();
	}
}
