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
}
