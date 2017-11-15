package ca.utoronto.utm.paint.manipulator;

import java.util.logging.Logger;

import ca.utoronto.utm.paint.PaintPanel;
import ca.utoronto.utm.paint.shape.RenderableShape;

/**
 * Bug 2.3: Factory pattern for ShapeManupulatorStrategy
 *
 */
public class ShapeManipulatorStrategyFactoryImpl implements ShapeManipulatorStrategyFactory {
	
	private static final Logger LOG = Logger.getLogger(ShapeManipulatorStrategyFactoryImpl.class.getName());

	@Override
	public ShapeManipulatorStrategy create(String shapeLabel, PaintPanel paintPanel)
	{
		if (RenderableShape.LABEL_CIRCLE.equals(shapeLabel)) {
			return new CircleShapeManipulatorStrategy(paintPanel);
		} else if (RenderableShape.LABEL_RECTANGLE.equals(shapeLabel)) {
			return new RectangleShapeManipulatorStrategy(paintPanel);
		} else if (RenderableShape.LABEL_SQUARE.equals(shapeLabel)) {
			return new SquareShapeManipulatorStrategy(paintPanel);
		} else if (RenderableShape.LABEL_SQUIGGLE.equals(shapeLabel)) {
			return new SquiggleShapeManipulatorStrategy(paintPanel);
		} else if (RenderableShape.LABEL_POLYLINE.equals(shapeLabel)) {
			return new PolylineShapeManipulatorStrategy(paintPanel);
		} else if(RenderableShape.LABEL_OVAL.equals(shapeLabel)) {
			return new OvalShapeManipulatorStrategy(paintPanel);
		} else if(RenderableShape.LABEL_TRIANGLE.equals(shapeLabel)) {
			return new TriangleShapeManipulatorStrategy(paintPanel);
		} else {
			throw new IllegalStateException("Unknown shape is registered: " + shapeLabel);
		}
	}

}
