package ca.utoronto.utm.paint.render;

import java.awt.Color;

// 2.1 
// 2.2  handle changes to the graphics parameter
// which is why used in drawing command
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
