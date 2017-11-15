package ca.utoronto.utm.paint.shape;

/**
 * We name this RenderableShape for distinction since awt package has Shape.
 */
public interface RenderableShape {
	
	// will be used in ShapeChooser and ShapeManipulationStrategyFactory
	String LABEL_CIRCLE    = "Circle";		// public static final (implicitly)
	String LABEL_RECTANGLE = "Rectangle";
	String LABEL_SQUARE    = "Square";
	String LABEL_SQUIGGLE  = "Squiggle";
	String LABEL_POLYLINE  = "Polyline";
	String LABEL_OVAL      = "Oval";
	String LABEL_TRIANGLE  = "Triangle";

}
