package ca.utoronto.utm.paint.shape;

import java.util.List;

/**
 * @author RealSlimShady
 *
 */
public class Squiggle implements RenderableShape {
	
	private List<Point> points;
	
	/**
	 * creates a squiggle object
	 * @param points List of points.
	 */
	public Squiggle(List<Point> points) {
		this.points = points;
	}
	
	/**
	 * @return list of points in squiggle
	 */
	public List<Point> getPoints() {
		return this.points;
	}
	
	/** adds points to the list of squiggles.
	 * @param point point to be added
	 */
	public void addPoint(Point point) {
		this.points.add(point);
	}
	
}
