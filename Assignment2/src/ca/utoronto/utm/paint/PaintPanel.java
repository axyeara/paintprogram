package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

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
	
	private Color defaultColor = Color.RED;
	private Color fillColor;
	private boolean fillState = false;
	private int defaultStroke;
	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300,300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		this.mode= mode; // bad code here?
		
		this.model = model;
		// paintPanel will now observe model
		// Every time model is changed (model calls notifyObservers()), this update method gets called
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
			System.out.println("render c=" + c.getRadius());
			g2d.setColor(c.color);
			int x = c.getCentre().getX();
			int y = c.getCentre().getY();
			int radius = c.getRadius();
			// we want x and y coordinates for drawOval
			int topX = x - radius;
			int topY = y - radius;
			if (c.fillState == true) {
				g2d.setPaint(c.fillColor);
				g2d.fillOval(topX, topY, radius*2, radius*2);
			}
			else {

				g2d.drawOval(topX, topY, radius*2, radius*2);

				g2d.setStroke(new BasicStroke(c.stroke));

			}
		}
		
		// Draw Rectangles
		ArrayList<Rectangle> rectangles = this.model.getRectangles();
		for (Rectangle r: this.model.getRectangles()) {
			System.out.println("render r=" + r.getRenderTopLeftPoint() + ", w=" + r.getWidth() + ", h=" + r.getHeight());
			g2d.setColor(r.color);
			Point renderTopLeftP = r.getRenderTopLeftPoint();
//			int x = r.getOrigin().getX();
//			int y = r.getOrigin().getY();
			int x = renderTopLeftP.getX();
			int y = renderTopLeftP.getY();
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
			Point renderTopLeftP = sq.getRenderTopLeftPoint();
//			int x = sq.getOrigin().getX();
//			int y = sq.getOrigin().getY();
			int x = renderTopLeftP.getX();
			int y = renderTopLeftP.getY();
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

	// implements Observer
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
		System.out.println("Dragged...");
		if(this.mode=="Squiggle"){
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
			// 1. We must addCircle() to render shape
			// 2. we will replace the last object in the collection in the model to reduce duplication
			// 3. we will add the shape object on mousePressed to guarantee this logic
			this.model.getSquiggles().remove(this.model.getSquiggles().size() - 1);
			this.model.addSquiggle(this.squiggle);
			
		} else if(this.mode=="Circle"){
			// get coordinates for center of circle
			int xCoord = Math.abs(e.getX() - this.circle.getCentre().getX());
			int yCoord = Math.abs(e.getY() - this.circle.getCentre().getY());
			
			// solve for correct value of radius using distance formula
			int xSq = (int) Math.pow(xCoord, 2);
			int ySq = (int) Math.pow(yCoord, 2);
			int radius = (int) Math.sqrt(xSq + ySq);
			this.circle.setRadius(radius);
			
			// set coordinates for circle that is logical for drawOval
			this.circle.setX(this.circle.getCentre().getX() - this.circle.getRadius());
			this.circle.setY(this.circle.getCentre().getY() - this.circle.getRadius());
			
			this.circle.color = this.defaultColor;
			if (this.fillState) {
				this.circle.fillColor = this.fillColor;
				this.circle.setFillState(true);
			}

			this.circle.stroke = this.defaultStroke;
			// 1. We must addCircle() to render shape
			// 2. we will replace the last object in the collection in the model to reduce duplication
			// 3. we will add the shape object on mousePressed to guarantee this logic
			this.model.getCircles().remove(this.model.getCircles().size() - 1);
			this.model.addCircle(this.circle);
			
		} else if (this.mode=="Rectangle") {
//			Point renderTopLeftP = this.rectangle.getRenderTopLeftPoint();
//			Point dragStartOrigin = this.rectangle.getOrigin();
//			// set width
//			int width = Math.abs(dragStartOrigin.getX() - e.getX());
//			this.rectangle.setWidth(width);
//			// set height
//			int height = Math.abs(dragStartOrigin.getY() - e.getY());
//			this.rectangle.setHeight(height);
//			// set renderTopLeftP x
//			if (e.getX() > renderTopLeftP.getX()) {
//				renderTopLeftP.setX(dragStartOrigin.getX());
//			} else {
//				renderTopLeftP.setX(e.getX());
//			}
//			// set renderTopLeftP y
//			if (e.getY() > renderTopLeftP.getY()) {
//				renderTopLeftP.setY(dragStartOrigin.getY());
//			} else {
//				renderTopLeftP.setY(e.getY());
//			}
//			System.out.println("drag r=" + rectangle.getRenderTopLeftPoint() + ", w=" + rectangle.getWidth() + ", h=" + rectangle.getHeight());
//			
//			this.rectangle.color = this.view.defaultColor;
//			if (this.view.fillState == true) {
//				this.rectangle.fillColor = this.view.fillColor;
//				this.rectangle.setFillState(true);
//			}
//			this.rectangle.stroke = this.view.defaultStroke;
//			// 1. We must addCircle() to render shape
//			// 2. we will replace the last object in the collection in the model to reduce duplication
//			// 3. we will add the shape object on mousePressed to guarantee this logic
//			this.model.getRectangles().remove(this.model.getRectangles().size() - 1);
//			this.model.addRectangle(this.rectangle);
		
//		} else if (this.mode=="Square") {
//			Point renderTopLeftP = this.square.getRenderTopLeftPoint();
//			Point dragStartOrigin = this.square.getOrigin();
//			// set width
//			int width = Math.abs(dragStartOrigin.getX() - e.getX());
//			this.rectangle.setWidth(width);
//			// set height
//			int height = Math.abs(dragStartOrigin.getY() - e.getY());
//			this.rectangle.setHeight(height);
//			// set renderTopLeftP x
//			if (e.getX() > renderTopLeftP.getX()) {
//				renderTopLeftP.setX(dragStartOrigin.getX());
//			} else {
//				renderTopLeftP.setX(e.getX());
//			}
//			// set renderTopLeftP y
//			if (e.getY() > renderTopLeftP.getY()) {
//				renderTopLeftP.setY(dragStartOrigin.getY());
//			} else {
//				renderTopLeftP.setY(e.getY());
//			}
////			
////			if (this.rectangle.getOrigin().getX() - e.getX() <  0) {
////				int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
////				this.rectangle.setWidth(width);
////			}
////			else {
////				int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
////				this.rectangle.getOrigin().setX(Math.abs(e.getX()));
////				this.rectangle.setWidth(width);
////			}
////			if (this.rectangle.getOrigin().getY() - e.getY() < 0){
////				int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
////				this.rectangle.setHeight(height);
////			}
////			else {
////				int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
////				this.rectangle.getOrigin().setY(Math.abs(e.getY()));
////				this.rectangle.setHeight(height);
////			}
////			
//			this.rectangle.color = this.view.defaultColor;
//			if (this.view.fillState == true) {
//				this.rectangle.fillColor = this.view.fillColor;
//				this.rectangle.setFillState(true);
//			}
//			this.rectangle.stroke = this.view.defaultStroke;
//			// 1. We must addCircle() to render shape
//			// 2. we will replace the last object in the collection in the model to reduce duplication
//			// 3. we will add the shape object on mousePressed to guarantee this logic
//			this.model.getRectangles().remove(this.model.getRectangles().size() - 1);
//			this.model.addRectangle(this.rectangle);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}

	// 1. We must addCircle() to render shape
	// 2. we will replace the last object in the collection in the model to reduce duplication
	// 3. we will add the shape object on mousePressed to guarantee this logic
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Pressed...");
		if(this.mode=="Squiggle"){
			this.squiggle = new Squiggle(new ArrayList<Point>());
			this.model.addSquiggle(this.squiggle);
			
		} else if(this.mode=="Circle"){
			// Problematic notion of radius and centre!!
			Point centre = new Point(e.getX(), e.getY());
			int radius = 0;
			this.circle=new Circle(centre, 0);
			this.model.addCircle(this.circle);
			
		} else if(this.mode == "Rectangle") {
			Point origin = new Point(e.getX(), e.getY());
			int height = 0;
			int width = 0;
			this.rectangle = new Rectangle(origin, 0,  0);
			this.model.addRectangle(this.rectangle);
			
		} else if (this.mode == "Square") {
			Point origin = new Point(e.getX(), e.getY());
			int width = 0;
			int height = 0;
			this.square = new Square(origin, 0, 0);
			this.model.addSquare(this.square);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Released...");
		if(this.mode=="Squiggle"){
			if (this.squiggle != null) {
				this.squiggle.addPoint(new Point(e.getX(), e.getY()));
				this.squiggle.color = this.defaultColor;
				this.squiggle.fillColor = this.fillColor;
				this.squiggle.stroke = this.defaultStroke;
				// 1. We must addCircle() to render shape
				// 2. we will replace the last object in the collection in the model to reduce duplication
				// 3. we will add the shape object on mousePressed to guarantee this logic
				this.model.getSquiggles().remove(this.model.getSquiggles().size() - 1);
				this.model.addSquiggle(this.squiggle);
				this.squiggle = null;
			}
		} else if(this.mode=="Circle"){
			if(this.circle!=null){
				// Problematic notion of radius and centre!!
				// get coordinates for center of circle
				int xCoord = Math.abs(e.getX() - this.circle.getCentre().getX());
				int yCoord = Math.abs(e.getY() - this.circle.getCentre().getY());
				
				// solve for correct value of radius using distance formula
				int xSq = (int) Math.pow(xCoord, 2);
				int ySq = (int) Math.pow(yCoord, 2);
				int radius = (int) Math.sqrt(xSq + ySq);
				this.circle.setRadius(radius);
				
				// set coordinates for circle that is logical for drawOval
				this.circle.setX(this.circle.getCentre().getX() - this.circle.getRadius());
				this.circle.setY(this.circle.getCentre().getY() - this.circle.getRadius());
				
				this.circle.color = this.defaultColor;
				if (this.fillState) {
					this.circle.fillColor = this.fillColor;
					this.circle.setFillState(true);
				}

				this.circle.stroke = this.defaultStroke;
				// 1. We must addCircle() to render shape
				// 2. we will replace the last object in the collection in the model to reduce duplication
				// 3. we will add the shape object on mousePressed to guarantee this logic
				this.model.getCircles().remove(this.model.getCircles().size() - 1);
				this.model.addCircle(this.circle);
				this.circle=null;
			}
		} else if(this.mode=="Rectangle") {
			if(this.rectangle!= null) {
				Point renderTopLeftP = this.rectangle.getRenderTopLeftPoint();
				Point dragStartOrigin = this.rectangle.getOrigin();
				// set width
				int width = Math.abs(dragStartOrigin.getX() - e.getX());
				this.rectangle.setWidth(width);
				// set height
				int height = Math.abs(dragStartOrigin.getY() - e.getY());
				this.rectangle.setHeight(height);
				// set renderTopLeftP x
				if (e.getX() > renderTopLeftP.getX()) {
					renderTopLeftP.setX(dragStartOrigin.getX());
				} else {
					renderTopLeftP.setX(e.getX());
				}
				// set renderTopLeftP y
				if (e.getY() > renderTopLeftP.getY()) {
					renderTopLeftP.setY(dragStartOrigin.getY());
				} else {
					renderTopLeftP.setY(e.getY());
				}
				System.out.println("release r=" + rectangle.getRenderTopLeftPoint() + ", w=" + rectangle.getWidth() + ", h=" + rectangle.getHeight());
				
				// ----------------------
//				if (this.rectangle.getOrigin().getX() - e.getX() <  0) {
//					int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
//					this.rectangle.setWidth(width);
//				}
//				else {
//					int width = Math.abs(this.rectangle.getOrigin().getX()-e.getX());
//					this.rectangle.getOrigin().setX(Math.abs(e.getX()));
//					this.rectangle.setWidth(width);
//				}
//				if (this.rectangle.getOrigin().getY() - e.getY() < 0){
//					int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
//					this.rectangle.setHeight(height);
//				}
//				else {
//					int height = Math.abs(this.rectangle.getOrigin().getY()-e.getY());
//					this.rectangle.getOrigin().setY(Math.abs(e.getY()));
//					this.rectangle.setHeight(height);
//				}
				
				this.rectangle.color = this.defaultColor;
				if (this.fillState) {
					this.rectangle.fillColor = this.fillColor;
					this.rectangle.setFillState(true);
				}
				this.rectangle.stroke = this.defaultStroke;
				// 1. We must addCircle() to render shape
				// 2. we will replace the last object in the collection in the model to reduce duplication
				// 3. we will add the shape object on mousePressed to guarantee this logic
				this.model.getRectangles().remove(this.model.getRectangles().size() - 1);
				this.model.addRectangle(this.rectangle);
				this.rectangle = null;
			}
		} else if (this.mode == "Square") {
			if(this.square!= null) {
				Point renderTopLeftP = this.square.getRenderTopLeftPoint();
				Point dragStartOrigin = this.square.getOrigin();
				// set width
				int width = Math.abs(dragStartOrigin.getX() - e.getX());
				this.square.setWidth(width);
				// set height
				int height = Math.abs(dragStartOrigin.getY() - e.getY());
				this.square.setHeight(height);
				// set renderTopLeftP x
				if (e.getX() > renderTopLeftP.getX()) {
					renderTopLeftP.setX(dragStartOrigin.getX());
				} else {
					renderTopLeftP.setX(e.getX());
				}
				// set renderTopLeftP y
				if (e.getY() > renderTopLeftP.getY()) {
					renderTopLeftP.setY(dragStartOrigin.getY());
				} else {
					renderTopLeftP.setY(e.getY());
				}
				
				//--------------
//				if (this.square.getOrigin().getX() - e.getX() <  0) {
//					int width = Math.abs(this.square.getOrigin().getX()-e.getX());
//					this.square.setWidth(width);
//				}
//				else {
//					int width = Math.abs(this.square.getOrigin().getX()-e.getX());
//					this.square.getOrigin().setX(Math.abs(e.getX()));
//					this.square.setWidth(width);
//				}
//				if (this.square.getOrigin().getY() - e.getY() < 0){
//					int height = Math.abs(this.square.getOrigin().getY()-e.getY());
//					this.square.setHeight(height);
//				}
//				else {
//					int height = Math.abs(this.square.getOrigin().getY()-e.getY());
//					this.square.getOrigin().setY(Math.abs(e.getY()));
//					this.square.setHeight(height);
//				}
				
				this.square.color = this.defaultColor;
				if (this.fillState) {
					this.square.fillColor = this.fillColor;
					this.square.setFillState(true);
				}
				this.square.stroke = this.defaultStroke;
				// 1. We must addCircle() to render shape
				// 2. we will replace the last object in the collection in the model to reduce duplication
				// 3. we will add the shape object on mousePressed to guarantee this logic
				this.model.getSquares().remove(this.model.getSquares().size() - 1);
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
	
	public Color getDefaultColor() {
		return defaultColor;
	}

	public void setDefaultColor(Color defaultColor) {
		this.defaultColor = defaultColor;
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

	public int getDefaultStroke() {
		return defaultStroke;
	}

	public void setDefaultStroke(int defaultStroke) {
		this.defaultStroke = defaultStroke;
	}
}
