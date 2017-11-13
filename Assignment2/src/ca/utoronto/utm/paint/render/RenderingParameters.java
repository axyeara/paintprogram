package ca.utoronto.utm.paint.render;

import java.awt.Color;

// 2.1 
// 2.2  handle changes to the graphics parameter
// which is why used in drawing command
public class RenderingParameters {
	
	private static final Color DEFAULT_LINE_COLOR = Color.RED;
	private static final int DEFAULT_STROKE = 25;
	private static final Color DEFAULT_FILL_COLOR = Color.RED;
	
	private Color color = DEFAULT_LINE_COLOR;
	private int stroke = DEFAULT_STROKE;
	// to draw fill
	private Color fillColor = DEFAULT_FILL_COLOR;
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
	
	public RenderingParameters copy() {
		RenderingParameters renderingParams = new RenderingParameters();
		renderingParams.setColor(this.color);
		renderingParams.setStroke(this.stroke);
		renderingParams.setFillColor(this.fillColor);
		renderingParams.setFillState(this.fillState);
		return renderingParams;
	}

}
