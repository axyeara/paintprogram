package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.render.DrawingCommand;

/**
 * Has 2 responsibilities:
 * 1: holds only placed shapes
 * 2: Observable, so notifies Observer -> Bug 2.4 3: called by ShapeManipulator and View at appropriate time
 * 
 * My understanding of a part of Bug 2.4 is creating an instance of the DrawingCommand of 
 * a shape and storing it into the List in PaintModel.
 * @author repo_a2_realSlimShady
 *
 */
public class PaintModel extends Observable {
	// log
	private static final Logger LOG = Logger.getLogger(PaintModel.class.getName());
	
	// Bug 2.1: command pattern
	// Should not declare the variable as concrete class like ArrayList
	private List<DrawingCommand> placedDrawingCommands = new ArrayList<>();
	private List<DrawingCommand> undoneDrawingCommands = new ArrayList<>();

	/**
	 * notifies Observers
	 * Bug 2.4 : called from ShapeManupulatorStrategy
	 */
	public void changed() {
		// notify observers
		this.setChanged();
		this.notifyObservers();
	}

	//------------------------------------------------
	// Shapes already placed
	/**
	 * clears DrawingCommand stack
	 * clears the canvas
	 * called when menu button "clear" is clicked
	 */
	public void clearPlacedDrawingCommands() {
		this.placedDrawingCommands = new ArrayList<>();
		changed(); // notify observers
	}
	
	/**
	 * adds placed DrawingCommand object to the stack
	 * called by ShapeManipulatorStrategy when drawing of a shape is complete
	 * 
	 * passes dragging drawingCommand of Manipulator as argument
	 * drawingCommand (rubberband) holds shape which holds points of shape when it is made final
	 */
	public void addPlacedDrawingCommand(DrawingCommand cmd) {
		this.placedDrawingCommands.add(cmd);
		changed(); // notify observers
	}
	
	/**
	 * To draw the shapes
	 * called by paintPanel.paintComponent
	 */
	public List<DrawingCommand> getPlacedDrawingCommands() {
		return placedDrawingCommands;
	}
	
	//------------------------------------------------
	// Undo Redo
	/**
	 * To undo the last placed shape on the stack
	 */
	public void undo()
	{
		if (placedDrawingCommands.isEmpty()) {
			return;
		}

		int lastInd = placedDrawingCommands.size() - 1;
		DrawingCommand lastCmd = placedDrawingCommands.get(lastInd);
		placedDrawingCommands.remove(lastInd);

		undoneDrawingCommands.add(lastCmd);

		changed(); // notify observers
	}
	/**
	 * If a shape has been removed from the stack by calling the undo function then it will be placed back if this function is called
	 */
	public void redo()
	{
		if (undoneDrawingCommands.isEmpty()) {
			return;
		}

		int lastInd = undoneDrawingCommands.size() - 1;
		DrawingCommand lastCmd = undoneDrawingCommands.get(lastInd);
		undoneDrawingCommands.remove(lastInd);

		placedDrawingCommands.add(lastCmd);

		changed(); // notify observers
	}
	
}

