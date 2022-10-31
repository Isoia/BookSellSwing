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
		btn1 = new JButton("����");
		btn1.addActionListener(this);
		btn2 = new JButton("����");
		btn2.addActionListener(this);
		jp1 = new JPanel(new FlowLayout());
		jp1.add(btn1);
		jp1.add(btn2);
		add(jp1);
		Vector<String> title = new Vector<>();
		title.add("ID");
		title.add("�̸�");
		title.add("PW");
		title.add("�޴���");
		title.add("�ּ�");
		
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
		
		if(btns.getText().equals("����")) {	//����
			String name = JOptionPane.showInputDialog("������ ������� �̸��� �Է��ϼ���.");
			if(userDAO.removeUser(name)) {	//������ ���������� �̷��������
				JOptionPane.showMessageDialog(null, "���� �Ϸ�!",
						"���� ����",JOptionPane.PLAIN_MESSAGE);
				printMemberList();
			}else {							//������ ���������� �̷������ �ʾ�����
				JOptionPane.showMessageDialog(null, "����� �̸��� �ٽ� Ȯ���ϼ���!",
						"���� ����",JOptionPane.PLAIN_MESSAGE);
			}
			
		}else {								//����
			String name = JOptionPane.showInputDialog("������ ������� �̸��� �Է��ϼ���.");
			if(userDAO.findUser(name) != null) {
				updateUser = userDAO.findUser(name);
				new UpdateMember(updateUser);
				printMemberList();
			}else {
				JOptionPane.showMessageDialog(null, "����� �̸��� �ٽ� Ȯ���ϼ���!",
						"���� ����",JOptionPane.PLAIN_MESSAGE);
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
			String[] answer = {"����", "����", "���"};

			int ans = JOptionPane.showOptionDialog(this, "���� �۾��� �Ͻǰǰ���", "�޴�â", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
					answer, null);
			
			if(ans == 0) {			//���� ����
				int row = tb.getSelectedRow();
				String name = (String)tb.getValueAt(row, 1);
				
				if(userDAO.findUser(name) != null) {
					updateUser = userDAO.findUser(name);
					new UpdateMember(updateUser);
					printMemberList();
				}else {
					JOptionPane.showMessageDialog(null, "����� �̸��� �ٽ� Ȯ���ϼ���!",
							"���� ����",JOptionPane.PLAIN_MESSAGE);
				}
				
			}else if(ans == 1) {	//���� ����
				int row = tb.getSelectedRow();
				String name = (String)tb.getValueAt(row, 1);
				if(userDAO.removeUser(name)) {			//���� ������
					JOptionPane.showMessageDialog(null, "���� �Ϸ�!",
							"���� ����",JOptionPane.PLAIN_MESSAGE);
					printMemberList();
				}else {							//������ ���������� �̷������ �ʾ�����
					JOptionPane.showMessageDialog(null, "����� �̸��� �ٽ� Ȯ���ϼ���!",
							"���� ����",JOptionPane.PLAIN_MESSAGE);
				}
			}else {					//��� ����
				
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
