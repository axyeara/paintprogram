package ca.utoronto.utm.paint;

import javax.swing.*;  
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private int i=0;
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view

	private String mode; // modifies how we interpret input (could be better?)
	private Circle circle; // the circle we are building
	private Rectangle rectangle;
	private Squiggle squiggle;
	private Square square;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.mode= mode; // bad code here?
		
		this.model = model;
		this.model.addObserver(this);
		
		this.view=view;
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
   

		// Draw Squiggles
		ArrayList<Squiggle> squiggles = this.model.getSquiggles();
		for (Squiggle s: squiggles) {
			g2d.setColor(s.color);
			for(int i=0;i<s.getPoints().size()-1; i++){
				g2d.setStroke(new BasicStroke(s.stroke));
				Point p1=s.getPoints().get(i);
				Point p2=s.getPoints().get(i+1);
				g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			}
		}
		
		// Draw Circles
		ArrayList<Circle> circles = this.model.getCircles();
		for(Circle c: circles){
			g2d.setColor(c.color);
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
			if (c.fillState == true) {
				g2d.setPaint(c.fillColor);
				g2d.fillOval(x, y, radius, radius);
			}
			else {
				g2d.setStroke(new BasicStroke(c.stroke));
				g2d.drawOval(x, y, radius, radius);
			}
		}
		
		// Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangles();
		for (Rectangle r: this.model.getRectangles()) {
			g2d.setColor(r.color);
			int x = r.getOrigin().getX();
			int y = r.getOrigin().getY();
			int height = r.getHeight();
			int width = r.getWidth();
			if (r.fillState == true) {
				g2d.setPaint(r.fillColor);
				g2d.fillRect(x, y, width, height);
			}
			else {
				g2d.setStroke(new BasicStroke(r.stroke));
				g2d.drawRect(x, y, width, height);
			}
			
			
		}
		
		// Draw Squares
		ArrayList<Square> squares = this.model.getSquares();
		for (Square sq: this.model.getSquares()) {
			g2d.setColor(sq.color);
			int x = sq.getOrigin().getX();
			int y = sq.getOrigin().getY();
			int height = sq.getHeight();
			int width = sq.getWidth();
			int length = Math.max(height, width);
			if (sq.fillState == true) {
				g2d.setPaint(sq.fillColor);
				g2d.fillRect(x, y, length, length);
			}
			else {
				g2d.setStroke(new BasicStroke(sq.stroke));
				g2d.drawRect(x, y, length, length);
			}
			
		}
		
		g2d.dispose();
	}

	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	/**
	 *  Controller aspect of this
	 */
	public void setMode(String mode){
		this.mode=mode;
	}
	
	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		} else if(this.mode=="Circle"){
			
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.squiggle = new Squiggle(new ArrayList<Point>());
		} else if(this.mode=="Circle"){
			// Problematic notion of radius and centre!!
			Point centre = new Point(e.getX(), e.getY());
			int radius = 0;
			this.circle=new Circle(centre, 0);
			
		} else if(this.mode == "Rectangle") {
			Point origin = new Point(e.getX(), e.getY());
			int height = 0;
			int width = 0;
			this.rectangle = new Rectangle(origin, 0,  0);
		} else if (this.mode == "Square") {
			Point origin = new Point(e.getX(), e.getY());
			int width = 0;
			int height = 0;
			this.square = new Square(origin, 0, 0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.mode=="Squiggle"){
			if (this.squiggle != null) {
				this.squiggle.addPoint(new Point(e.getX(), e.getY()));
				this.squiggle.color = this.view.defaultColor;
				this.squiggle.fillColor = this.view.fillColor;
				this.squiggle.stroke = this.view.defaultStroke;
				this.model.addSquiggle(this.squiggle);
				this.squiggle = null;
			}
		} else if(this.mode=="Circle"){
			if(this.circle!=null){
				// Problematic notion of radius and centre!!
				int radius = Math.abs(this.circle.getCentre().getX()-e.getX());
				this.circle.setRadius(radius);
				this.circle.color = this.view.defaultColor;
				if (this.view.fillState == true) {
					this.circle.fillColor = this.view.fillColor;
					this.circle.setFillState(true);
				}
				this.circle.stroke = this.view.defaultStroke;
				this.model.addCircle(this.circle);
				this.circle=null;
			}
		} else if(this.mode=="Rectangle") {
			if(this.rectangle!= null) {
				if (this.rectangle.getOrigin().getX() - e.getX() <  0) {
					int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
					this.rectangle.setWidth(width);
				}
				else {
					int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
					this.rectangle.getOrigin().setX(Math.abs(e.getX()));
					this.rectangle.setWidth(width);
				}
				if (this.rectangle.getOrigin().getY() - e.getY() < 0){
					int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
					this.rectangle.setHeight(height);
				}
				else {
					int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
					this.rectangle.getOrigin().setY(Math.abs(e.getY()));
					this.rectangle.setHeight(height);
				}
				
				this.rectangle.color = this.view.defaultColor;
				if (this.view.fillState == true) {
					this.rectangle.fillColor = this.view.fillColor;
					this.rectangle.setFillState(true);
				}
				this.rectangle.stroke = this.view.defaultStroke;
				this.model.addRectangle(this.rectangle);
				this.rectangle = null;
			}
		} else if (this.mode == "Square") {
			if(this.square!= null) {
				if (this.square.getOrigin().getX() - e.getX() <  0) {
					int width = Math.abs(this.square.getOrigin().getX()-e.getX());
					this.square.setWidth(width);
				}
				else {
					int width = Math.abs(this.square.getOrigin().getX()-e.getX());
					this.square.getOrigin().setX(Math.abs(e.getX()));
					this.square.setWidth(width);
				}
				if (this.square.getOrigin().getY() - e.getY() < 0){
					int height = Math.abs(this.square.getOrigin().getY()-e.getY());
					this.square.setHeight(height);
				}
				else {
					int height = Math.abs(this.square.getOrigin().getY()-e.getY());
					this.square.getOrigin().setY(Math.abs(e.getY()));
					this.square.setHeight(height);
				}
				
				this.square.color = this.view.defaultColor;
				if (this.view.fillState == true) {
					this.square.fillColor = this.view.fillColor;
					this.square.setFillState(true);
				}
				this.square.stroke = this.view.defaultStroke;
				this.model.addSquare(this.square);
				this.square = null;
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}
}
