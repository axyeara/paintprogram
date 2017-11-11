package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

// Command
public interface DrawingCommand {
	
void setRenderingParameters(RenderingParameters renderingParameters);
	
	RenderingParameters getRenderingParameters();
	
	void render(Graphics g);
	
	void mouseDragged(MouseEvent e);
	
	void mouseReleased(MouseEvent e);
	
	// for dragging control
	void setDragging(boolean falg);
	
	boolean isDragging();
	
	// for debug
	RenderableShape getShape();

}
