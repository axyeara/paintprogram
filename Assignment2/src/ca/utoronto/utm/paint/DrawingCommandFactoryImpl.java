package ca.utoronto.utm.paint;

import java.util.logging.Logger;

public class DrawingCommandFactoryImpl implements DrawingCommandFactory{
	
private static final Logger LOG = Logger.getLogger(DrawingCommandFactoryImpl.class.getName());
	
	@Override
	public DrawingCommand create(RenderableShape shape, RenderingParameters renderingParams)
	{
		if (shape instanceof Circle) {
			return new CircleRenderCommand((Circle)shape, renderingParams);
			
		} else {
			throw new IllegalArgumentException("RenderCommandFactoryImpl: unknown shape: " + shape.getClass().getSimpleName());
		}
	}

}
