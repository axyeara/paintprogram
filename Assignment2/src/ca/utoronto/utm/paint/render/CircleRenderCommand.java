package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

// concrete command
public class CircleRenderCommand implements DrawingCommand{
	
private static final Logger LOG = Logger.getLogger(CircleRenderCommand.class.getName());
	
	private final Circle circle;
	private RenderingParameters renderingParams;
	private boolean dragging;
	
	public CircleRenderCommand(Circle circle, RenderingParameters renderingParams)
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
	public RenderingParameters getRenderingParameters()
	{
		return renderingParams;
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
		if (renderingParams.isFillState()) {
			g2d.setPaint(renderingParams.getFillColor());
			g2d.fillOval(topX, topY, radius*2, radius*2);
			
		} else {
			g2d.drawOval(topX, topY, radius*2, radius*2);
			g2d.setStroke(new BasicStroke(renderingParams.getStroke()));
		}
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		LOG.fine("mouseDragged circle=" + circle.getRadius());
		// get coordinates for center of circle
		int xCoord = Math.abs(e.getX() - this.circle.getCentre().getX());
		int yCoord = Math.abs(e.getY() - this.circle.getCentre().getY());
		
		// solve for correct value of radius using distance formula
		int xSq = (int) Math.pow(xCoord, 2);
		int ySq = (int) Math.pow(yCoord, 2);
		int radius = (int) Math.sqrt(xSq + ySq);
		this.circle.setRadius(radius);
		
		// set coordinates for circle that is logical for drawOval
		this.circle.setX(this.circle.getCentre().getX() - this.circle.getRadius());
		this.circle.setY(this.circle.getCentre().getY() - this.circle.getRadius());
		
		// this will be rendered by render() method
		
//			this.circle.setColor(this.lineColor);
//			if (this.fillState) {
//				this.circle.setFillColor(this.fillColor);
//				this.circle.setFillState(true);
//			}
//	
//			this.circle.setStroke(this.lineThickness);
		
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		LOG.fine("mouseReleased circle=" + circle.getRadius());
		// Problematic notion of radius and centre!!
		// get coordinates for center of circle
		int xCoord = Math.abs(e.getX() - this.circle.getCentre().getX());
		int yCoord = Math.abs(e.getY() - this.circle.getCentre().getY());
		
		// solve for correct value of radius using distance formula
		int xSq = (int) Math.pow(xCoord, 2);
		int ySq = (int) Math.pow(yCoord, 2);
		int radius = (int) Math.sqrt(xSq + ySq);
		this.circle.setRadius(radius);
		
		// set coordinates for circle that is logical for drawOval
		this.circle.setX(this.circle.getCentre().getX() - this.circle.getRadius());
		this.circle.setY(this.circle.getCentre().getY() - this.circle.getRadius());
		
//		this.circle.setColor(this.lineColor);
//		if (this.fillState) {
//			this.circle.setFillColor(this.fillColor);
//			this.circle.setFillState(true);
//		}
//
//		this.circle.setStroke(this.lineThickness);
//		// 1. We must addCircle() to render shape
//		// 2. we will replace the last object in the collection in the model to reduce duplication
//		// 3. we will add the shape object on mousePressed to guarantee this logic
//		this.model.getCircles().remove(this.model.getCircles().size() - 1);
//		this.model.addCircle(this.circle);
//		this.circle=null;
	}

	@Override
	public void setDragging(boolean dragging)
	{
		this.dragging = dragging;
	}
	
	@Override
	public boolean isDragging()
	{
		// TODO Auto-generated method stub
		return dragging;
	}

}
