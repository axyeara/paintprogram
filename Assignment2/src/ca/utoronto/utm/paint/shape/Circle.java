package ca.utoronto.utm.paint.shape;

/**
 * @author RealSlimShady
 *The Circle class implements circle objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 *is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 */
public class Circle implements RenderableShape {
	private Point centre; 	
	private int radius;


	private int x;		
	private int y;
	int stroke; // line thickness of the circle.

	
	/**
	 * Constructs a circle object given a center Point and the radius of the circle.
	 * 
	 * @param centre centre Point of the circle.
	 * @param radius radius of the circle.
	 */
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
	}

	/**
	 * Returns the coordinate of the circle in the PaintPanel.
	 * 
	 * @return Centre coordinate of the circle in the PaintPanel.
	 */
	public Point getCentre() {
		return centre;
	}

	/**
	 * Sets the center of the circle to the given centre Point.
	 * 
	 * @param centre Center coordinate of the circle in the PaintPanel.
	 */
	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	/**
	 * Sets the radius of the circle to the given radius Point.
	 * 
	 * @param radius Radius of the circle in the PaintPanel.
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	/**
	 * Sets the x value of the coordinate for the left point of the rectangle to draw circle.
	 * 
	 * @param newX x value of the coordinate for the left point of the rectangle to draw circle.
	 */
	public void setX(int newX) {
		this.x = newX;
	}
	
	/**
	 * Returns the x value of the coordinate for the left point of the rectangle to draw circle.
	 * 
	 * @return x value of the coordinate for the left point of the rectangle to draw circle.
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Sets the x value of the coordinate for the left point of the rectangle to draw circle.
	 * 
	 * @param newY y value of the coordinate for the left point of the rectangle to draw circle.
	 */
	public void setY(int newY) {
		this.y = newY;
	}
	
	/**
	 * Returns the y value of the coordinate for the left point of the rectangle to draw circle.
	 * 
	 * @return y value of the coordinate for the left point of the rectangle to draw circle.
	 */
	public int getY() {
		return y;
	}
	
}
