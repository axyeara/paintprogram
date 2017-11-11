package ca.utoronto.utm.paint;

public interface DrawingCommandFactory {
	
	DrawingCommand create(RenderableShape shape, RenderingParameters renderingParams);

}
