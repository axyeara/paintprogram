package ca.utoronto.utm.paint;

import java.awt.Color;
import java.util.ArrayList;

public class Squiggle extends Shape{
	private ArrayList<Point> points;
	
	public Squiggle(Point point, Color color, String fillState, int lineThickness) {
		super(color, fillState, lineThickness);
		this.points = new ArrayList<Point>();
		this.points.add(point);
	}
	
	public int getSize() {
		return points.size();
	}
	
	public ArrayList<Point> getSquigglePoints(){
		return this.points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
	public Point getPoint(int index) {
		return points.get(index);
	}
	

}
