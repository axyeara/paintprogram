package ca.utoronto.utm.paint.render;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Polyline;
import ca.utoronto.utm.paint.shape.RenderableShape;

public class PolylineDrawingCommand implements DrawingCommand {
	
	private static final Logger LOG = Logger.getLogger(PolylineDrawingCommand.class.getName());

	private final Polyline polyine;
	private RenderingParameters renderingParams;

	public PolylineDrawingCommand(Polyline polyine, RenderingParameters renderingParams) {
		this.polyine = polyine;
		this.renderingParams = renderingParams;
	}

	@Override
	public void setRenderingParameters(RenderingParameters renderingParameters) {
		this.renderingParams = renderingParameters;
	}

	@Override
	public RenderableShape getShape() {
		return polyine;
	}

	@Override
	public void render(Graphics g) {
		LOG.fine("render polyine: point-count = " + polyine.getPoints().size());

		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api

		g2d.setColor(renderingParams.getColor());
		g2d.setStroke(new BasicStroke(renderingParams.getStroke()));

		List<Point> points = polyine.getPoints();
		for(int i=0; i< points.size()-1; i++) {
			Point p1=points.get(i);
			Point p2=points.get(i+1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}

}
