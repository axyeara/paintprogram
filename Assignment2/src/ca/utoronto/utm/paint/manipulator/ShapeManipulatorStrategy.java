package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;

import ca.utoronto.utm.paint.render.DrawingCommand;
/**
 * Bug 2.4 1: Each for particular shape, contains the drawing parameters for shape
 * shape classes in shape package only hold points (no paint attributes)
 * 
 * through concrete classes, this creates mirror hierarchy
 * 
 * Bug 2.4 2: handles mouseEvent
 * is called by eventListeners in PaintPanel
 * Bug 2.4 3: repaints at appropriate time when called through paintModel by notifying Observers
 * 
 * Design note: 
 * 1: employed Template Method Pattern: ShapeManipulatorStrategyTemplate
 * 
 * @author RealSlimShady
 *
 */
public interface ShapeManipulatorStrategy
{
	/**
	 * delete rubberband (goes back to initial state)
	 * view has escape key handler which calls this
	 */
	void reset();
	
	/**
	 * Manipulator holds DrawingCommand for rubberband 
	 * this is different from placed DrawingCommand
	 * 
	 * this is called from PaintPanel.paintComponent method
	 *  
	 * @return DrawingCommand object for the shape of the concrete class
	 */
	DrawingCommand getDraggingDrawingCommand();

	//--------------------------------------------
	// mouse event handling methods
	//-------------------------------------------
	void mousePressed(MouseEvent e);

	void mouseDragged(MouseEvent e);
	
	void mouseMoved(MouseEvent e);
	
	void mouseReleased(MouseEvent e);
	
	void mouseClicked(MouseEvent e);
	
	//--------------------------------------------
	// for dragging control
	//-------------------------------------------
	/**
	 * if flag is true, dragging
	 * if flag is false, not dragging
	 * 
	 * @param flag
	 */
	void setDragging(boolean flag);

	boolean isDragging();

}
