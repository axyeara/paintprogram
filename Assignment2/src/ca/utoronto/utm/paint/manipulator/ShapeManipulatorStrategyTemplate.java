package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.DrawingCommand;

public abstract class ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	protected PaintPanel paintPanel;
	protected PaintModel model;
	
	protected DrawingCommand draggingDrawCmd;
	protected boolean dragging;
	
	// template method the sub-class must implement
	abstract protected void doMousePressedSetDraggingDrawCmd(MouseEvent e);
	abstract protected void doMouseDraggedUpdateShape(MouseEvent e);
	abstract protected void doMouseReleasedUpdateShape(MouseEvent e);

	@Override
	public DrawingCommand getDraggingDrawingCommand()
	{
		return this.draggingDrawCmd;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
		doMousePressedSetDraggingDrawCmd(e);
		
		this.dragging = true;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		doMouseDraggedUpdateShape(e);
		
		// Bug 2.4 3: notify observers (call repaint to render rubberband)
		this.model.draggableChanged();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		doMouseReleasedUpdateShape(e);
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
		return dragging;
	}

}
