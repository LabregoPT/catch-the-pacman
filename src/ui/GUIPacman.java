package ui;

import model.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * Class that holds different JavaFX shapes to be drawn in the GUI
 * @author Jhon Edward Mora - Universidad ICESI - A00355710
 * @version 1.2 - March/2019
 */
public class GUIPacman{
	
	//Attributes
	/**The shape that represents the pacman's body*/
	private Arc body;
	
	//Relation
	/**The PacMan represented by this GUIPacman*/
	private PacMan pacman;
	
	//Methods
	
	/**
	 * Constructor method. Initializes an instance of the Class.
	 * @param p The PacMan represented by this GUIPacman. 
	 */
	public GUIPacman(PacMan p) {
		pacman = p;
		double x = p.getX();
		double y = p.getY();
		double s = p.getSize();
		body = new Arc();
		body.setCenterX(x+(s/2));
		body.setCenterY(y+(s/2));
		body.setLength(270);
		body.setStartAngle(45);
		body.setRadiusX(s/2);
		body.setRadiusY(s/2);
		body.setType(ArcType.ROUND);
		body.setFill(Color.YELLOW);
		body.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				pacman.catchIt();
				body.setVisible(false);
			}
		});
		}
	
	/**
	 * Moves the shapes that represent each PacMan after it has been moved.
	 */
	public void move() {
		body.setCenterX(pacman.getX());
		body.setCenterY(pacman.getY());
		}
	
	/**
	 * Returns the Arc shape representing the body.
	 * @return The Arc representing the body.
	 */
	public Arc getBody() {
		return body;
	}
}
