package ca.utoronto.utm.paint.wip;

import java.awt.Color;

public class Circle extends Shape{
	private Point centre;
	private int radius;

	
	public Circle(Point centre, int radius, Color color, String fillState, int lineThickness){
		super(color, fillState, lineThickness);
		this.centre = centre;
		this.radius = radius;
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


	public Point getCentre() {
		return centre;
	}
	


}
