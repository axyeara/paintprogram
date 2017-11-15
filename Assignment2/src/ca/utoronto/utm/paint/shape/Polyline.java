package ca.utoronto.utm.paint.shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author RealSlimShady
 * The Polyline class implements Polyline objects and related methods. This class implements the RenderableShape interface. The RenderableShape interface
 *is in charge of assigning a string label to the circle object so that it can be accessed and identified by the ShapeManipulatorStrategyFactory class.
 */
public class Polyline implements RenderableShape {
	
	private List<Point> points;
	
	/**
	 * Creates Polyline object given the list of points it has.
	 * 
	 * @param points
	 */
	public Polyline(List<Point> points) {
		this.points = points;
	}
	
	/**
	 * @return list of points in Polyline.
	 */
	public List<Point> getPoints() {
		return this.points;
	}
	
	/**
	 * Replaces the last point in the list of points in squiggle line to the point given.
	 * 
	 * @param point Point that replaces the last Point.
	 */
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
	
	/**
	 * Removes the last point in the Polyline.
	 */
	public void removeLastPoint() {
		int pointCounts = this.points.size();
		if (pointCounts > 0) {
			this.points.remove(pointCounts - 1);
		}
	}
	
	/**
	 * Adds Point to the polyline.
	 * @param point Point to be added to the polyline.
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}

	/** Sets the points that composes the polyline to a new set of points.
	 * @param points set points that replaces the current set of points in polyline object.
	 */
	public void setPoints(ArrayList<Point> points)
	{
		this.points = points;
	}

}
