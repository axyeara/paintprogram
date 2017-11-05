package ca.utoronto.utm.paint;

import java.awt.Color;

public class Rectangle {
	private int width;
	private int height;
	private Point origin;
	Color color;
	
	public Rectangle(Point origin, int width, int height) {
		this.origin = origin;
		this.width = width;
		this.height = height;
		this.color = color;
		
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

}
