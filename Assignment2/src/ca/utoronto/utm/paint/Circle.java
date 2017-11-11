package ca.utoronto.utm.paint;

import java.awt.Color;

import ca.utoronto.utm.paint.RenderableShape;

public class Circle implements RenderableShape {
	private Point centre; 	// holds point of mouse click when user starts dragging
	private int radius;
	Color color;
	Color fillColor;
	boolean fillState = false;

	// TODO: Use Point class
	private int x;		// Top left point of rectangle to draw circle
	private int y;
	int stroke;

	
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
		this.color = color;
		this.fillColor = fillColor;
		this.stroke = stroke;
	}

	public Point getCentre() {
		return centre;
	}

	public void setCentre(Point centre) {
		this.centre = centre;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setFillState(boolean bool) {
		this.fillState = bool;
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
