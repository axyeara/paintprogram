package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;

/**
 * Concrete class for ShareManipulatorStrategy (Squiggle).
 * Contains the drawing parameters for squiggle and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
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
	
	@Override
	public void reset()
	{
		this.squiggle = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) 
	{
		LOG.fine("doMousePressedSetDraggingDrawCmd");

		this.squiggle = new Squiggle(new ArrayList<Point>());
		this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		
		draggingDrawCmd = new SquiggleDrawingCommand(squiggle, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseDraggedUpdateShape squiggle");

		this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		return true; // notify observers
	}

	protected boolean doMouseReleasedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseReleased squiggle");

		this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

}
