package ca.utoronto.utm.paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

import ca.utoronto.utm.paint.render.DrawingCommand;

/**
 * Has 2 responsibilities:
 * 1: holds only placed shapes
 * 2: Observable, so notifies Observer
 * 
 * My understanding of a part of Bug 2.4 is creating an instance of the DrawingCommand of 
 * a shape and storing it into the List in PaintModel.
 * @author momo
 *
 */
public class PaintModel extends Observable {
	// log
	private static final Logger LOG = Logger.getLogger(PaintModel.class.getName());
	
	// Bug 2.1: Should not declare the variable as concrete class like ArrayList
	private List<DrawingCommand> placedDrawingCommands = new ArrayList<>();
	private List<DrawingCommand> undoneDrawingCommands = new ArrayList<>();

	// Bug 2.4 : called from ShapeManupulatorStrategy
	public void changed() {
		// notify observers
		this.setChanged();
		this.notifyObservers();
	}

	//------------------------------------------------
	// Shapes already placed
	public void clearPlacedDrawingCommands() {
		this.placedDrawingCommands = new ArrayList<>();
		changed(); // notify observers
	}
	public void addPlacedDrawingCommand(DrawingCommand cmd) {
		this.placedDrawingCommands.add(cmd);
		changed(); // notify observers
	}
	public List<DrawingCommand> getPlacedDrawingCommands() {
		return placedDrawingCommands;
	}
	
	//------------------------------------------------
	// Undo Redo
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

