package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.OvalDrawingCommand;
import ca.utoronto.utm.paint.shape.Oval;
import ca.utoronto.utm.paint.shape.Point;

/**
 * Concrete class for ShareManipulatorStrategy (Oval).
 * Contains the drawing parameters for oval and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
public class OvalShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(OvalShapeManipulatorStrategy.class.getName());

	// shape
	private Oval oval;

	public OvalShapeManipulatorStrategy(PaintPanel paintPanel) {
		LOG.fine("new OvalShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}
	
	@Override
	public void reset() {
		this.oval = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) {
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		
		Point centre = new Point(e.getX(), e.getY());
		this.oval = new Oval(centre, 0, 0);

		draggingDrawCmd = new OvalDrawingCommand(oval, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseDraggedUpdateShape oval=" + oval.getWidth() + ":" + oval.getHeight());
		doUpdateCoordByEvent(e);
		return true; // notify observers
	}

	protected boolean doMouseReleasedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseReleased oval=" + oval.getWidth() + ":" + oval.getHeight());
		doUpdateCoordByEvent(e);
		
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

	private void doUpdateCoordByEvent(MouseEvent e) {
		
		// get coordinates for center of circle
		int xCoord = Math.abs(e.getX() - this.oval.getCentre().getX());
		int yCoord = Math.abs(e.getY() - this.oval.getCentre().getY());

		
		this.oval.setWidth((int)(xCoord));
		this.oval.setHeight((int)(yCoord));

		// set coordinates for circle that is logical for drawOval
		this.oval.setX(this.oval.getCentre().getX() - this.oval.getWidth());
		this.oval.setY(this.oval.getCentre().getY() - this.oval.getHeight());
		
	}

}
