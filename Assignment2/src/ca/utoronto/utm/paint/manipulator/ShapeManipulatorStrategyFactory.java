package ca.utoronto.utm.paint.manipulator;

import ca.utoronto.utm.paint.PaintPanel;

/**
 * Bug 2.3, Bug 2.4: Factory pattern to create different ShapeManupulatorStrategy instances per shape
 * In this application, if-statement to determine the shape is defined only in the concrete class
 *
 * @author RealSlimShady
 *
 */
public interface ShapeManipulatorStrategyFactory {
	
	ShapeManipulatorStrategy create(String shapeLabel, PaintPanel paintPanel);

}
