package admin;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto_dao_db.Book_DTO;
import dto_dao_db.User_DAO;
import dto_dao_db.User_DTO;

public class MemberList extends JPanel implements ActionListener, MouseListener{
	JPanel jp1,jp2;
	JButton btn1,btn2;
	ArrayList<User_DTO> userDTO = new ArrayList<>();
	Vector<String> user;
	User_DAO userDAO = new User_DAO();
	User_DTO updateUser;
	DefaultTableModel model;
	JTable table;
	JScrollPane scroll;
	String name;
	int select;
	int sw = 0;

	void printMemberList() {
		for(int i=0;i<model.getRowCount();i++) {
			model.removeRow(i);
			i--;
			sw = 1;
			if(sw == 0) {
				return;
			}
		}
		userDTO = userDAO.getUserList();
		
		for(User_DTO dto : userDTO) {
			user = new Vector<>();
			user.add(dto.getMemid());
			user.add(dto.getMemname());
			user.add(dto.getMempw());
			user.add(dto.getMemphone());
			user.add(dto.getMemaddr());
			
			model.addRow(user);
		}
	}
	
	public MemberList() {
		btn1 = new JButton("수정");
		btn1.addActionListener(this);
		btn2 = new JButton("삭제");
		btn2.addActionListener(this);
		jp1 = new JPanel(new FlowLayout());
		jp1.add(btn1);
		jp1.add(btn2);
		add(jp1);
		Vector<String> title = new Vector<>();
		title.add("ID");
		title.add("이름");
		title.add("PW");
		title.add("휴대폰");
		title.add("주소");
		
		model = new DefaultTableModel(title,0);
		table = new JTable(model);
		table.addMouseListener(this);
		scroll = new JScrollPane(table);
		add(scroll);
		
		userDTO = userDAO.getUserList();
		
		for(User_DTO users : userDTO) {
			user = new Vector<>();
			user.add(users.getMemid());
			user.add(users.getMemname());
			user.add(users.getMempw());
			user.add(users.getMemphone());
			user.add(users.getMemaddr());
			
			model.addRow(user);
		}
	}

	public static void main(String[] args) {

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btns = (JButton)e.getSource();
		User_DTO user;
		
		if(btns.getText().equals("삭제")) {	//삭제
			String name = JOptionPane.showInputDialog("삭제할 사용자의 이름을 입력하세요.");
			if(userDAO.removeUser(name)) {	//삭제가 성공적으로 이루어졌을때
				JOptionPane.showMessageDialog(null, "삭제 완료!",
						"삭제 성공",JOptionPane.PLAIN_MESSAGE);
				printMemberList();
			}else {							//삭제가 성공적으로 이루어지지 않았을때
				JOptionPane.showMessageDialog(null, "사용자 이름을 다시 확인하세요!",
						"삭제 실패",JOptionPane.PLAIN_MESSAGE);
			}
			
		}else {								//수정
			String name = JOptionPane.showInputDialog("수정할 사용자의 이름을 입력하세요.");
			if(userDAO.findUser(name) != null) {
				updateUser = userDAO.findUser(name);
				new UpdateMember(updateUser);
				printMemberList();
			}else {
				JOptionPane.showMessageDialog(null, "사용자 이름을 다시 확인하세요!",
						"수정 실패",JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		JTable tb = (JTable)e.getSource();
		if (e.getClickCount() == 2) { 
			String[] answer = {"수정", "삭제", "취소"};

			int ans = JOptionPane.showOptionDialog(this, "무슨 작업을 하실건가요", "메뉴창", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					answer, null);
			
			if(ans == 0) {			//수정 선택
				int row = tb.getSelectedRow();
				String name = (String)tb.getValueAt(row, 1);
				
				if(userDAO.findUser(name) != null) {
					updateUser = userDAO.findUser(name);
					new UpdateMember(updateUser);
					printMemberList();
				}else {
					JOptionPane.showMessageDialog(null, "사용자 이름을 다시 확인하세요!",
							"수정 실패",JOptionPane.PLAIN_MESSAGE);
				}
				
			}else if(ans == 1) {	//삭제 선택
				int row = tb.getSelectedRow();
				String name = (String)tb.getValueAt(row, 1);
				if(userDAO.removeUser(name)) {			//삭제 성공시
					JOptionPane.showMessageDialog(null, "삭제 완료!",
							"삭제 성공",JOptionPane.PLAIN_MESSAGE);
					printMemberList();
				}else {							//삭제가 성공적으로 이루어지지 않았을때
					JOptionPane.showMessageDialog(null, "사용자 이름을 다시 확인하세요!",
							"삭제 실패",JOptionPane.PLAIN_MESSAGE);
				}
			}else {					//취소 선택
				
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
