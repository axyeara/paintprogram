package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class Squiggle {
	Color color;
	Color fillColor;
	private ArrayList<Point> points;
	
	public Squiggle(ArrayList<Point> points) {
		this.points = points;
		this.color = color;
		this.fillColor = fillColor;
	}
	
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
}
