package ca.utoronto.utm.paint.shape;

import java.util.ArrayList;
import java.util.List;

public class Squiggle implements RenderableShape {
	
	private List<Point> points;
	
	public Squiggle(List<Point> points) {
		this.points = points;
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
}
