package ca.utoronto.utm.paint;

import java.awt.Color;

public class Circle {
	private Point centre;
	private int radius;
	Color color;
	
	public Circle(Point centre, int radius){
		this.centre = centre;
		this.radius = radius;
		this.color = color;
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

}
