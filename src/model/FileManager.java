package model;
import java.util.*;
import java.io.*;
import ui.*;

/** Class meant to handle all the files storing the program's data.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 *
 */
public class FileManager {
	
	//Constants
	/**Path to the saved game file.*/
	private static final String SAVE_PATH = "data/savegame.txt";
	
	/**Path to the saved scores file.*/
	private static final String SCORES_PATH = "data/scores.dat";
	
	//Relations
	/**The GUI that holds all the data to be stored.*/
	private Controller gui;
	
	/**Scores in this game.*/
	private List<Score> scores;
	
	
	/** Constructor method. Initializes an instance of the class.
	 * @param gui
	 */
	public FileManager(Controller gui) throws IOException, ClassNotFoundException{
		this.gui = gui;
		scores = new ArrayList<Score>();
		loadScores();
	}

	/**
	 * Reads a saved game file and restores the game status it has.
	 * @throws IOException When there's a problem reading the File.
	 */
	public void loadGame() throws IOException{
		File f = new File(SAVE_PATH);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		ArrayList<PacMan> pacmen = new ArrayList<PacMan>();
		String[] parts = null;
		while(line != null) {
			if(line.charAt(0) == '#') {
				line = br.readLine();
			}else {
				parts = line.split("\t");
				PacMan p = new PacMan(parts);
				pacmen.add(p);
				line = br.readLine();
			}
		}
		gui.startGame(pacmen);
		
		br.close();
	}
	
	/**
	 * Stores the status of a game in a file.
	 * @throws IOException When there's a problem reading the File.
	 */
	public void saveGame() throws IOException {
		List<PacMan> pacmen = gui.getPacmen();
		String save = "#Pacman\n";
		save += "#X\tY\tDIRECTION\tSLEEP\tSIZE\tID\tBOUNCES\tCAUGHT\n";
		for(PacMan p : pacmen) {
			save += p.getX() + "\t" + p.getY() + "\t" + p.getDirection().toString() + "\t" + p.getSleep() + "\t" + p.getSize() + "\t" + p.getId() + "\t" + p.getBounces() + "\t" + p.isCaught() + "\t" + "\n";
		}
		PrintWriter pw = new PrintWriter(new File(SAVE_PATH));
		pw.write(save);
		pw.close();
	}
	
	/**
	 * Stores the already saved scores in a serialized file.
	 * @throws IOException When the serialized file is not found.
	 */
	public void saveScores() throws IOException{
		File f = new File(SCORES_PATH);
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		oos.writeObject(scores);
		oos.close();
	}
	
	/**
	 * Loads the information saved in the scores file.
	 * @throws IOException When the serialized file is not found.
	 * @throws ClassNotFoundException When the class "ArrayList<Score>" is not found.
	 */
	@SuppressWarnings("unchecked")
	public void loadScores() throws IOException, ClassNotFoundException{
		File f = new File(SCORES_PATH);
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			scores = (ArrayList<Score>)ois.readObject();
			ois.close();
		}
	}
	
	/**
	 * Checks if a Score is in the Hall of Fame (Top ten of Scores)
	 * @param c Scored number of bounces.
	 * @return True if the score is in the top ten of scores, false if not.
	 */
	public boolean mayBeAdded(int c) {
		boolean added = false;	
		if(!scores.isEmpty() && scores.size()<10) {
			for (int i = 0; i < 10 && !added; i++) {
				if(!added) {
					if(scores.get(i).getBounces() < c) {
						added = true;
					}
				}
			}
		}else {
			added = true;
		}
		return added;
	}
	
	/**
	 * Adds a new score to the registered scores in the program.
	 * @param c Score to be added.
	 */
	public void addScore(Score c) throws IOException{
		boolean added = false;
		if(scores.isEmpty()) {
			scores.add(c);
			added = true;
		}
		for (int i = 0; i < scores.size() && !added; i++) {
			if(added) {
				if(scores.get(i).getBounces() > c.getBounces()) {
					added = true;
					scores.add(i, c);
				}
			}
		}
		saveScores();
	}
	
	
	/**
	 * Returns the information of the best 10 Scores recorded in the game.
	 * @return The name and number of bounces of the 10 best scores in the game.
	 */
	public String getHOF() {
		String msg = "Hall Of Fame:\n";
		if(scores.size() < 10) {
			for(int i = 0; i<scores.size(); i++) {
				msg += scores.get(i).toString() + "\n";
			}
		}else {
			for(int i = 0; i<10; i++) {
				msg += scores.get(i).toString() + "\n";
			}
		}
		return msg;
	}
}
