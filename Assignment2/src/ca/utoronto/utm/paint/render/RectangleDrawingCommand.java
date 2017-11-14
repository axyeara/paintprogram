package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Rectangle;
import ca.utoronto.utm.paint.shape.RenderableShape;

public class RectangleDrawingCommand implements DrawingCommand {
	
private static final Logger LOG = Logger.getLogger(RectangleDrawingCommand.class.getName());
	
	private final Rectangle rect;
	private RenderingParameters renderingParams;
	
	public RectangleDrawingCommand(Rectangle rect, RenderingParameters renderingParams) {
		this.rect = rect;
		this.renderingParams = renderingParams;
	}
	
	@Override
	public void setRenderingParameters(RenderingParameters renderingParameters) {
		this.renderingParams = renderingParameters;
	}
	
	@Override
	public RenderableShape getShape() {
		return rect;
	}

	@Override
	public void render(Graphics g) {
		LOG.fine("render rectangle=" + rect.getRenderTopLeftPoint() + ", w=" + rect.getWidth() + ", h=" + rect.getHeight());
		
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		
		g2d.setColor(renderingParams.getColor());
		Point renderTopLeftP = rect.getRenderTopLeftPoint();
		int x = renderTopLeftP.getX();
		int y = renderTopLeftP.getY();
		int height = rect.getHeight();
		int width = rect.getWidth();
		if (renderingParams.getFillState() == "Solid") {
			g2d.fillRect(x, y, width, height);
		} else {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
			g2d.drawRect(x, y, width, height);
		}
	}

}
