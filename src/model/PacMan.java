package model;

public class PacMan {
	
	//Attributes
	/**Unique id for this PacMan*/
	private int id;
	
	/**Movement direction.*/
	private Directions direction;
	
	/**Size of the PacMan*/
	private double size;
	
	/**Horizontal position of the PacMan*/
	private double positionX;
	
	/**Vertical position of the PacMan*/
	private double positionY;
	
	/**Number of bounces this PacMan has done*/
	private int bounces;
	
	/**True if this PacMan has been caught, false if not.*/
	private boolean caught;
	
	/**Determines how fast the PacMan moves.*/
	private long sleepTime;

	//Methods
	
	/**
	 * Initializes a new instance of this Class.
	 * @param dir Direction of movement of this PacMan
	 * @param x Horizontal position of this PacMan
	 * @param y Vertical position of this PacMan
	 * @param s Diameter of the PacMan's body
	 * @param id Unique identifier for this PacMan
	 */
	public PacMan(Directions dir, double x, double y, double s, int id, long st) {
		this.id = id;
		direction = dir;
		size = s;
		positionX = x;
		positionY = y;
		bounces = 0;
		caught = false;
		sleepTime = st;
	}
	
	/**
	 * Initializes a new instance of this class using the information held in the file.
	 * @param parts An array holding the information needed to build an object of this class.
	 * @throws NumberFormatException when a field does not match the required information with the information in the parameter.
	 */
	public PacMan(String[] parts) {
		//"#X\tY\tDIRECTION\tSLEEP\tSIZE\tID\tBOUNCES\tCAUGHT";
		positionX = Double.parseDouble(parts[0]);
		positionY = Double.parseDouble(parts[1]);
		direction = Directions.valueOf(parts[2]);
		sleepTime = Long.parseLong(parts[3]);
		size = Double.parseDouble(parts[4]);
		id = Integer.parseInt(parts[5]);
		bounces = Integer.parseInt(parts[6]);
		caught = Boolean.parseBoolean(parts[7]);
		
	}
	
	/**
	 * Updates the horizontal and vertical position of this PacMan.
	 * @param maxX The maximum horizontal position, as set by the board's width.
	 * @param maxY The maximum vertical position, as set by the board's height.
	 */
	public void move(double maxX, double maxY) {
		double advance = 10;
		double radius = size/2;
		switch(direction) {
			case DOWNWARD:
				if(positionY+advance+radius>maxY) {
					direction = Directions.UPWARD;
					positionY = maxY-radius;
				}else {
					positionY = positionY+advance;					
				}
				break;
			case FORWARD:
				if(positionX+advance+radius>maxX) {
					direction = Directions.BACKWARD;
					positionX = maxX-radius;
				}else {
					positionX = positionX+advance;					
				}
				break;
			case UPWARD:
				if(positionY-advance-radius<0) {
					direction = Directions.DOWNWARD;
					positionY = radius;
				}else {
					positionY = positionY-advance;			
				}
				break;
			case BACKWARD:
				if(positionX-advance-radius<0) {
					direction = Directions.FORWARD;
					positionX = radius;
				}else {
					positionX = positionX-advance;			
				}
				break;
			}
		}

	/**
	 * Returns the horizontal position of this PacMan
	 * @return the value of the positionX field.
	 */
	public double getX() {
		return positionX;
	}
	
	/**
	 * Returns the vertical position of this PacMan
	 * @return the value of the positionY field
	 */
	public double getY() {
		return positionY;
	}
	
	/**
	 * Returns the size of this PacMan
	 * @return The size of the PacMan
	 */
	public double getSize() {
		return size;
	}
	
	/**
	 * Updates the value of the caught field and makes it true.
	 */
	public void catchIt() {
		caught = true;
	}
	
	/**
	 * Returns the value of the caught field.
	 * @return Either true or not, depending if this PacMan has been caught.
	 */
	public boolean isCaught() {
		return caught;
	}
	
	/**
	 * Adds one to the number of bounces this PacMan has done.
	 */
	public void addBounce() {
		bounces++;
	}
	
	/**
	 * Returns the value of the bounces field.
	 * @return The number of bounces this PacMan has done.
	 */
	public int getBounces() {
		return bounces;
	}
	
	/**
	 * Returns the value of the id field.
	 * @return The unique numerical ID of this PacMan
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Returns the sleep time of this PacMan, which determines its speed.
	 * @return The value of the sleepTime field.
	 */
	public long getSleep() {
		return sleepTime;
	}
	
	/**
	 * Returns the Direction of movement of this PacMan
	 * @return the direction of movement of this PacMan
	 */
	public Directions getDirection() {
		return direction;
	}
	
	/**
	 * Changes the direction of the PacMan to one directly in the contrary of the one it's already moving.
	 */
	public void changeDirection() {
		switch(direction) {
		case DOWNWARD:
			direction=Directions.UPWARD;
			break;
		case UPWARD:
			direction=Directions.DOWNWARD;
			break;
		case FORWARD:
			direction=Directions.BACKWARD;
			break;
		case BACKWARD:
			direction=Directions.FORWARD;
			break;
		}
	}
}