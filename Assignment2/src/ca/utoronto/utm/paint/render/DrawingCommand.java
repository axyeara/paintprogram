package ca.utoronto.utm.paint.render;

import java.awt.Graphics;

import ca.utoronto.utm.paint.shape.RenderableShape;

/**
 * Interface for command pattern
 * render shapes
 * called by paintComponent in paintpanel
 * 
 * Bug 2.1 1, Bug 2.4 1: Each for particular shape, contains the drawing parameters for shape
 * shape classes in shape package only hold points (no paint attributes)
 * @author momo
 *
 */
public interface DrawingCommand {
	
	void setRenderingParameters(RenderingParameters renderingParameters);

	void render(Graphics g);

	// for debug
	RenderableShape getShape();

}
