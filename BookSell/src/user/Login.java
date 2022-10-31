package user;

import javax.swing.*;

import dto_dao_db.DBConnect;
import dto_dao_db.User_DAO;

import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.sql.Connection;

public class Login extends JFrame implements ActionListener{
	DBConnect dbCon = new DBConnect();
	Connection con = dbCon.getConnection();
	User_DAO userDAO = new User_DAO();
	
	JTextField idInput = null;
	JPasswordField pwInput = null;
	
	public Login() {
		setTitle("�α���/ȸ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel login = new JPanel();
		JPanel loginOrInsert = new JPanel();
		
		login.setLayout(null);
		loginOrInsert.setLayout(new FlowLayout());
		
		JLabel id = new JLabel("���̵�");
		id.setSize(100,100);
		id.setLocation(50,10);
		idInput = new JTextField(10);
		idInput.setLocation(130,45);
		idInput.setSize(120,30);
		login.add(id);
		login.add(idInput);
		
		JLabel pw = new JLabel("��й�ȣ");
		pw.setSize(100,100);
		pw.setLocation(50,70);
		pwInput = new JPasswordField(10);
		pwInput.setLocation(130,105);
		pwInput.setSize(120,30);
		login.add(pw);
		login.add(pwInput);
		
		add(login);
		
		JButton btn1,btn2;
		btn1 = new JButton("�α���");
		btn2 = new JButton("ȸ������");
		
		loginOrInsert.add(btn1);
		loginOrInsert.add(btn2);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		add(loginOrInsert,BorderLayout.SOUTH);
		setResizable(false);
		setSize(300,300);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Login();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals("�α���")) {
			if(userDAO.LoginCheck(idInput.getText(),pwInput.getText())) {
				JOptionPane.showMessageDialog(null, idInput.getText()+"�� ȯ���մϴ�",
								"�α��� ����",JOptionPane.PLAIN_MESSAGE);
				new MainFrame();
				dispose();
			}else {
				JOptionPane.showMessageDialog(null, "�α��ο� �����ϼ̽��ϴ�",
						"�α��� ����",JOptionPane.PLAIN_MESSAGE);
			}
		}
		else {
			new Join();
			dispose();
		}
	}

}
