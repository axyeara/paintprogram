package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.SquareDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Square;

/**
 * Concrete class for ShareManipulatorStrategy (Square).
 * Contains the drawing parameters for square and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
public class SquareShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(SquareShapeManipulatorStrategy.class.getName());

	// shape
	private Square square;

	public SquareShapeManipulatorStrategy(PaintPanel paintPanel)
	{
		LOG.fine("new SquareShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}
	
	@Override
	public void reset()
	{
		this.square = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) 
	{
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		Point origin = new Point(e.getX(), e.getY());
		int width = 0;
		int height = 0;
		this.square = new Square(origin, 0, 0);

		draggingDrawCmd = new SquareDrawingCommand(square, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseDraggedUpdateShape square");
		doUpdateCoordByEvent(e);
		return true; // notify observers
		
	}

	protected boolean doMouseReleasedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseReleased square=");
		doUpdateCoordByEvent(e);
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

	private void doUpdateCoordByEvent(MouseEvent e)
	{
		Point renderTopLeftP = this.square.getRenderTopLeftPoint();
		Point dragStartOrigin = this.square.getOrigin();
		// set width
		int width = Math.abs(dragStartOrigin.getX() - e.getX());
		this.square.setWidth(width);
		// set height
		int height = Math.abs(dragStartOrigin.getY() - e.getY());
		this.square.setHeight(height);
		
		// make width and height the same length
		int squareLength = Math.max(width, height);
		
		// set renderTopLeftP x
		if (e.getX() > dragStartOrigin.getX()) {
			renderTopLeftP.setX(dragStartOrigin.getX());
		} else {
			// calculate top x of square by squareLength
			int topLeftX = dragStartOrigin.getX() - squareLength;
			renderTopLeftP.setX(topLeftX);
		}
		// set renderTopLeftP y
		if (e.getY() > dragStartOrigin.getY()) {
			renderTopLeftP.setY(dragStartOrigin.getY());
		} else {
			// calculate top y of square by squareLength
			int topLeftY = dragStartOrigin.getY() - squareLength;
			renderTopLeftP.setY(topLeftY);
		}
	}

}
