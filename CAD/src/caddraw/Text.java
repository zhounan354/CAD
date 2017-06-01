package caddraw;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Text extends Shape {
	
	private int wordSize;
	Text(double x1, double y1, double x2, double y2, Color c, float width, String word) {
		super(x1, y1, x2, y2, c, width, word);
		// TODO Auto-generated constructor stub
		wordSize = (int) Math.max((Math.abs(x2 - x1) + Math.abs(y2 - y1)) / 3, 2);
	}

	
	@Override
	public void draw(Graphics2D g) {
		g.setStroke(new BasicStroke(width));
		g.setColor(color);
		g.setFont(new Font("", Font.ITALIC, wordSize));
		if (wordString!=null)
			g.drawString(wordString, (int) x1, (int) y1);

	}

	@Override
	public boolean isPick(int x, int y) {
		if (x > x1 && y < y1 && x < x1 + wordString.length() * wordSize && y > y1 -  wordSize)
			return true;
		return false;
	}

	@Override
	public void newDraw(int x, int y) {
		// TODO Auto-generated method stub
		super.newDraw(x, y);
		wordSize = (int) Math.max((Math.abs(x2 - x1) + Math.abs(y2 - y1)) / 3, 2);
	}
	


	@Override
	public void larger() {
		// TODO Auto-generated method stub
		super.larger();
		wordSize = (int) Math.max((Math.abs(x2 - x1) + Math.abs(y2 - y1)) / 3, 2);
	}

	@Override
	public void shorter() {
		// TODO Auto-generated method stub
		super.shorter();
		wordSize = (int) Math.max((Math.abs(x2 - x1) + Math.abs(y2 - y1)) / 3, 2);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}


	
	
	
}
