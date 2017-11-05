package ca.utoronto.utm.paint;

import java.awt.Color;

public class Square {
	private int sideLength;
	Color color;
	Color fillColor;
	boolean fillState = false;
	private Point origin;
	private int width;
	private int height;
	int stroke;
	
	public Square(Point origin, int width, int height) {
		this.origin = origin;
		this.color = color;
		this.width = width;
		this.height = height;
		this.fillColor = fillColor;
		this.stroke = stroke;
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
	
	public void setFillState(boolean bool) {
		this.fillState = bool;
	}
}
