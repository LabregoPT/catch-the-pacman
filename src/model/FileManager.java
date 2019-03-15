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
	
	//Constants
	/**Stores the path where the saved games are located."*/
	private static final String SAVE_PATH = "data/save.txt";
	
	/**Stores the path where the saved scores are located."*/
	private static final String SCORE_PATH = "data/scores.dat";
	
	//Attributes
	/**Path to the saved game file*/
	private String savedGamePath;
	
	/**File to the saved scores file*/
	private String savedScoresPath;
	
	//Relations
	/**List of all registered scores. Cannot be changed.*/
	private List<Score> scores;
	
	private Controller gui;
	
	/**
	 * Creates an instance of FileManager.
	 * @param gui Relation with class Controller
	 * @param sgp Path to the file storing the saved game.
	 * @param ssp Path to the files storing the saved scores.
	 * @throws IOException when there's an error loading the files.
	 * @throws ClassNotFoundException when there's an error casting the class in the files.
	 */
	public FileManager(Controller gui) throws IOException, ClassNotFoundException{
		this.gui = gui;
		savedGamePath = SAVE_PATH;
		savedScoresPath = SCORE_PATH;
		scores = new ArrayList<Score>();
		loadScore();
	}
	
	/**
	 * Overwrites the saved game file and stores the current state of the game in it.
	 * @throws FileNotFoundException When there's an error locating the saved game file. 
	 */
	public void saveGame() throws FileNotFoundException{
		String gameData = "";
		List<PacMan> pacmans = gui.getPacmen();
		//Saves the data.
		File f = new File(savedGamePath);
		PrintWriter pw = new PrintWriter(f);
		pw.print(gameData);
		pw.close();
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
	
	/**
	 * Modifies the saved scores file.
	 * @throws IOException When there's an error with the input.
	 */
	public void saveScores() throws IOException{
		File f = new File(savedScoresPath);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream (f));
		oos.writeObject(scores);
		oos.close();
	}
	
	/**Loads the saved score file.
	 * @throws IOException When there's an error with the input.
	 * @throws ClassNotFoundException When there's an error loading the class. 
	 */
	@SuppressWarnings("unchecked")
	public void loadScore() throws IOException, ClassNotFoundException {
		File f = new File(savedScoresPath);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			scores = (List<Score>)ois.readObject();
			ois.close();
		}
	}
}
