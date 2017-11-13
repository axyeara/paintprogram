package ca.utoronto.utm.paint.wip;

import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.wip.Point;
import ca.utoronto.utm.paint.wip.Shape;

public class PaintModel extends Observable {
	private ArrayList<Point> points=new ArrayList<Point>();
	
	private ArrayList<Shape> shapeStack = new ArrayList<Shape>();

	public void addShape(Shape shape) {
		this.shapeStack.add(shape);
		this.setChanged();
		this.notifyObservers();
	}
	
	public ArrayList<Shape> getShapes(){
		return this.shapeStack;
	}
	
	public void addPoint(Point p){
		this.points.add(p);
		this.setChanged();
		this.notifyObservers();
	} 
	
	public void clearPoints() {
		this.points.clear();
	}
	
	
	public ArrayList<Point> getPoints(){
		return points;
	}
	
	public void replaceLastPoint(Point p) {
		if(points.size() >= 2) {
			this.points.set(points.size()-1, p);
			this.setChanged();
			this.notifyObservers();
		}
	}	
}
