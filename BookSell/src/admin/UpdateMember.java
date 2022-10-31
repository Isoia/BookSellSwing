package admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dto_dao_db.User_DAO;
import dto_dao_db.User_DTO;

public class UpdateMember extends JFrame implements ActionListener{
	User_DAO user = new User_DAO();
	JButton btn1,btn2;
	User_DTO insertUser;
	JTextField tf1,tf2,tf3,tf4,tf5;
	String beforeName;
	int complete=0;		// ���� �� ����Ʈ ���� ������ ���� ����
	
	int callComplete() {
		return complete;
	}
	
	public UpdateMember(User_DTO updateUser) {	
		insertUser = new User_DTO(updateUser.getMemid(),updateUser.getMemname(),updateUser.getMempw(),
				updateUser.getMemphone(), updateUser.getMemaddr());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("���� ����");
		JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
		JLabel lb1,lb2,lb3,lb4,lb5;		
		
		btn1 = new JButton("����");
		btn2 = new JButton("��������");
		
		jp6 = new JPanel(new GridLayout(5,1));
		jp7 = new JPanel(new FlowLayout());
		
		lb1 = new JLabel("ID");
		tf1 = new JTextField(15);
		tf1.setText(insertUser.getMemid());
		jp1 = new JPanel(new FlowLayout());
		jp1.add(lb1);
		jp1.add(tf1);
		
		lb2 = new JLabel("�̸�");
		tf2 = new JTextField(15);
		tf2.setText(insertUser.getMemname());
		beforeName = insertUser.getMemname();
		jp2 = new JPanel(new FlowLayout());
		jp2.add(lb2);
		jp2.add(tf2);
		
		lb3 = new JLabel("PW");
		tf3 = new JTextField(15);
		tf3.setText(insertUser.getMempw());
		jp3 = new JPanel(new FlowLayout());
		jp3.add(lb3);
		jp3.add(tf3);
		
		lb4 = new JLabel("�޴���");
		tf4 = new JTextField(15);
		tf4.setText(insertUser.getMemphone());
		jp4 = new JPanel(new FlowLayout());
		jp4.add(lb4);
		jp4.add(tf4);
		
		lb5 = new JLabel("�ּ�");
		tf5 = new JTextField(15);
		tf5.setText(insertUser.getMemaddr());
		jp5 = new JPanel(new FlowLayout());
		jp5.add(lb5);
		jp5.add(tf5);
		
		jp6.add(jp1);
		jp6.add(jp2);
		jp6.add(jp3);
		jp6.add(jp4);
		jp6.add(jp5);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		jp7.add(btn1);
		jp7.add(btn2);
		
		add(jp6);
		add(jp7,BorderLayout.SOUTH);
		
		setSize(400,300);
		setVisible(true);
	}
	public static void Main(String[] args) {
		new UpdateMember(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn.getText().equals("����")) {		//������ư Ŭ�� ��
			if(tf1.getText().equals("") || tf2.getText().equals("")
					|| tf3.getText().equals("") || tf4.getText().equals("")){		// �Է�ĭ�� ������ ���� ��
				JOptionPane.showMessageDialog(null, "��ĭ�� �ֽ��ϴ�! �ٽ� Ȯ���ϼ���",
						"�߰� ����",JOptionPane.PLAIN_MESSAGE);
			}
			else {					// �� �Է�������
				User_DTO updateUser = new User_DTO(tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),tf5.getText());
				if(user.updateUser(updateUser, beforeName)) {		//�߰� ������
					JOptionPane.showMessageDialog(null, "���� ����!",
							"�߰� ����",JOptionPane.PLAIN_MESSAGE);
					complete = 1;
				}else {				//�߰� ���н�
					JOptionPane.showMessageDialog(null, "���� ����",
							"�߰� ����",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}else {									//�������ι�ư Ŭ�� ��
			dispose();
			new MemberList();
		}
	}
}
