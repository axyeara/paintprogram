package ca.utoronto.utm.paint.shape;

import java.util.ArrayList;

public class Squiggle implements RenderableShape {
	
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
	
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}

}
