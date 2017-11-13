package ca.utoronto.utm.paint.wip;

import java.awt.Color;

public class Rectangle extends Shape {
	private Point corner;
	private int width;
	private int height;
	
	public Rectangle(Point corner, int width, int height, Color color, String fillState, int lineThickness) {
		super(color, fillState, lineThickness);
		this.corner = corner;
		this.width = width;
		this.height = height;
	}

	public Point getCorner() {
		return corner;
	}

	public void setCorner(Point corner) {
		this.corner = corner;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
}

