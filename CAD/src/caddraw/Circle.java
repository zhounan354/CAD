package caddraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Circle extends Shape {

	Circle(double x1, double y1, double x2, double y2, Color c, float width, String word) {
		super(x1, y1, x2, y2, c, width, word);
		// TODO Auto-generated constructor stub
		this.radius = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	private double radius;


	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(width));
		g.setColor(color);
		g.drawOval((int) (x1 - radius), (int) (y1 - radius), (int) radius * 2, (int) radius * 2);
	}

	@Override
	public boolean isPick(int x, int y) {
		double cx = x1, cy = y1;
		double r = Math.sqrt((x - cx) * (x - cx) + (y - cy) * (y - cy));
		if (Math.abs(r - radius) < Math.max(width / 2 + 1, 5))
			return true;
		return false;
	}

	@Override
	public void larger() {
		radius = radius * 1.02;
	}

	@Override
	public void shorter() {
		// TODO Auto-generated method stub
		radius = Math.max(radius * 0.98, 1);
	}

	@Override
	public void newDraw(int x, int y) {
		// TODO Auto-generated method stub
		super.newDraw(x, y);
		this.radius = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	
}
