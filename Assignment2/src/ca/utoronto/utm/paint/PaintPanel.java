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

import ca.utoronto.utm.paint.manipulator.ShapeManipulatorStrategy;
import ca.utoronto.utm.paint.render.DrawingCommand;
import ca.utoronto.utm.paint.render.RenderingParameters;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

public class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {

	// JPanel implements Serializable
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger.getLogger(PaintPanel.class.getName());
	
	private final PaintModel model; // slight departure from MVC, because of the way painting works

	// followings are paint panel properties, nothing to do with view
	// these are set by chooser component hen users select a choice for drawing
	// parameters to draw lines
	private Color lineColor = Color.RED;
	private int lineThickness;
	// parameters to draw fill
	private Color fillColor;
	private boolean fillState = false;
	
	// Bug 2.2, 2.3, 2.4
	private ShapeManipulatorStrategy shapeManipulator;
	
	public PaintPanel(PaintModel model){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.model = model;
		
		// paintPanel will now observe model
		// Every time model is changed (model calls notifyObservers()), this update method gets called
		this.model.addObserver(this);	
	}
	
	public void setShapeManupulator(ShapeManipulatorStrategy shapeManipulator) {
		this.shapeManipulator = shapeManipulator;
	}
	
	/**
	 * convert current rendering parameters into RenderingParameters object
	 * to set to DrawingCommand
	 * @return
	 */
	public RenderingParameters toRenderingParameters() {
		RenderingParameters renderingParams = new RenderingParameters();
		renderingParams.setColor(this.lineColor);
		renderingParams.setFillColor(this.fillColor);
		renderingParams.setStroke(this.lineThickness);
		renderingParams.setFillState(this.fillState);
		return renderingParams;
	}


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
		
		// draw placed shape
		for (DrawingCommand drawingCmd : this.model.getPlacedDrawingCommands()) {
			drawingCmd.render(g2d);
		}

		// draw dragging shape (rubberband)
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
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
//	/**
//	 *  Controller aspect of this
//	 */
//	public void setMode(String mode){
//		this.mode=mode;
//	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {

	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		LOG.fine("Dragged [" + e.getX() + ", " + e.getY() + "]...");
		
		if (shapeManipulator != null) {
			shapeManipulator.mouseDragged(e);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		LOG.fine("Clicked...");
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		LOG.fine("Pressed...");
		if (shapeManipulator != null) {
			shapeManipulator.mousePressed(e);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		LOG.fine("Released...");

		if (shapeManipulator != null) {
			DrawingCommand draggingDrawCmd = this.shapeManipulator.getDraggingDrawingCommand();
			if (draggingDrawCmd != null) {
				shapeManipulator.getDraggingDrawingCommand().setRenderingParameters(toRenderingParameters());
				shapeManipulator.mouseReleased(e);
				shapeManipulator.setDragging(false);

				this.model.addPlacedDrawingCommand(draggingDrawCmd); // place dragging shape
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		LOG.fine("mouseEntered...");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		LOG.fine("mouseExited...");
	}
	
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public Color getFillColor() {
		return fillColor;
	}

	public void setFillColor(Color fillColor) {
		this.fillColor = fillColor;
	}

	public boolean isFillState() {
		return fillState;
	}

	public void setFillState(boolean fillState) {
		this.fillState = fillState;
	}

	public int getLineThickness() {
		return lineThickness;
	}

	public void setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
	}
	
	public PaintModel getModel()
	{
		return model;
	}
}
