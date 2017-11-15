package ca.utoronto.utm.paint.shape;

/**
 * @author RealSlimShady
 * The Oval class implements Oval objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 * is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 *
 */
public class Oval implements RenderableShape {
	private Point centre; 

	
	private int x;		// Top left point of rectangle to draw circle
	private int y;
	int stroke;
	private int height;
	private int width;

	
	/**
	 * Constructs an oval object given a center Point,the radius and the width of the oval.
	 * 
	 * @param centre centre Point of the oval.
	 * @param height height of the oval.
	 * @param width width of the oval.
	 */
	public Oval(Point centre, int height, int width){
		this.centre = centre;
		this.height = height;
		this.width = width;
	}

	/**
	 * Returns the center point of the oval.
	 * 
	 * @return center Point of the oval.
	 */
	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	/**
	 * Returns the height of the oval.
	 * 
	 * @return the height of the oval.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height of the oval to the given height.
	 * 
	 * @param height integer value of the height the Oval will have.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	/**
	 * Returns the width of the oval.
	 * 
	 * @return the width of the oval.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width of the oval to the given width value.
	 * 
	 * @param width width of the oval the Oval will have.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Sets the x value of the coordinate for the left point of the rectangle to draw oval.
	 * 
	 * @param newX x value of the coordinate for the left point of the rectangle to draw oval.
	 */
	public void setX(int newX) {
		this.x = newX;
	}
	
	/**
	 * Returns the x value of the coordinate for the left point of the rectangle to draw oval.
	 * 
	 * @return x value of the coordinate for the left point of the rectangle to draw oval.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the y value of the coordinate for the left point of the rectangle to draw oval.
	 * 
	 * @param newY y value of the coordinate for the left point of the rectagle to draw oval.
	 */
	public void setY(int newY) {
		this.y = newY;
	}
	
	/**
	 * Sets the y value of the coordinate for the left point of the rectangle to draw oval.
	 * 
	 * @return y value of the coordinate for the left point of the rectagle to draw oval.
	 */
	public int getY() {
		return y;
	}
	
}
