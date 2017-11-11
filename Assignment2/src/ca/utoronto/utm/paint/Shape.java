package ca.utoronto.utm.paint;

import java.awt.Color;

public abstract class Shape {
	private Color color;
	private String fillState;
	private int lineThickness;
	
	public Shape(Color color, String fillState, int lineThickness) {
		this.color = color;
		this.fillState = fillState;
		this.lineThickness = lineThickness;
	}
	public Color getColor() {
		return this.color;
	}
	
	public String getFillState() {
		return this.fillState;
	}
	
	public int getLineThickness() {
		return this.lineThickness;
	}
}
