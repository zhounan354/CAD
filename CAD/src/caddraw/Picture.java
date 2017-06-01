package caddraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Picture extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Shape shape;
	private ArrayList<Shape> listShape = new ArrayList<Shape>();
	private Color color=new Color(0,0,0);
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;  //g是Graphics对象  
        //g2.setStroke(new BasicStroke(width));
        
		for ( Shape s : listShape )
		{
			s.draw(g2);
		}			
	}
		
	
	
	public void add(Shape s)
	{
		listShape.add(s);
		shape=s;
	}

	
	
	public void wider(){
		if(shape!=null)
		{
			shape.wider();
			this.repaint();
		}
	}
	
	public void thinner(){
		if(shape!=null)
		{
			shape.thinner();
			this.repaint();
		}
	}
	
	public void setColor(Color c){
		this.color=c;
		if(shape!=null)
			shape.setColor(color);
	}
	
	public void shapeMove(int x1,int y1,int x2,int y2){
		if(shape!=null)
			shape.move(x2-x1,y2-y1);
	}
	
	public boolean pickShape(int x,int y){
		for ( Shape s : listShape )
		{
			if(s.isPick(x,y))
			{
				shape=s;
				return true;
			}
		}
		return false;	
	}
	
	public void cleanChoose()
	{
		if(shape!=null)
			shape=null;
	}
	
	public void delShape()
	{
		if(shape!=null)
			listShape.remove(shape);
	}
	
	public void larger(){
		if(shape!=null)
		{
			shape.larger();
			//this.repaint();
		}
	}
	
	public void shorter(){
		if(shape!=null)
		{
			shape.shorter();
			//this.repaint();
		}
	}
	
	public void newDraw(int x,int y){
		shape.newDraw(x, y);
		//this.repaint();
	}
	
	public Color getcolor(){
		return new Color(this.color.getRGB());
		
	}
	
	public void setStartPoint(int x,int y){
		if(shape!=null)
			shape.setStartPoint(x,y);
	}
	
	public void open(){
		int option;
		if(!listShape.isEmpty())
		{
			 option = JOptionPane.showConfirmDialog(null,
				       "是否放弃当前图画？", "放弃当前图画？", JOptionPane.YES_NO_OPTION,
				       JOptionPane.WARNING_MESSAGE, null);	
		}
		else
		{
			option = -1;
		}
		
		if(option==JOptionPane.YES_OPTION || option ==-1){
			clear();
			File file = null;
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
			chooser.setAcceptAllFileFilterUsed(false);
			chooser.setFileFilter(new FileNameExtensionFilter("画图pic","pic"));
			int result =chooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
	            file = chooser.getSelectedFile(); // 得到选择的文件
	            String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
	            
	            //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
	            if(fname.indexOf(".pic")==-1){  
	                file=new File(chooser.getCurrentDirectory(),fname+".pic");  
	                //System.out.println("renamed");  
	                //System.out.println(file.getName());  
	            } 

	        } else{
	          //  this.label.setText("没有选择任何文件");
	        }
			if (file != null) {	
				try {
                    Scanner scan = new Scanner(new FileInputStream(file));
                    scan.useDelimiter("\n");
                    while (scan.hasNext()) {
                        String[] str=scan.next().split("\t");
                        if(str[0].endsWith("Line")){
                        	this.add(
                        			new Line(
                        			Double.parseDouble(str[1]),
                        			Double.parseDouble(str[2]),
                        			Double.parseDouble(str[3]),
                        			Double.parseDouble(str[4]),
                        			new Color(Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7])),
                        			Float.parseFloat(str[8]),
                        			str[9])
                        			);
                        }else if(str[0].endsWith("Rect")){
                        	this.add(
                        			new Rect(
                        			Double.parseDouble(str[1]),
                        			Double.parseDouble(str[2]),
                        			Double.parseDouble(str[3]),
                        			Double.parseDouble(str[4]),
                        			new Color(Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7])),
                        			Float.parseFloat(str[8]),
                        			str[9])
                        			);
                        }else if(str[0].endsWith("Circle")){
                        	this.add(
                        			new Circle(
                        			Double.parseDouble(str[1]),
                        			Double.parseDouble(str[2]),
                        			Double.parseDouble(str[3]),
                        			Double.parseDouble(str[4]),
                        			new Color(Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7])),
                        			Float.parseFloat(str[8]),
                        			str[9])
                        			);
                        }else if(str[0].endsWith("Text")){
                        	this.add(
                        			new Text(
                        			Double.parseDouble(str[1]),
                        			Double.parseDouble(str[2]),
                        			Double.parseDouble(str[3]),
                        			Double.parseDouble(str[4]),
                        			new Color(Integer.parseInt(str[5]),Integer.parseInt(str[6]),Integer.parseInt(str[7])),
                        			Float.parseFloat(str[8]),
                        			str[9])
                        			);
                        }
                    }
                    scan.close();
                } catch (Exception e1) {
                	System.out.println("open failed!");
                }
	        }
			
			
			//System.out.println("open");
			this.repaint();
		
		}
	}
	
	public void save(){
		
		File file = null;
		JFileChooser chooser = new JFileChooser(".");
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY );
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("画图pic","pic"));
		int result =chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) { // 选择的是确定按钮
            file = chooser.getSelectedFile(); // 得到选择的文件
            String fname = chooser.getName(file);   //从文件名输入框中获取文件名  
            
            //假如用户填写的文件名不带我们制定的后缀名，那么我们给它添上后缀
            if(fname.indexOf(".pic")==-1){  
                file=new File(chooser.getCurrentDirectory(),fname+".pic");  
                System.out.println("renamed");  
                System.out.println(file.getName());  
            } 
            
            //假如文件已存在
            if(file.exists()){
            	int option = JOptionPane.showConfirmDialog(null,
            		       "文件已存在，是否覆盖？", "覆盖文件？", JOptionPane.YES_NO_OPTION,
            		       JOptionPane.WARNING_MESSAGE, null);
            							switch (option) {
            		     case JOptionPane.YES_OPTION: {
            		      break;
            		     }
            		     case JOptionPane.NO_OPTION:
            		      return;
            		     }
            }
        } else{
          //  this.label.setText("没有选择任何文件");
        }
		if (file != null) {
            try {
                PrintStream out = new PrintStream(new FileOutputStream(file));
                for(Shape k:listShape){
                	out.print(k.toString());
                }
                out.close();
            } catch (Exception e1) {
            }
        }
		
		//System.out.println("save");	
	}
	
	public void clear(){
		listShape.clear();
	}
}