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
		setTitle("관리자 등록");
		
		JButton join = new JButton("관리자 등록");
		JButton cancel = new JButton("취소");
		
		idInput = new JTextField(10);
		pwInput = new JPasswordField(10);
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("아이디 : "));
		idPanel.add(idInput);
			
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwPanel.add(new JLabel("비밀번호 : "));
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
		
		if(btn.getText().equals("관리자 등록")) {
			if(idInput.getText() != null && pwInput.getText() != null) {	// 공백 확인
				if(adminDAO.overlapId(idInput.getText())) {	//아이디 중복확인
					//db에 정보 추가
					adminDTO = new Admin_DTO(idInput.getText(),pwInput.getText());	//textfield에 있는 값 저장
					adminDAO.JoinMember(adminDTO);	//가입
					JOptionPane.showMessageDialog(null, "관리자 등록 성공!",
							"회원가입 성공",JOptionPane.PLAIN_MESSAGE);
					new Login();
					dispose();
				}else {
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다!",
							"회원가입 실패",JOptionPane.PLAIN_MESSAGE);
				}
				
			}else {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다! 다시 확인하세요",
						"회원가입 실패",JOptionPane.PLAIN_MESSAGE);
			}
		}
		else if(btn.getText().equals("취소")){
			new Login();
			dispose();
		}
	}

}
