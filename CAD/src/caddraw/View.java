package caddraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;



public class View {
	
	private int LINE=1,RECT=2,CIRCLE=3,TEXT=4;
	private int shapetype;
	
	private int x1;
	private int y1;
	private int x,y;
	private int flag;//flag=1 -- picked  flag=2 -- drawing
	private String wordString;
	private float width=2.0f;
	
	private JButton buttonLine = new JButton();
	private JButton buttonRect = new JButton();
	private JButton buttonCircle = new JButton();
	private JButton buttonText = new JButton();
	private JButton buttonDel = new JButton();
	
	private Picture picture = new Picture();
	private MouseA mouseA= new MouseA();
	private MouseB mouseB= new MouseB();
	private JFrame frame = new JFrame();

	private ColorButton buttonBlack = new ColorButton(Color.black);
	private ColorButton buttonWhite = new ColorButton(Color.white);
	private ColorButton buttonYellow = new ColorButton(Color.yellow);
	private ColorButton buttonGreen = new ColorButton(Color.green);
	private ColorButton buttonBlue = new ColorButton(Color.blue);
	private ColorButton buttonDarkGray = new ColorButton(Color.darkGray);
	private ColorButton buttonLightGray = new ColorButton(Color.lightGray);
	private ColorButton buttonOrange = new ColorButton(Color.orange);
	private ColorButton buttonPink = new ColorButton(Color.pink);
	private ColorButton buttonRed = new ColorButton(Color.red);
	private ColorButton buttonCyan = new ColorButton(Color.cyan);
	private ColorButton buttonMagenta = new ColorButton(Color.magenta);
	
	private JMenuBar menuTitle=new JMenuBar();
	private JMenu menuItemFile = new JMenu("文件");

	private JMenuItem item1 = new JMenuItem("Save");
	private JMenuItem item2 = new JMenuItem("Open");
	private JMenuItem item3 = new JMenuItem("说明");
	
	public View()
	{
		JPanel menu = new JPanel();
		ImageIcon iconLine = new ImageIcon("直线.png");
		ImageIcon iconRect = new ImageIcon("矩形.png");
		ImageIcon iconCircle = new ImageIcon("圆.png");
		ImageIcon iconText = new ImageIcon("text.png");
		ImageIcon iconDel = new ImageIcon("清理.png");
		
		JPanel colorBoard = new JPanel();
		
		buttonLine.setIcon(iconLine);
		buttonRect.setIcon(iconRect);
		buttonCircle.setIcon(iconCircle);
		buttonText.setIcon(iconText);
		buttonDel.setIcon(iconDel);
		
		buttonLine.setPreferredSize(new Dimension(80, 80));
		buttonRect.setPreferredSize(new Dimension(80, 80));
		buttonCircle.setPreferredSize(new Dimension(80, 80));
		buttonText.setPreferredSize(new Dimension(80, 80));
		buttonDel.setPreferredSize(new Dimension(80, 80));
		
		colorBoard.setLayout(new GridLayout(4, 3, 0, 0));
		colorBoard.add(buttonBlack);
		colorBoard.add(buttonDarkGray);
		colorBoard.add(buttonLightGray);
		colorBoard.add(buttonWhite);
		colorBoard.add(buttonYellow);
		colorBoard.add(buttonOrange);
		colorBoard.add(buttonRed);
		colorBoard.add(buttonPink);
		colorBoard.add(buttonGreen);
		colorBoard.add(buttonBlue);
		colorBoard.add(buttonMagenta);
		colorBoard.add(buttonCyan);
		colorBoard.setPreferredSize(new Dimension(80, 80));
		
		menu.setLayout(new GridLayout(6,1));
		menu.add(buttonLine);
		menu.add(buttonRect);
		menu.add(buttonCircle);
		menu.add(buttonText);
		menu.add(buttonDel);
		menu.add(colorBoard);
		JPanel menu1 = new JPanel();
		menu1.add(menu);
		
			
		//frame.setLayout(new FlowLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.add(picture,BorderLayout.CENTER);
		frame.add(menu1,BorderLayout.EAST);
		
		frame.setJMenuBar(menuTitle);
		menuTitle.add(menuItemFile);
		menuItemFile.add(item1);
		menuItemFile.add(item2);
		menuItemFile.add(item3);
		item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				save();
			}
		});
		item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				open();
			}
		});
		item3.addActionListener(new ActionListener() {//弹出说明对话框
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,
					       "Escape      退出当前操作\n"
					       + "Ctrl+O      打开文件\n"
					       + "Ctrl+S      保存文件\n"
					       + "R          删除选中图形\n"
					       + "<          缩小选中图形\n"
					       + ">          放大选中图形\n"
					       + "-          细化选中图形\n"
					       + "+          加粗选中图形\n"
					       + "PS：图像选择需点击图形线条（文字除外），线\n"
					       + "较细时，点击线条附近即可选中！\n"
					       + "PPS：圆和矩形的选择也需要点中线条！\n", "操作说明", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		frame.pack();
		frame.setBounds(200,100,800,600);
		frame.setVisible(true);
		picture.requestFocus(true);
		
		
	}	
	
	public void viewInit(){
		
		picture.addMouseListener(mouseA);
		picture.addMouseMotionListener(mouseB);
		picture.addKeyListener(kl4picture);
		
		buttonLine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				shapetype=LINE;
			}
		});
		
		buttonRect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shapetype=RECT;
			}
		});
		
		buttonCircle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shapetype=CIRCLE;
			}
		});
		
		buttonText.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shapetype=TEXT;
				wordString=JOptionPane.showInputDialog(null, "Please input your words!", "Text");
				
			}
		});
		
		buttonDel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				picture.delShape();
				picture.requestFocus(true);
				picture.repaint();
			}
		});
		
		buttonLine.addKeyListener(kl4button);
		buttonRect.addKeyListener(kl4button);
		buttonCircle.addKeyListener(kl4button);
		buttonText.addKeyListener(kl4button);
		
		picture.requestFocus(true);
	}
	
	public class MouseA implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			x1=e.getX();
			y1=e.getY();
			if(flag==0 )
				if(picture.pickShape(x1,y1))
					flag=1;//selected
			if(shapetype!=0 && flag==0)
			{
				flag=2;//drawing
				switch(shapetype){
				case 1:	picture.add(new Line(x1,y1,x1,y1,picture.getcolor(),width,null));
						break;
				case 2:	picture.add(new Rect(x1,y1,x1,y1,picture.getcolor(),width,null));
						break;
				case 3:	picture.add(new Circle(x1,y1,x1,y1,picture.getcolor(),width,null));
						break;
				case 4: picture.add(new Text(x1,y1,x1,y1,picture.getcolor(),width, wordString));
						break;
				default:
						break;
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {

//			switch(shapetype){
//			case 1:	picture.add(new Line(x1,y1,x2,y2));
//					waitcommand();
//					break;
//			case 2:	picture.add(new Rect(x1,y1,x2,y2));
//					waitcommand();
//					break;
//			case 3:	picture.add(new Circle(x1,y1,x2,y2));
//					waitcommand();
//					break;
//			default:
//					break;
//			}
			//shapetype=0;
			picture.requestFocus(true);
			//System.out.println("hellp");
			flag=0;
			picture.repaint();
		}
	}
	
	
	public class MouseB implements MouseMotionListener{
		
		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			x=e.getX();
			y=e.getY();
			if(flag==1)//selected!
			{
				//System.out.println("move");
				picture.shapeMove(x1, y1, x, y);
				x1=x;y1=y;
				picture.repaint();
			}
			if(flag==2){
				//drawing
				picture.newDraw(x, y);
				picture.repaint();
			}
			
			
		}
		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
	
	
	private KeyAdapter kl4button = new KeyAdapter(){
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				picture.requestFocus(true);
				shapetype=0;
			}	
		}
	};
	
	private KeyAdapter kl4picture = new KeyAdapter() {

		@Override
		public void keyPressed(KeyEvent e) {

			if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
			{
				shapetype=0;
				picture.cleanChoose();
			}
			if(e.getKeyCode()==KeyEvent.VK_R)
				picture.delShape();
			if(e.getKeyCode()==KeyEvent.VK_MINUS)
				picture.thinner();
			if(e.getKeyCode()==KeyEvent.VK_EQUALS && e.isShiftDown())
				picture.wider();
			if(e.getKeyCode()==44 && e.isShiftDown())//'<'
				picture.shorter();
			if(e.getKeyCode()==46 && e.isShiftDown())//'>'
				picture.larger();
			if(e.getKeyCode()==KeyEvent.VK_S && e.isControlDown())
				save();
			if(e.getKeyCode()==KeyEvent.VK_O && e.isControlDown())
				open();
			
			picture.repaint();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyReleased(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			super.keyTyped(e);
		}
		
	};
	

	private class ColorButton extends JButton{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Color color;
		
		public ColorButton(Color c){
			this.color=c;
			this.setBackground(color);
			this.addKeyListener(kl4button);
			this.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					picture.setColor(getColor());
					picture.repaint();
					picture.requestFocus(true);
				}
			});
		}

		public Color getColor(){
			return color;
		}
		
		
	}
	
	public void open(){

		picture.open();
		picture.requestFocus(true);
	}
	
	public void save(){

		picture.save();
		//System.out.println("view out");
		picture.requestFocus(true);
	}
	
	public static void main(String[] args)
    {
        View view = new View();
        view.viewInit();
       
    }
}

