package ca.utoronto.utm.paint.shape;

/**
 * @author RealSlimShady
 *The Square class implements Square objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 *is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 */
public class Square implements RenderableShape {
	private int sideLength;
	private Point origin;
	private Point renderTopLeftPoint;
	private int width;
	private int height;
	
	/**
	 * 
	 * creates square objects
	 * 
	 * @param origin origin of the square
	 * @param width width of square
	 * @param height height of square
	 */
	public Square(Point origin, int width, int height) {
		this.origin = origin;
		setRenderTopLeftPoint(new Point(origin));
		this.width = width;
		this.height = height;
	}
	
	/**
	 * @return width of square
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return height of square
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * @return origin of square
	 */
	public Point getOrigin() {
		return this.origin;
	}
	
	/** sets width of square
	 * @param width new width of square
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * sets height of square
	 * @param height height of square
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	/**
	 * @return top left point of square
	 */
	public Point getRenderTopLeftPoint() {
		return renderTopLeftPoint;
	}

	/**
	 * @param point
	 */
	public void setRenderTopLeftPoint(Point point) {
		this.renderTopLeftPoint = point;
	}

	/**
	 * @return side length of square
	 */
	public int getSideLength() {
		return sideLength;
	}

	/**
	 * sets the side length of square
	 * 
	 * @param sideLength side length of square
	 */
	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

}
