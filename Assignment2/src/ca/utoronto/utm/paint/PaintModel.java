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
 * @author momo
 *
 */
public class PaintModel extends Observable {
	// log
	private static final Logger LOG = Logger.getLogger(PaintModel.class.getName());
	
	// Bug 2.1: Should not declare the variable as concrete class like ArrayList
	private List<DrawingCommand> placedDrawingCommands = new ArrayList<>();

	// Bug 2.4 : called from ShapeManupulatorStrategy
	public void draggableChanged() {
		// notify observers
		this.setChanged();
		this.notifyObservers();
	}

	//------------------------------------------------
	// Shapes already placed
	public void clearPlacedDrawingCommands() {
		this.placedDrawingCommands = new ArrayList<>();
		// notify observers
		this.setChanged();
		this.notifyObservers();
	}
	
	public void addPlacedDrawingCommand(DrawingCommand cmd) {
		this.placedDrawingCommands.add(cmd);
		// notify observers
		this.setChanged();
		this.notifyObservers();
	}
	
	public List<DrawingCommand> getPlacedDrawingCommands() {
		return placedDrawingCommands;
	}
	
}

