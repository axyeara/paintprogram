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
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener  {
	private PaintModel model; // slight departure from MVC, because of the way painting works
	private View view; // So we can talk to our parent or other components of the view
	private String mode; 
	private Circle circle; 
	private Rectangle rectangle; 
	private Square square;
	private Squiggle squiggle;
	private String fillState;	
	private Color color;	
	private int lineThickness = 1;

	
	public PaintPanel(PaintModel model, View view){
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(1600,900));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.model = model;
		this.model.addObserver(this);
		this.view=view;
	}


	public void paintComponent(Graphics g) {
	
		
        super.paintComponent(g); //paint background
        Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		
     	ArrayList<Shape> shapeStack = this.model.getShapes();
     	for(Shape sh: shapeStack) {
     		
     			// Draw Squiggles
     			if(sh instanceof Squiggle) {
     	        	for(int i=0;i< ((Squiggle) sh).getSize()-1; i++){
     	    			Point p1=((Squiggle) sh).getPoint(i);
     	    			Point p2=((Squiggle) sh).getPoint(i+1);
     	    			g2d.setColor(((Squiggle) sh).getColor());
     	    			g2d.setStroke(new BasicStroke(((Squiggle) sh).getLineThickness()));
     	    			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
     	    		}  
     	        }
     			else if(sh instanceof Circle) {
     			// Draw Circles
     				int x = ((Circle) sh).getCentre().getX();
     				int y = ((Circle) sh).getCentre().getY();
     				int radius = ((Circle) sh).getRadius();
     				g2d.setColor(((Circle) sh).getColor());
     				if(((Circle) sh).getFillState()=="Outline") {
     				g2d.setStroke(new BasicStroke(((Circle) sh).getLineThickness()));	
     				g2d.drawOval(x-radius, y-radius, 2*radius, 2*radius);}
     				else {
     					g2d.fillOval(x-radius, y-radius, 2*radius, 2*radius);
     				}
     			
     			}
     			// Draw Rectangles
     			else if(sh instanceof Rectangle) {
     				int x = ((Rectangle) sh).getCorner().getX();
     				int y = ((Rectangle) sh).getCorner().getY();
     				int width = ((Rectangle) sh).getWidth();
     				int height = ((Rectangle) sh).getHeight();
     				if(width<0) {
     					width = Math.abs(width);
     					x = ((Rectangle) sh).getCorner().getX()-width;
     				}
     				if(height<0) {
     					height = Math.abs(height);
     					y = ((Rectangle) sh).getCorner().getY()-height;
     				}
     				g2d.setColor(((Rectangle) sh).getColor());
     				if(((Rectangle) sh).getFillState()=="Outline") {
     				g2d.setStroke(new BasicStroke(((Rectangle) sh).getLineThickness()));
     				g2d.drawRect(x, y, width, height);}
     				else {
     					g2d.fillRect(x, y, width, height);
     				}
     			}
     			
     			//Draw Squares
     			else if (sh instanceof Square) {
     				int x = ((Square) sh).getCorner().getX();
     				int y = ((Square) sh).getCorner().getY();
     				int width = ((Square) sh).getWidth();
     				int height = ((Square) sh).getHeight();
     				if(width<0) {
     					width = Math.abs(width);
     					x = ((Square) sh).getCorner().getX()-width;
     				}
     				if(height<0) {
     					height = Math.abs(height);
     					y = ((Square) sh).getCorner().getY()-height;
     				}
     				g2d.setColor(((Square) sh).getColor());
     				if(((Square) sh).getFillState()=="Outline") {
     				g2d.setStroke(new BasicStroke(((Square) sh).getLineThickness()));
     				g2d.drawRect(x, y, width, width);}
     				else {
     					g2d.fillRect(x, y, width, width);
     				}
     			}
     		}
        
     	// Draw Lines
     		ArrayList<Point> points = this.model.getPoints();
     		for(int i=0;i<points.size()-1; i++){
     			Point p1=points.get(i);
     			Point p2=points.get(i+1);
     			g2d.setColor(this.color);
     			g2d.setStroke(new BasicStroke(this.lineThickness));
     			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
     		}
		
		
		g2d.dispose();
	}


	
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}
	
	public void setMode(String mode){
		this.mode=mode;
	}
	
	public void setFillState(String fillState) {
		this.fillState = fillState;
	}
	
	public void setLineThickness(int thickness) {
		this.lineThickness = thickness;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	
	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addPoint(new Point(e.getX(), e.getY()));
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
		} else if(this.mode=="Circle"){
			int radius = (int) Math.sqrt((this.circle.getCentre().getX()-e.getX())*(this.circle.getCentre().getX()-e.getX())+(this.circle.getCentre().getY()-e.getY())*(this.circle.getCentre().getY()-e.getY()));
			this.circle.setRadius(radius);
			this.model.addShape(this.circle);
		} else if(this.mode=="Rectangle") {
			int width = e.getX()-this.rectangle.getCorner().getX();
			int height = e.getY()-this.rectangle.getCorner().getY();
			this.rectangle.setWidth(width);
			this.rectangle.setHeight(height);
			this.model.addShape(this.rectangle);
		}else if(this.mode=="Square") {
			int width = Math.max(Math.abs(e.getX()-this.square.getCorner().getX()), Math.abs(e.getY()-this.square.getCorner().getY()));
			this.square.setHeight(width*(int)Math.signum(e.getY()-this.square.getCorner().getY()));
			this.square.setWidth(width*(int)Math.signum(e.getX()-this.square.getCorner().getX()));
			this.model.addShape(this.square);
		}
			
		
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(this.mode=="Squiggle"){
			
		} else if(this.mode=="Circle"){
			
		}
	}


	@Override
	public void mousePressed(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addPoint(new Point(e.getX(),e.getY()));
			this.squiggle = new Squiggle(new Point(e.getX(),e.getY()),this.color,this.fillState,this.lineThickness);
		} else if(this.mode=="Circle"){
			int radius = 0;
			Point centre = new Point(e.getX(), e.getY());
			this.circle=new Circle(centre, radius, this.color,this.fillState,this.lineThickness);
		} else if(this.mode=="Rectangle") {
			int width = 0;
			int height = 0;
			Point corner = new Point(e.getX(),e.getY());
			this.rectangle=new Rectangle(corner,width,height, this.color,this.fillState,this.lineThickness);
		} else if(this.mode == "Square") {
			int width = 0;
			Point corner = new Point(e.getX(),e.getY());
			this.square=new Square(corner,width, width,this.color,this.fillState,this.lineThickness);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(this.mode=="Squiggle"){
			this.model.addPoint(new Point(e.getX(), e.getY()));
			this.squiggle.addPoint(new Point(e.getX(), e.getY()));
			this.model.addShape(this.squiggle);
			this.model.clearPoints();
		} else if(this.mode=="Circle"){
			if(this.circle!=null){
				int radius = (int) Math.sqrt((this.circle.getCentre().getX()-e.getX())*(this.circle.getCentre().getX()-e.getX())+(this.circle.getCentre().getY()-e.getY())*(this.circle.getCentre().getY()-e.getY()));
				this.circle.setRadius(radius);
				this.model.addShape(this.circle);
			}
		}else if(this.mode=="Rectangle") {
			if(this.rectangle!=null) {
				int width = e.getX()-this.rectangle.getCorner().getX();
				int height = e.getY()-this.rectangle.getCorner().getY();
				this.rectangle.setWidth(width);
				this.rectangle.setHeight(height);
				this.model.addShape(this.rectangle);
			}
		}else if(this.mode=="Square") {
			if(this.square!=null) {
				int width = Math.max(Math.abs(e.getX()-this.square.getCorner().getX()), Math.abs(e.getY()-this.square.getCorner().getY()));
				this.square.setHeight(width*(int)Math.signum(e.getY()-this.square.getCorner().getY()));
				this.square.setWidth(width*(int)Math.signum(e.getX()-this.square.getCorner().getX()));
				this.model.addShape(this.square);
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


	
	