package ca.utoronto.utm.paint;

import java.awt.Color;

public class Circle {
	private Point centre;
	private int radius;
	Color color;
	Color fillColor;
	boolean fillState = false;
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
}
