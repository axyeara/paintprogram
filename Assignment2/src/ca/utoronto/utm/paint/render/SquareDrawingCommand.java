package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.RenderableShape;
import ca.utoronto.utm.paint.shape.Square;

public class SquareDrawingCommand implements DrawingCommand {
	
	private static final Logger LOG = Logger.getLogger(SquareDrawingCommand.class.getName());

	private final Square square;
	private RenderingParameters renderingParams;

	public SquareDrawingCommand(Square square, RenderingParameters renderingParams)
	{
		this.square = square;
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
		return square;
	}

	@Override
	public void render(Graphics g)
	{
		LOG.fine("render square");

		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api

		g2d.setColor(renderingParams.getColor());

		Point renderTopLeftP = square.getRenderTopLeftPoint();
		int x = renderTopLeftP.getX();
		int y = renderTopLeftP.getY();
		int height = square.getHeight();
		int width = square.getWidth();
		int length = Math.max(height, width);
		if (renderingParams.getFillState() == "Solid") {
			g2d.fillRect(x, y, length, length);
		}
		else {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
			g2d.drawRect(x, y, length, length);
		}

	}

}
