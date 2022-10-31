package user;

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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto_dao_db.User_DAO;
import dto_dao_db.User_DTO;

public class Join extends JFrame implements ActionListener{
	User_DAO userDAO = new User_DAO();
	User_DTO userDTO;
	
	JTextField idInput;
	JPasswordField pwInput;
	JTextField nameInput;
	JTextField phoneInput;
	JTextField addrInput;
	
	public Join() {
		setTitle("ȸ������");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel login = new JPanel();
		JPanel loginOrInsert = new JPanel();
		
		login.setLayout(null);
		loginOrInsert.setLayout(new FlowLayout());
		
		JLabel id = new JLabel("���̵�");
		id.setSize(100,50);
		id.setLocation(100,0);
		idInput = new JTextField(10);
		idInput.setLocation(160,10);
		idInput.setSize(120,30);
		login.add(id);
		login.add(idInput);
		
		JLabel pw = new JLabel("��й�ȣ");
		pw.setSize(100,50);
		pw.setLocation(100,55);
		pwInput = new JPasswordField(10);
		pwInput.setLocation(160,65);
		pwInput.setSize(120,30);
		login.add(pw);
		login.add(pwInput);
		
		JLabel name = new JLabel("��   ��");
		name.setSize(100,50);
		name.setLocation(100,110);
		nameInput = new JTextField(10);
		nameInput.setLocation(160,120);
		nameInput.setSize(120,30);
		login.add(name);
		login.add(nameInput);
		
		JLabel phone = new JLabel("��ȭ��ȣ");
		phone.setSize(100,50);
		phone.setLocation(100,165);
		phoneInput = new JTextField(10);
		phoneInput.setLocation(160,175);
		phoneInput.setSize(120,30);
		login.add(phone);
		login.add(phoneInput);
		
			
		JLabel addr = new JLabel("��   ��");
		addr.setSize(100,50);
		addr.setLocation(100,220);
		addrInput = new JTextField(10);
		addrInput.setLocation(160,230);
		addrInput.setSize(120,30);
		login.add(addr);
		login.add(addrInput);
		
		add(login);
		
		JButton btn1,btn2;
		btn1 = new JButton("ȸ������");
		btn2 = new JButton("���");
		
		loginOrInsert.add(btn1);
		loginOrInsert.add(btn2);
		
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		
		add(loginOrInsert,BorderLayout.SOUTH);
		setResizable(false);
		setSize(400,380);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Join();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn.getText().equals("ȸ������")) {
			if(idInput.getText() != null && pwInput.getText() != null && nameInput.getText() != null
									&& phoneInput.getText() != null && addrInput.getText() != null) {	// ���� Ȯ��
				if(userDAO.overlapId(idInput.getText())) {	//���̵� �ߺ�Ȯ��
					//db�� ���� �߰�
					userDTO = new User_DTO(idInput.getText(),nameInput.getText(),pwInput.getText()
									,phoneInput.getText(),addrInput.getText());	//textfield�� �ִ� �� ����
					userDAO.JoinMember(userDTO);	//����
					JOptionPane.showMessageDialog(null, "ȸ������ ����!",
							"ȸ������ ����",JOptionPane.PLAIN_MESSAGE);
					new Login();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�!",
							"ȸ������ ����",JOptionPane.PLAIN_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "��ĭ�� �ֽ��ϴ�! �ٽ� Ȯ���ϼ���",
						"ȸ������ ����",JOptionPane.PLAIN_MESSAGE);
			}
		}
		else if(btn.getText().equals("���")){
			new Login();
			dispose();
		}
	}

}
