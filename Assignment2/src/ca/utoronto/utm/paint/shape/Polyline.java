package ca.utoronto.utm.paint.shape;

import java.util.ArrayList;
import java.util.List;

public class Polyline implements RenderableShape {
	
private List<Point> points;
	
	public Polyline(List<Point> points) {
		this.points = points;
	}
	
	public List<Point> getPoints() {
		return this.points;
	}
	
	public void replaceLastPoint(Point point) {
		int pointCounts = this.points.size();
		if (pointCounts == 0) {
			return; // not clicked any point yet, ignore this event
		} else if (pointCounts == 1) {
			// add a point
			addPoint(point);
		} else {
			// replace last point
			this.points.set(pointCounts - 1, point);
		}
	}
	
	public void removeLastPoint() {
		int pointCounts = this.points.size();
		if (pointCounts > 0) {
			this.points.remove(pointCounts - 1);
		}
	}
	
	public void addPoint(Point point) {
		this.points.add(point);
	}

	public void setPoints(ArrayList<Point> points)
	{
		this.points = points;
	}

}
