package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.SquareDrawingCommand;
import ca.utoronto.utm.paint.shape.Point;
import ca.utoronto.utm.paint.shape.Square;

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

	protected void doMousePressedSetDraggingDrawCmd(MouseEvent e) 
	{
		LOG.fine("doMousePressedSetDraggingDrawCmd");
		Point origin = new Point(e.getX(), e.getY());
		int width = 0;
		int height = 0;
		this.square = new Square(origin, 0, 0);

		draggingDrawCmd = new SquareDrawingCommand(square, paintPanel.toRenderingParameters());
	}

	protected void doMouseDraggedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseDraggedUpdateShape square");
		doUpdateCoordByEvent(e);

	}

	protected void doMouseReleasedUpdateShape(MouseEvent e)
	{
		LOG.fine("doMouseReleased square=");
		doUpdateCoordByEvent(e);
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
		// set renderTopLeftP x
		if (e.getX() > renderTopLeftP.getX()) {
			renderTopLeftP.setX(dragStartOrigin.getX());
		} else {
			renderTopLeftP.setX(e.getX());
		}
		// set renderTopLeftP y
		if (e.getY() > renderTopLeftP.getY()) {
			renderTopLeftP.setY(dragStartOrigin.getY());
		} else {
			renderTopLeftP.setY(e.getY());
		}
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
