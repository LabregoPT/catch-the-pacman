package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Application app that will launch the GUI.
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.2 - March/2019
 */
public class Main extends Application{

	/**
	 * Main method, starts the program.
	 * @param args Arguments for the program to be launched.
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Launches the GUI of the program.
	 */
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Catch the PacMan!");
		stage.show();
	}

}
