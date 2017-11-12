package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.RenderableShape;
import ca.utoronto.utm.paint.shape.Squiggle;

public class SquiggleDrawingCommand implements DrawingCommand {
	
	private static final Logger LOG = Logger.getLogger(SquiggleDrawingCommand.class.getName());

	private final Squiggle squiggle;
	private RenderingParameters renderingParams;

	public SquiggleDrawingCommand(Squiggle squiggle, RenderingParameters renderingParams)
	{
		this.squiggle = squiggle;
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
		return squiggle;
	}

	@Override
	public void render(Graphics g)
	{
		LOG.fine("render squiggle");

		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api

		g2d.setColor(renderingParams.getColor());

		for(int i=0; i< squiggle.getPoints().size()-1; i++) {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));

			Point p1=squiggle.getPoints().get(i);
			Point p2=squiggle.getPoints().get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}

	}

}
