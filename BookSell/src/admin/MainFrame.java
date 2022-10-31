package admin;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public class MainFrame extends JFrame{
	TopPanel tp = new TopPanel();
	Container c;
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,500);
		
		c = this.getContentPane();
		viewPanel(new CheckBook());
		
		setResizable(false);
		setTitle("관리자 화면");
		setVisible(true);
		
	}
	
	void viewPanel(JPanel p) {
		c.removeAll();
		add(tp, BorderLayout.NORTH);
		add(p);
		c.revalidate();
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
