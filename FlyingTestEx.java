package event;

import java.awt.Container;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FlyingTestEx extends JFrame{
	private final int FLYING_UNIT = 10;
	private JLabel la = new JLabel("HELLO");
	
	public FlyingTestEx() {
		setTitle("»óÇÏÁÂ¿ì");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);
		c.addKeyListener(new MyKeyListener());
		
		la.setLocation(50,50);
		la.setSize(100,20);
		c.add(la);
		setSize(300,300);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus();
	}
	
	class MyKeyListener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			Container c = (Container)e.getSource();
			Point point = la.getLocation();
			int move = e.getKeyCode();
			
			if(move == KeyEvent.VK_UP) {
				la.setLocation(point.x,point.y-10);
			}
			else if(move == KeyEvent.VK_DOWN)
				la.setLocation(point.x,point.y+10);
			else if(move == KeyEvent.VK_LEFT)
				la.setLocation(point.x-10,point.y);
			else if(move == KeyEvent.VK_RIGHT)
				la.setLocation(point.x+10,point.y);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
	}
	
	public static void main(String[] args) {
		new FlyingTestEx();

	}

}
