package caddraw;


import java.awt.Color;
import java.awt.Graphics2D;


public class Shape {
	
	protected double x1;
	protected double y1;
	protected double x2;
	protected double y2;
	protected float width=2.0f;
	protected Color color;
	protected String wordString ;
	
	Shape(double x1,double y1,double x2,double y2,Color c){
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
		this.color=c;
	}
	
	Shape(double x1,double y1,double x2,double y2,Color c,String word){
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
		this.color=c;
		if (word!=null)
			this.wordString=word;
	}
	
	
	Shape(double x1,double y1,double x2,double y2,Color c,float width,String word){
		this.x1 = x1; this.y1 = y1;
		this.x2 = x2; this.y2 = y2;
		this.color=c;
		this.width=width;
		if (word!=null)
			this.wordString=word;
	}
	
	public void draw(Graphics2D g){
		
	}
	
	public void move(int x,int y){
		x1+=x;
		y1+=y;
		x2+=x;
		y2+=y;
	}
	
	public void setwidth(int w){
		width=w;
	}

	public void wider(){
		width+=0.5;
	}
	
	public void thinner(){
		width-=0.5;
		if(width <0)
			width=0;
	}
	
	public void setColor(Color c){
		color=c;
	}
	
	public boolean isPick(int x,int y){
		return false;
	}
	
	public void larger(){
		
		x2=(x2-x1)*1.02 +x1;
		y2=(y2-y1)*1.02 +y1;
	}
	
	public void shorter(){
		
		x2=(x2-x1)*0.98 +x1;
		y2=(y2-y1)*0.98 +y1;
	}
	
	public void newDraw(int x,int y){
		x2 = x;
		y2 = y;
	}
	
	public void setStartPoint(int x,int y){
		x1=x;y1=y;
	}

	@Override
	public String toString() {
			
		return this.getClass()+"\t"+this.x1+"\t"+this.y1+"\t"+this.x2+"\t"+this.y2+"\t"
				+this.color.getRed()+"\t"+this.color.getGreen()+"\t"+this.color.getBlue()+"\t"+
				this.width+"\t"+this.wordString+"\t\n";
				
	}
	
	
}