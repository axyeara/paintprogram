package ca.utoronto.utm.paint;

import java.awt.Color;

public class Square {
	private int sideLength;
	Color color;
	private Point origin;
	private int width;
	private int height;
	
	public Square(Point origin, int width, int height) {
		this.origin = origin;
		this.color = color;
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
}
