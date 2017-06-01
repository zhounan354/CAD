package caddraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Line extends Shape {


	Line(double x1, double y1, double x2, double y2, Color c, float width, String word) {
		super(x1, y1, x2, y2, c, width, word);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(width));
		g.setColor(color);
		g.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	@Override
	public boolean isPick(int x, int y) {
		double A = y1 - y2;
		double B = x2 - x1;
		double C = x1 * y2 - x2 * y1;
		double d = Math.abs(A * x + B * y + C) / Math.sqrt(A * A + B * B);
		if (d < Math.max(width / 2 + 1, 5) && (x > Math.min(x1, x2) && x < Math.max(x1, x2)))
			return true;

		return false;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	

}
