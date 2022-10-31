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
	int complete=0;		// 수정 후 리스트 새로 갱신을 위한 변수
	
	int callComplete() {
		return complete;
	}
	
	public UpdateMember(User_DTO updateUser) {	
		insertUser = new User_DTO(updateUser.getMemid(),updateUser.getMemname(),updateUser.getMempw(),
				updateUser.getMemphone(), updateUser.getMemaddr());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("정보 수정");
		JPanel jp1,jp2,jp3,jp4,jp5,jp6,jp7;
		JLabel lb1,lb2,lb3,lb4,lb5;		
		
		btn1 = new JButton("수정");
		btn2 = new JButton("이전으로");
		
		jp6 = new JPanel(new GridLayout(5,1));
		jp7 = new JPanel(new FlowLayout());
		
		lb1 = new JLabel("ID");
		tf1 = new JTextField(15);
		tf1.setText(insertUser.getMemid());
		jp1 = new JPanel(new FlowLayout());
		jp1.add(lb1);
		jp1.add(tf1);
		
		lb2 = new JLabel("이름");
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
		
		lb4 = new JLabel("휴대폰");
		tf4 = new JTextField(15);
		tf4.setText(insertUser.getMemphone());
		jp4 = new JPanel(new FlowLayout());
		jp4.add(lb4);
		jp4.add(tf4);
		
		lb5 = new JLabel("주소");
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
		
		if(btn.getText().equals("수정")) {		//수정버튼 클릭 시
			if(tf1.getText().equals("") || tf2.getText().equals("")
					|| tf3.getText().equals("") || tf4.getText().equals("")){		// 입력칸에 공백이 있을 시
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다! 다시 확인하세요",
						"추가 실패",JOptionPane.PLAIN_MESSAGE);
			}
			else {					// 다 입력했을시
				User_DTO updateUser = new User_DTO(tf1.getText(),tf2.getText(),tf3.getText(),tf4.getText(),tf5.getText());
				if(user.updateUser(updateUser, beforeName)) {		//추가 성공시
					JOptionPane.showMessageDialog(null, "수정 성공!",
							"추가 성공",JOptionPane.PLAIN_MESSAGE);
					complete = 1;
				}else {				//추가 실패시
					JOptionPane.showMessageDialog(null, "수정 실패",
							"추가 실패",JOptionPane.PLAIN_MESSAGE);
				}
			}
		}else {									//이전으로버튼 클릭 시
			dispose();
			new MemberList();
		}
	}
}
