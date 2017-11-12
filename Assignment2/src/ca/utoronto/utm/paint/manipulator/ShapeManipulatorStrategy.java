package ca.utoronto.utm.paint.manipulator;

import java.awt.event.MouseEvent;

import ca.utoronto.utm.paint.render.DrawingCommand;

public interface ShapeManipulatorStrategy
{
	void reset();
	
	DrawingCommand getDraggingDrawingCommand();

	void mousePressed(MouseEvent e);

	void mouseDragged(MouseEvent e);
	
	void mouseMoved(MouseEvent e);
	
	void mouseReleased(MouseEvent e);
	
	void mouseClicked(MouseEvent e);
	
	// for dragging control
	void setDragging(boolean falg);

	boolean isDragging();

}
