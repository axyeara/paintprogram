package ca.utoronto.utm.paint.shape;

public class Triangle implements RenderableShape {
	private Point p1;
	private Point p2;
	private Point origin;	
	
	
	/**
	 * Creates a triangle object 
	 * 
	 * @param origin
	 * @param p1
	 * @param p2
	 */
	public Triangle(Point origin, Point p1, Point p2) {
		this.origin = origin;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	/**
	 * @return origin of triangle
	 */
	public Point getOrigin() {
		return this.origin;
	}
	
	/**
	 * sets p1 of triangle
	 * @param p1
	 */
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	/**
	 * @return p1 of triangle
	 */
	public Point getP1() {
		return this.p1;
	}
	
	/**
	 * sets the origin of the triangle
	 * @param origin
	 */
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
	/**
	 * @return p2 of the triangle.
	 */
	public Point getP2() {
		return this.p2;
	}
	
	/**
	 * sets p2 of the triangle.
	 * @param p2
	 */
	public void setP2(Point p2) {
		this.p2 = p2;
	}

}
