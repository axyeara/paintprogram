package ca.utoronto.utm.paint.shape;

/**
 * @author RealSlimShady
 *The Point class implements Point objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 *is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 */
public class Point implements RenderableShape {
	private int x, y;
	
	/**
	 * Constructs a Point object given its X and Y coordinates.
	 * @param x x value for Point coordinate
	 * @param y y value for Point coordinate
	 */
	public Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	/**
	 * Replaces current Point to another Point by changing its X and Y coordinates.
	 * @param p
	 */
	Point(Point p) {
		this.x=p.getX(); this.y=p.getY();
	}
	
	/**
	 * Returns the X coordinate.
	 * 
	 * @return X coordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the X coordinate.
	 * 
	 * @param x new X coordinate that is being set.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Returns the X coordinate.
	 * @return Y coordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the Y coordinate.
	 * 
	 * @param y new Y coordinate being set.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/* 
	 * Returns a string representation of the Point object.
	 */
	public String toString() {
		return "[" + x + ", " + y + "]";
	}
	
}
