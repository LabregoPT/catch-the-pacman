package model;

public class PacMan {
	
	//Attributes
	/**Unique id for this PacMan*/
	private int id;
	
	/**Movement direction.*/
	private Directions direction;
	
	/**Size of the PacMan*/
	private double size;
	
	/**Radius of the PacMan*/
	private double radius;
	
	/**Horizontal position of the PacMan*/
	private double positionX;
	
	/**Vertical position of the PacMan*/
	private double positionY;
	
	/**Number of bounces this PacMan has done*/
	private int bounces;
	
	/**True if this PacMan has been caught, false if not.*/
	private boolean caught;
	//Methods
	
	/**
	 * Initializes a new instance of this Class.
	 * @param dir Direction of movement of this PacMan
	 * @param x Horizontal position of this PacMan
	 * @param y Vertical position of this PacMan
	 * @param s Diameter of the PacMan's body
	 * @param id Unique identifier for this PacMan
	 */
	public PacMan(Directions dir, double x, double y, double s, int id) {
		this.id = id;
		direction = dir;
		size = s;
		radius = s/2;
		positionX = x;
		positionY = y;
		bounces = 0;
		caught = false;
	}
	
	/**
	 * Updates the horizontal and vertical position of this PacMan.
	 * @param newX The new horizontal position.
	 * @param newY The new vertical position.
	 * @param maxX The maximum horizontal position, as set by the board's width.
	 * @param maxY The maximum vertical position, as set by the board's height.
	 */
	public void move(double newX, double newY, double maxX, double maxY) {
		switch(direction) {
			case DOWNWARD:
				
				break;
			case FORWARD:
				
				break;
			case UPWARD:
			
				break;
			case BACKWARD:
				
				break;
			default:
			
			break;
			}
		}

	public double getX() {
		return positionX;
	}
	
	public double getY() {
		return positionY;
	}
	
	public void catchIt() {
		caught = true;
	}
	
	public boolean isCaught() {
		return caught;
	}
	
}