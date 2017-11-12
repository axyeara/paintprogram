package ca.utoronto.utm.paint.render;

import java.awt.Graphics;

import ca.utoronto.utm.paint.shape.RenderableShape;

// Command
public interface DrawingCommand {
	
	void setRenderingParameters(RenderingParameters renderingParameters);

	void render(Graphics g);

	// for debug
	RenderableShape getShape();

}
