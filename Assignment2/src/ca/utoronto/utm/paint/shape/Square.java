package ca.utoronto.utm.paint.shape;

public class Square implements RenderableShape {
	private int sideLength;
	private Point origin;
	private Point renderTopLeftPoint;
	private int width;
	private int height;
	
	public Square(Point origin, int width, int height) {
		this.origin = origin;
		setRenderTopLeftPoint(new Point(origin));
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
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

	public int getSideLength() {
		return sideLength;
	}

	public void setSideLength(int sideLength) {
		this.sideLength = sideLength;
	}

}
