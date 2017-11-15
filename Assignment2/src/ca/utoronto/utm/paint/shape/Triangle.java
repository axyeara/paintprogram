package ca.utoronto.utm.paint.shape;

public class Triangle implements RenderableShape {
	private Point p1;
	private Point p2;
	private Point origin;	
	
	
	public Triangle(Point origin, Point p1, Point p2) {
		this.origin = origin;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	
	public void setP1(Point p1) {
		this.p1 = p1;
	}
	public Point getP1() {
		return this.p1;
	}
	
	public void setOrigin(Point origin) {
		this.origin = origin;
	}
	public Point getP2() {
		return this.p2;
	}
	
	public void setP2(Point p2) {
		this.p2 = p2;
	}

}
