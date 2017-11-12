package ca.utoronto.utm.paint.shape;

public class Rectangle implements RenderableShape {
	private int width;
	private int height;
	private Point origin;	// holds point of mouse click when user starts dragging
	private Point renderTopLeftPoint;
	
	public Rectangle(Point origin, int width, int height) {
		this.origin = origin;
		setRenderTopLeftPoint(new Point(origin));
		this.width = width;
		this.height = height;		
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public Point getOrigin() {
		return this.origin;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Point getRenderTopLeftPoint() {
		return renderTopLeftPoint;
	}

	public void setRenderTopLeftPoint(Point point) {
		this.renderTopLeftPoint = point;
	}
	
	public void setOrigin(Point origin) {
		this.origin = origin;
	}

}
