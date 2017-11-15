package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.CircleDrawingCommand;
import ca.utoronto.utm.paint.shape.Circle;
import ca.utoronto.utm.paint.shape.Point;

/**
 * Concrete class for ShareManipulatorStrategy (Circle).
 * Contains the drawing parameters for circle and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
public class CircleShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(CircleShapeManipulatorStrategy.class.getName());

	// shape
	private Circle circle;

	public CircleShapeManipulatorStrategy(PaintPanel paintPanel) {
		LOG.fine("new CircleShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}
	
	@Override
	public void reset() {
		this.circle = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) {
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		// Problematic notion of radius and centre!!
		Point centre = new Point(e.getX(), e.getY());
		int radius = 0;
		this.circle = new Circle(centre, 0);

		draggingDrawCmd = new CircleDrawingCommand(circle, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseDraggedUpdateShape circle=" + circle.getRadius());
		doUpdateCoordByEvent(e);
		return true; // notify observers
	}

	protected boolean doMouseReleasedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseReleased circle=" + circle.getRadius());
		doUpdateCoordByEvent(e);
		
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

	private void doUpdateCoordByEvent(MouseEvent e) {
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
	}

}
