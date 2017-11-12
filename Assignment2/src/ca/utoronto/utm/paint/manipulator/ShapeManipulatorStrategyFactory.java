package ca.utoronto.utm.paint.manipulator;

import ca.utoronto.utm.paint.PaintPanel;

/**
 * Bug 2.3: Factory pattern for ShapeManupulatorStrategy
 * In this application, if-statement to determine the shape is defined only in this class
 */
public interface ShapeManipulatorStrategyFactory {
	
	ShapeManipulatorStrategy create(String shapeLabel, PaintPanel paintPanel);

}
