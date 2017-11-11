package ca.utoronto.utm.paint;

import java.awt.Color;

public class RenderingParameters {
	
	private Color color = Color.RED;
	private int stroke;
	// to draw fill
	private Color fillColor;
	private boolean fillState = false;
	
	public Color getColor()
	{
		return color;
	}
	public void setColor(Color lineColor)
	{
		this.color = lineColor;
	}
	public int getStroke()
	{
		return stroke;
	}
	public void setStroke(int stroke)
	{
		this.stroke = stroke;
	}
	public Color getFillColor()
	{
		return fillColor;
	}
	public void setFillColor(Color fillColor)
	{
		this.fillColor = fillColor;
	}
	public boolean isFillState()
	{
		return fillState;
	}
	public void setFillState(boolean fillState)
	{
		this.fillState = fillState;
	}

}
