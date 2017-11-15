package ca.utoronto.utm.paint.shape;

public class Oval implements RenderableShape {
	private Point centre; 	// holds point of mouse click when user starts dragging

	// TODO: Use Point class
	private int x;		// Top left point of rectangle to draw circle
	private int y;
	int stroke;
	private int height;
	private int width;

	
	public Oval(Point centre, int height, int width){
		this.centre = centre;
		this.height = height;
		this.width = width;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public int getX() {
		return x;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public int getY() {
		return y;
	}
	
}
