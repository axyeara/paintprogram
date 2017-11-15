package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.PolylineDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Polyline;

/**
 * Concrete class for ShareManipulatorStrategy (Polyline).
 * Contains the drawing parameters for polyline and specifies
 * the action taken with mouseEvents.
 * 
 * 
 * @author RealSlimShady
 *
 */
public class PolylineShapeManipulatorStrategy extends ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(PolylineShapeManipulatorStrategy.class.getName());

	// shape
	private Polyline plyline;

	public PolylineShapeManipulatorStrategy(PaintPanel paintPanel) {
		LOG.fine("new PolylineShapeManupulatorStrategy...");
		this.paintPanel = paintPanel;
		this.model = paintPanel.getModel();
	}

	@Override
	public void reset() {
		this.plyline = null;
		this.draggingDrawCmd = null;
		setDragging(false);
	}

	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) {
		if (! isDragging()) {
			LOG.fine("doMousePressedSetDraggingDrawCmd: polyline start");

			this.plyline = new Polyline(new ArrayList<Point>());
			this.plyline.addPoint(new Point(e.getX(), e.getY()));
			draggingDrawCmd = new PolylineDrawingCommand(plyline, paintPanel.getRenderingParameters().copy());
		} else {
			LOG.fine("doMousePressedSetDraggingDrawCmd: polyline add point");
			this.plyline.addPoint(new Point(e.getX(), e.getY()));
		}
	}

	protected boolean doMouseDraggedUpdateShape(MouseEvent e) {
		return false; // do not notify observers
	}

	protected boolean doMouseMovedUpdateShape(MouseEvent e) {
		if (draggingDrawCmd == null) {
			return false; // not click any point yet, do not notify observers
		}

		LOG.fine("doMouseMovedUpdateShape [" + e.getX() + ", " + e.getY() + "]...");
		this.plyline.replaceLastPoint(new Point(e.getX(), e.getY()));
		return true; // notify observers
	}

	protected boolean doMouseReleasedUpdateShape(MouseEvent e) {
		return false; // set manupulator.dragging is not over
	}

	protected boolean doMouseDoubleClickUpdateShape(MouseEvent e) {
		LOG.fine("doMouseRightClickUpdateShape polyline");

		this.plyline.removeLastPoint(); //remove last dragging point
		if (this.plyline.getPoints().size() > 1) { // no reason to save less than 2 points.
			this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
		}
		this.draggingDrawCmd = null;
		this.plyline = null;

		setDragging(false); // set manupulator.dragging is over
		return true; // notify observers
	}

}
