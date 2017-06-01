package caddraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Rect extends Shape {

	Rect(double x1, double y1, double x2, double y2, Color c, float width, String word) {
		super(x1, y1, x2, y2, c, width, word);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(width));
		g.setColor(color);
		g.drawRect((int)Math.min(x1, x2), (int)Math.min(y1, y2), (int)Math.abs(x2-x1), (int)Math.abs(y2-y1));
	}

	@Override
	public boolean isPick(int x, int y) {
		double bound = Math.max(width/2+1, 5);
		if((Math.abs(x-x1)<bound || Math.abs(x-x2)<bound || Math.abs(y-y1)<bound || Math.abs(y-y2)<bound ) 
				&& (x<Math.max(x1, x2)+bound && x>Math.min(x1, x2)-bound && y<Math.max(y1, y2)+bound && y>Math.min(y1, y2)-bound))
			return true;
		
		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	

	
}