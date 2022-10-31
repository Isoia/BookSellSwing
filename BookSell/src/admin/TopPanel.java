package admin;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.event.*;

public class TopPanel extends JPanel{
	JButton btn1,btn2,btn3,btn4,btn5;
	
	public TopPanel(){
		btn1 = new JButton("������ȸ");
		btn2 = new JButton("�����߰�");
		btn3 = new JButton("��������");
		btn4 = new JButton("ȸ������");
		btn5 = new JButton("�Ǹ���Ȳ");
		
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
				case "������ȸ":
					mf.viewPanel(new CheckBook());
					break;
				case "�����߰�":
					mf.viewPanel(new AddBook());
					break;
				case "��������":
					mf.viewPanel(new RemoveBook());
					break;
				case "ȸ������":
					mf.viewPanel(new MemberList());
					break;
				case "�Ǹ���Ȳ":
					mf.viewPanel(new SellList());
					break;
			}
		}
		
	}
}
