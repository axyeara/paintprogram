package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.render.DrawingCommand;

public abstract class ShapeManipulatorStrategyTemplate implements ShapeManipulatorStrategy {
	
	private static final Logger LOG = Logger.getLogger(ShapeManipulatorStrategyTemplate.class.getName());
	
	protected PaintPanel paintPanel;
	protected PaintModel model;
	
	protected DrawingCommand draggingDrawCmd;
	protected boolean dragging;
	
	// template method the sub-class must implement
	abstract protected void doMousePressedSetDraggingDrawCmd(MouseEvent e);
	abstract protected boolean doMouseDraggedUpdateShape(MouseEvent e);
	abstract protected boolean doMouseReleasedUpdateShape(MouseEvent e);
	
	// default methods : TODO make this abstract if we have time.
	// sub-class may need to override this when it need to track mouse move instead of drag.
	protected boolean doMouseMovedUpdateShape(MouseEvent e) {
		// does nothing for most of the shapes. so implement default methods here. 
		return false; // do not notify observers
	}
	
	protected boolean doMouseLeftClickUpdateShape(MouseEvent e) {
		// does nothing for most of the shapes. so implement default methods here. 
		return false; // do not notify observers
	}
	
	protected boolean doMouseRightClickUpdateShape(MouseEvent e) {
		// does nothing for most of the shapes. so implement default methods here. 
		return false; // do not notify observers
	}
	protected boolean doMouseDoubleClickUpdateShape(MouseEvent e) {
		// does nothing for most of the shapes. so implement default methods here. 
		return false; // do not notify observers
	}

	@Override
	public DrawingCommand getDraggingDrawingCommand() {
		return this.draggingDrawCmd;
	}
	
	// mouseEvents
	// pressed -> released -> clicked
	@Override
	public void mousePressed(MouseEvent e) {
		// only need left mouse button for drawing a new shape
		if (! SwingUtilities.isLeftMouseButton(e)) {
			return; // ignore right button pressed to enable mouseClicked event handling.
		}
		
		doMousePressedSetDraggingDrawCmd(e);
		
		this.dragging = true;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (! isDragging()) {
			return; // this event is only used when dragging
		}
		if (doMouseDraggedUpdateShape(e)) {
			this.model.changed(); // notify observers
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if (! isDragging()) {
			return; // this event is only used when dragging
		}
		if (doMouseMovedUpdateShape(e)) {
			this.model.changed(); // notify observers
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (! SwingUtilities.isLeftMouseButton(e)) {
			return; // ignore right button pressed to enable mouseClicked event handling.
		}
		if (! isDragging()) {
			return; // this event is only used when dragging
		}
		if (doMouseReleasedUpdateShape(e)) {
			this.model.changed(); // notify observers
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		boolean notifyObserver = false;
		
		if (SwingUtilities.isLeftMouseButton(e)) {
			if(e.getClickCount() == 2) {
				notifyObserver = doMouseDoubleClickUpdateShape(e);
			}else {
			notifyObserver = false; // we process pressed and released for right button
			}
		} else if (SwingUtilities.isRightMouseButton(e)) {
			notifyObserver = doMouseRightClickUpdateShape(e);
			
		} else if (SwingUtilities.isMiddleMouseButton(e)) {
			notifyObserver = false;
		} else {
			notifyObserver = false;
		}
		
		// notify observers
		if (notifyObserver) {
			this.model.changed(); // notify observers
		}
	}

	@Override
	public void setDragging(boolean dragging) {
		this.dragging = dragging;
		
		if (!dragging) {
			draggingDrawCmd = null;
		}
	}
	
	@Override
	public boolean isDragging() {
		return dragging;
	}

}
