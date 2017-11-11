package ca.utoronto.utm.paint;

import java.awt.Color;

public class Circle extends Shape{
	private Point centre;
	private int radius;
	
	/**
	 * the constructor of class circle 
	 * @param centre Point center of the circle
	 * @param radius int radius of the circle 
	 * @param color color of this circle
	 * @param writeMode
	 */
	
	public Circle(Point centre, int radius, Color color, String fillState, int lineThickness){
		super(color, fillState, lineThickness);
		this.centre = centre;
		this.radius = radius;
	}
	
	/**
	 * get the center Point of this circle 
	 * @return centre (of this circle)
	 */

	public Point getCentre() {
		return centre;
	}
	
	/**
	 * set the center Point of this circle 
	 * @param centre this circle's center
	 * @return Nothing
	 */

	public void setCentre(Point centre) {
		this.centre = centre;
	}
	
	/**
	 * get the radius of this circle
	 * @return int radius
	 */
	public int getRadius() {
		return radius;
	}
	/**
	 *  set the raidus of this circle 
	 * @param radius int this circle's radius 
	 * @return Nothign
	 */

	public void setRadius(int radius) {
		this.radius = radius;
	}

}
