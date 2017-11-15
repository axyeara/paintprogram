package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Triangle;
import ca.utoronto.utm.paint.shape.RenderableShape;

public class TriangleDrawingCommand implements DrawingCommand {
	
private static final Logger LOG = Logger.getLogger(TriangleDrawingCommand.class.getName());
	
	private final Triangle triangle;
	private RenderingParameters renderingParams;
	
	public TriangleDrawingCommand(Triangle triangle, RenderingParameters renderingParams) {
		this.triangle = triangle;
		this.renderingParams = renderingParams;
	}
	
	@Override
	public void setRenderingParameters(RenderingParameters renderingParameters) {
		this.renderingParams = renderingParameters;
	}
	
	@Override
	public RenderableShape getShape() {
		return triangle;
	}

	@Override
	public void render(Graphics g) {
		//LOG.fine("render rectangle=" + rect.getRenderTopLeftPoint() + ", w=" + rect.getWidth() + ", h=" + rect.getHeight());
		
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		
		g2d.setColor(renderingParams.getColor());
		
		int[] xPoints = {this.triangle.getOrigin().getX(),this.triangle.getP1().getX(),this.triangle.getP2().getX()};
		int[] yPoints = {this.triangle.getOrigin().getY(),this.triangle.getP1().getY(),this.triangle.getP2().getY()};

		if (renderingParams.getFillState() == "Solid") {
			g2d.fillPolygon(xPoints, yPoints, 3);
		} else {
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
			g2d.drawPolygon(xPoints, yPoints, 3);
		}
	}

}
