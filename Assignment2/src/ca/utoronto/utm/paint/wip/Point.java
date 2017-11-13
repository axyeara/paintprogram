package ca.utoronto.utm.paint.wip;

public class Point {
	int x, y;
	Point(int x, int y){
		this.x=x; this.y=y;
	}
	
	Point(Point p){
		this.x=p.getX(); 
		this.y=p.getY();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
