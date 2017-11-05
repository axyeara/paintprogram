package ca.utoronto.utm.paint;

import java.util.ArrayList;

public class Squiggle {
	private ArrayList<Point> points;
	
	public Squiggle(ArrayList<Point> points) {
		this.points = points;
	}
	
	public ArrayList<Point> getPoints() {
		return this.points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
}
