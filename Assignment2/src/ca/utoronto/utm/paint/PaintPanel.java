package ca.utoronto.utm.paint;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategy;
import ca.utoronto.utm.paint.render.DrawingCommand;
import ca.utoronto.utm.paint.render.RenderingParameters;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

/**
 * 	this class handles the main paint canvas of the program by implementing observer and mouse listeners and uses Strategy design pattern objects
 * @author repo_a2_realSlimShady
 *
 */
public class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {

	// JPanel implements Serializable
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(PaintPanel.class.getName());
	
	private final PaintModel model; // slight departure from MVC, because of the way painting works
	
	// parameters to draw lines
	private final RenderingParameters renderingParameter = new RenderingParameters();
	
	// Bug 2.2, 2.3, 2.4
	private ShapeManipulatorStrategy shapeManipulator;
	/**
	 * this constructor creates the canvas to draw on as well as adds the mouse action listeners and adds observers
	 * @param model A PaintModel object
	 */
	public PaintPanel(PaintModel model){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(1600,900));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		
		this.model = model;
		
		// paintPanel will now observe model
		// Every time model is changed (model calls notifyObservers()), this update method gets called
		this.model.addObserver(this);	
	}
	
	public RenderingParameters getRenderingParameters() {
		return renderingParameter;
	}
	
	public void setShapeManupulator(ShapeManipulatorStrategy shapeManipulator) {
		this.shapeManipulator = shapeManipulator;
	}
	
	public ShapeManipulatorStrategy getShapeManipulator() {
		return shapeManipulator;
	}
	
	/**
	 * convert current rendering parameters into RenderingParameters object
	 * to set to DrawingCommand
	 * @return
	 */
//	public RenderingParameters toRenderingParameters() {
//		RenderingParameters renderingParams = new RenderingParameters();
//		renderingParams.setColor(this.lineColor);
//		renderingParams.setFillColor(this.fillColor);
//		renderingParams.setStroke(this.lineThickness);
//		renderingParams.setFillState(this.fillState);
//		return renderingParams;
//	}

	/**
	 *  View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		// setBackground (Color.blue); 
		// Origin is at the top left of the window 50 over, 75 down
		g2d.setColor(Color.white);
		
		// Bug 2.1: draw placed shape
		for (DrawingCommand drawingCmd : this.model.getPlacedDrawingCommands()) {
			drawingCmd.render(g2d);
		}

		// draw dragging shape (rubberband)
		// rubberband must be drawn above because it is a stack
		// never saves rubberband drawingCommand
		// draws separately
		if (shapeManipulator != null) {
			DrawingCommand draggingDrawCmd = this.shapeManipulator.getDraggingDrawingCommand();
			if (draggingDrawCmd != null) {
				if (shapeManipulator.isDragging()) {
					LOG.fine("paint draggable shape = " + draggingDrawCmd.getShape());
					draggingDrawCmd.render(g2d);		// draw rubberband
				}
			}
		}
   
		g2d.dispose();
	}

	// implements Observer
	/**
	 * update function given from observer to repaint the canvas
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	// EventListenerMethods below
	// MouseMotionListener below
	/**
	 * mouse moved function used for polyline function
	 */
	@Override
	public void mouseMoved(MouseEvent e) {
		// for Polyline
		if (shapeManipulator != null) {
			shapeManipulator.mouseMoved(e);
		}
	}
	/**
	 * checks when mouse is dragged and updated accordingly
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		LOG.fine("Dragged [" + e.getX() + ", " + e.getY() + "]...");
		
		if (shapeManipulator != null) {
			shapeManipulator.mouseDragged(e);
		}
	}

	// MouseListener below
	/**
	 * checks when mouse is clicked and updates accordingly
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		LOG.fine("Clicked");
		if (shapeManipulator != null) {
			shapeManipulator.mouseClicked(e);
		}
	}
	
	/**
	 * checks when mouse is pressed and updates accordingly
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		LOG.fine("Pressed...");
		if (shapeManipulator != null) {
			shapeManipulator.mousePressed(e);
		}
	}

	/**
	 * checks when mouse is released and updates accordingly
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		LOG.fine("Released...");
		if (shapeManipulator != null) {
			shapeManipulator.mouseReleased(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
//		LOG.fine("mouseEntered...");
	}

	@Override
	public void mouseExited(MouseEvent e) {
//		LOG.fine("mouseExited...");
	}
	
	public PaintModel getModel()
	{
		return model;
	}
}
