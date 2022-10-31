package admin;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;

public class TopPanel extends JPanel{
	JButton btn1,btn2,btn3,btn4,btn5;
	
	public TopPanel(){
		btn1 = new JButton("도서조회");
		btn2 = new JButton("도서추가");
		btn3 = new JButton("도서삭제");
		btn4 = new JButton("회원관리");
		btn5 = new JButton("판매현황");
		
		MyActionListener al = new MyActionListener();
		
		btn1.addActionListener(al);
		btn2.addActionListener(al);
		btn3.addActionListener(al);
		btn4.addActionListener(al);
		btn5.addActionListener(al);
		
		add(btn1);
		add(btn2);
		add(btn3);
		add(btn4);
		add(btn5);
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
				case "도서추가":
					mf.viewPanel(new AddBook());
					break;
				case "도서삭제":
					mf.viewPanel(new RemoveBook());
					break;
				case "회원관리":
					mf.viewPanel(new MemberList());
					break;
				case "판매현황":
					mf.viewPanel(new SellList());
					break;
			}
		}
		
	}
}
