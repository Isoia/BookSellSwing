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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dto_dao_db.Admin_DAO;
import dto_dao_db.Admin_DTO;

public class Join extends JFrame implements ActionListener{
	Admin_DTO adminDTO;
	Admin_DAO adminDAO = new Admin_DAO();
	
	JTextField idInput;
	JPasswordField pwInput;
	public Join() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("������ ���");
		
		JButton join = new JButton("������ ���");
		JButton cancel = new JButton("���");
		
		idInput = new JTextField(10);
		pwInput = new JPasswordField(10);
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("���̵� : "));
		idPanel.add(idInput);
			
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwPanel.add(new JLabel("��й�ȣ : "));
		pwPanel.add(pwInput);
			
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(2, 1));
		formPanel.add(idPanel);
		formPanel.add(pwPanel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);
		
		JPanel panel = new JPanel();
		join.addActionListener(this);
		cancel.addActionListener(this);
		panel.add(join);
		panel.add(cancel);
		
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
			
		setSize(400,400);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Join();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		
		if(btn.getText().equals("������ ���")) {
			if(idInput.getText() != null && pwInput.getText() != null) {	// ���� Ȯ��
				if(adminDAO.overlapId(idInput.getText())) {	//���̵� �ߺ�Ȯ��
					//db�� ���� �߰�
					adminDTO = new Admin_DTO(idInput.getText(),pwInput.getText());	//textfield�� �ִ� �� ����
					adminDAO.JoinMember(adminDTO);	//����
					JOptionPane.showMessageDialog(null, "������ ��� ����!",
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
