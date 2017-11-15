package ca.utoronto.utm.paint.shape;

/**
 * @author RealSlimShady
 * The Rectangle class implements Rectangle objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 *is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 */
public class Rectangle implements RenderableShape {
	private int width;
	private int height;
	private Point origin;	// holds point of mouse click when user starts dragging
	private Point renderTopLeftPoint;
	
	/**
	 * Constructs a rectangle object given its point of origin, width and height.
	 * @param origin origin of creation of rectangle.
	 * @param width width of the rectangle.
	 * @param height height of the rectangle.
	 */
	public Rectangle(Point origin, int width, int height) {
		this.origin = origin;
		setRenderTopLeftPoint(new Point(origin));
		this.width = width;
		this.height = height;		
	}
	
	/**
	 * @return height of the rectangle.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * @return width of the rectangle.
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return point of origin of the rectangle.
	 */
	public Point getOrigin() {
		return this.origin;
	}
	/**
	 * sets the width of the rectangle.
	 * 
	 * @param width width of the rectangle.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	/**
	 * sets the height of the rectangle.
	 * @param height height of the rectangle.
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return top left point of the rectangle.
	 */
	public Point getRenderTopLeftPoint() {
		return renderTopLeftPoint;
	}

	/**
	 * sets the top left point
	 * @param point topleft point
	 */
	public void setRenderTopLeftPoint(Point point) {
		this.renderTopLeftPoint = point;
	}
	
	public void setOrigin(Point origin) {
		this.origin = origin;
	}

}
