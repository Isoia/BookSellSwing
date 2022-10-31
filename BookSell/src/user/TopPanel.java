package user;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;

public class TopPanel extends JPanel{
	JButton btn1,btn2,btn3,btn4;
	
	public TopPanel(){
		btn1 = new JButton("도서조회");
		btn2 = new JButton("도서검색");
		btn3 = new JButton("도서구매");
		btn4 = new JButton("구매목록");
		
		MyActionListener al = new MyActionListener();
		
		btn1.addActionListener(al);
		btn2.addActionListener(al);
		btn3.addActionListener(al);
		btn4.addActionListener(al);
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
	}
	
	class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			MainFrame mf = (MainFrame)btn.getTopLevelAncestor();
			
			switch(btn.getActionCommand()) {
				case "도서조회":
					mf.viewPanel(new CheckBook());
					break;
				case "도서검색":
					mf.viewPanel(new SearchBook());
					break;
				case "도서구매":
					mf.viewPanel(new SellBook());
					break;
				case "구매목록":
					mf.viewPanel(new SellList());
					break;
			}
		}
		
	}
}
