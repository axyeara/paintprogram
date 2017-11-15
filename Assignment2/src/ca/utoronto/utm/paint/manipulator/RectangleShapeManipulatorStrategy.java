package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;

/**
 * Concrete class for ShareManipulatorStrategy (Rectangle).
 * Contains the drawing parameters for rectangle and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.RectangleDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Rectangle;

public class RectangleShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(RectangleShapeManipulatorStrategy.class.getName());

	// shape
	private Rectangle rect;

	public RectangleShapeManipulatorStrategy(PaintPanel paintPanel) {
		LOG.fine("new RectangleShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}
	
	@Override
	public void reset() {
		this.rect = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) {
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		// Problematic notion of radius and centre!!
		Point origin = new Point(e.getX(), e.getY());
		int height = 0;
		int width = 0;
		this.rect = new Rectangle(origin, 0,  0);

		draggingDrawCmd = new RectangleDrawingCommand(rect, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseDraggedUpdateShape rectangle");
		doUpdateCoordByEvent(e);
		return true; // notify observers
	}
	
	protected boolean doMouseReleasedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseReleased rectangle");
		doUpdateCoordByEvent(e);
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

	private void doUpdateCoordByEvent(MouseEvent e) {
		Point renderTopLeftP = this.rect.getRenderTopLeftPoint();
		Point dragStartOrigin = this.rect.getOrigin();
		// set width
		int width = Math.abs(dragStartOrigin.getX() - e.getX());
		this.rect.setWidth(width);
		// set height
		int height = Math.abs(dragStartOrigin.getY() - e.getY());
		this.rect.setHeight(height);
		// set renderTopLeftP x
		if (e.getX() > dragStartOrigin.getX()) {
			renderTopLeftP.setX(dragStartOrigin.getX());
		} else {
			renderTopLeftP.setX(e.getX());
		}
		// set renderTopLeftP y
		if (e.getY() > dragStartOrigin.getY()) {
			renderTopLeftP.setY(dragStartOrigin.getY());
		} else {
			renderTopLeftP.setY(e.getY());
		}
		//		LOG.fine("updated r=" + rect.getRenderTopLeftPoint() + ", w=" + rect.getWidth() + ", h=" + rect.getHeight());
	}
	
}
