package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.TriangleDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Triangle;

/**
 * Concrete class for ShareManipulatorStrategy (Triangle).
 * Contains the drawing parameters for triangle and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
public class TriangleShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(TriangleShapeManipulatorStrategy.class.getName());

	// shape
	private Triangle triangle;

	public TriangleShapeManipulatorStrategy(PaintPanel paintPanel) {
		LOG.fine("new TriangleShapeManipulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}
	
	@Override
	public void reset() {
		this.triangle = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}
	
	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) {
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		// Problematic notion of radius and centre!!
		Point origin = new Point(e.getX(), e.getY());
		Point p1 = new Point(0,0);
		Point p2 = new Point(0,0);
		this.triangle = new Triangle(origin, p1,  p2);

		draggingDrawCmd = new TriangleDrawingCommand(triangle, paintPanel.getRenderingParameters().copy());
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseDraggedUpdateShape triangle");
		doUpdateCoordByEvent(e);
		return true; // notify observers
	}
	
	protected boolean doMouseReleasedUpdateShape(MouseEvent e) {
		LOG.fine("doMouseReleased triangle");
		doUpdateCoordByEvent(e);
		this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

	private void doUpdateCoordByEvent(MouseEvent e) {

		Point p1 = this.triangle.getP1();
		Point p2 = this.triangle.getP2();
		
		int x = e.getX();
		int y = e.getY();
	
		p1.setX(x);
		p1.setY(y);
		
		this.triangle.setP1(p1);
		
		x = this.triangle.getP1().getX();
		y = this.triangle.getOrigin().getY();
		
		p2.setX(x);
		p2.setY(y);
		
		this.triangle.setP2(p2);
	}
	
}
