package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Circle;
import ca.utoronto.utm.paint.shape.RenderableShape;

public class CircleDrawingCommand implements DrawingCommand {
	
private static final Logger LOG = Logger.getLogger(CircleDrawingCommand.class.getName());
	
	private final Circle circle;
	private RenderingParameters renderingParams;
	
	public CircleDrawingCommand(Circle circle, RenderingParameters renderingParams)
	{
		this.circle = circle;
		this.renderingParams = renderingParams;
	}
	
	@Override
	public void setRenderingParameters(RenderingParameters renderingParameters)
	{
		this.renderingParams = renderingParameters;
	}
	
	@Override
	public RenderableShape getShape()
	{
		return circle;
	}

	@Override
	public void render(Graphics g)
	{
		LOG.fine("render circle=" + circle.getRadius());
		
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		
		g2d.setColor(renderingParams.getColor());
		int x = circle.getCentre().getX();
		int y = circle.getCentre().getY();
		int radius = circle.getRadius();
		// we want x and y coordinates for drawOval
		int topX = x - radius;
		int topY = y - radius;
		if (renderingParams.getFillState() == "Solid") {
			g2d.fillOval(topX, topY, radius*2, radius*2);
			
		} else if (renderingParams.getFillState() == "Outline") {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
			g2d.drawOval(topX, topY, radius*2, radius*2);
			
		}
	}

}
