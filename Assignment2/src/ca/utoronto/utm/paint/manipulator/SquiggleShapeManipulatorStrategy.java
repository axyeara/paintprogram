package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.SquiggleDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Squiggle;

public class SquiggleShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(SquiggleShapeManipulatorStrategy.class.getName());

	// shape
	private Squiggle squiggle;

	public SquiggleShapeManipulatorStrategy(PaintPanel paintPanel)
	{
		LOG.fine("new SquiggleShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}

	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) 
	{
		LOG.fine("doMousePressedSetDraggingDrawCmd");

		this.squiggle = new Squiggle(new ArrayList<Point>());

		draggingDrawCmd = new SquiggleDrawingCommand(squiggle, paintPanel.toRenderingParameters());
	}

	protected void doMouseDraggedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseDraggedUpdateShape squiggle");

		this.squiggle.addPoint(new Point(e.getX(), e.getY()));
	}

	protected void doMouseReleasedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseReleased squiggle");

		this.squiggle.addPoint(new Point(e.getX(), e.getY()));
	}

	@Override
	public void setDragging(boolean dragging)
	{
		this.dragging = dragging;

		if (!dragging) {
			draggingDrawCmd = null;
		}
	}

	@Override
	public boolean isDragging()
	{
		// TODO Auto-generated method stub
		return dragging;
	}

}
