package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.Observable;

public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	private ArrayList<Shape> history = new ArrayList<Shape>();

	public void addShape(Shape shape) {
		this.history.add(shape);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return this.history;
	}
	
	public void clearPoints() {
		this.points.clear();
	}
	
	public void addPoint(Point p){
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	}
	public ArrayList<Point> getPoints(){
		return points;
	}
	
}
