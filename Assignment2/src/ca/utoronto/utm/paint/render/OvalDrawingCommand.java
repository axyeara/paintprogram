package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Oval;
import ca.utoronto.utm.paint.shape.RenderableShape;

public class OvalDrawingCommand implements DrawingCommand {
	
private static final Logger LOG = Logger.getLogger(OvalDrawingCommand.class.getName());
	
	private final Oval oval;
	private RenderingParameters renderingParams;
	
	public OvalDrawingCommand(Oval oval, RenderingParameters renderingParams)
	{
		this.oval = oval;
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
		return oval;
	}

	@Override
	public void render(Graphics g)
	{
		LOG.fine("render oval=" + oval.getWidth() + ":" + oval.getHeight());
		
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		
		g2d.setColor(renderingParams.getColor());
		int x = oval.getCentre().getX();
		int y = oval.getCentre().getY();
		int width = oval.getWidth();
		int height = oval.getHeight();
		// we want x and y coordinates for drawOval
		int topX = x - width;
		int topY = y - height;
		if (renderingParams.getFillState() == "Solid") {
			g2d.fillOval(topX, topY, width*2, height*2);
			
		} else if (renderingParams.getFillState() == "Outline") {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
			g2d.drawOval(topX, topY, width*2, height*2);
			
		}
	}

}
