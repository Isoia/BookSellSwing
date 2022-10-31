package admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto_dao_db.Book_DAO;
import dto_dao_db.Book_DTO;
import dto_dao_db.DBConnect;

public class RemoveBook extends JPanel implements ActionListener, MouseListener{
	JPanel jp1;
	JTextField tf1;
	JButton btn;
	JLabel lb1;
	DefaultTableModel model;
	DBConnect dbcon = new DBConnect();
	Book_DAO bookDAO = new Book_DAO();
	ArrayList<Book_DTO> bookList = new ArrayList<>();
	Vector<String> book;
	Vector<String> title;
	JTable table;
	int sw = 0;
	
	void getBookList() {
		bookList = bookDAO.getBookList();
	}
	
	void printBookList() {
		for(int i=0;i<model.getRowCount();i++) {
			model.removeRow(i);
			i--;
			sw = 1;
			if(sw == 0) {
				return;
			}
		}
		bookList = bookDAO.getBookList();
		
		for(Book_DTO dto : bookList) {
			book = new Vector<>();
			book.add(String.valueOf(dto.getBooknum()));
			book.add(dto.getBookname());
			book.add(dto.getBookpub());
			book.add(String.valueOf(dto.getBookprice()));
			
			model.addRow(book);
		}
	}
	
	public RemoveBook() {	
		jp1 = new JPanel(new FlowLayout());
		lb1 = new JLabel("å �̸� : ");
		btn = new JButton("����");
		btn.addActionListener(this);
		tf1 = new JTextField(15);
		
		jp1.add(lb1);
		jp1.add(tf1);
		jp1.add(btn);
		add(jp1,BorderLayout.NORTH);
		
		getBookList();
		
		JPanel list = new JPanel();
		title = new Vector<>();
		title.add("å��ȣ");
		title.add("å�̸�");
		title.add("����");
		title.add("����");
		
		model = new DefaultTableModel(title,0);
		table = new JTable(model);
		table.addMouseListener(this);
		
		JScrollPane scroll = new JScrollPane(table);
		
		this.add(scroll);
		
		for(Book_DTO dto : bookList) {
			book = new Vector<>();
			book.add(String.valueOf(dto.getBooknum()));
			book.add(dto.getBookname());
			book.add(dto.getBookpub());
			book.add(String.valueOf(dto.getBookprice()));
			
			model.addRow(book);
		}
		
		setSize(400,400);
		setVisible(true);
	}

	public static void main(String[] args) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = tf1.getText();
		JButton btn = (JButton)e.getSource();
		if(btn.getText().equals("����")) {
			if(bookDAO.removeBook(name)) {	//���ϰ����� true�� ������ �̹� ���� �߰�
				JOptionPane.showMessageDialog(null, "���� �Ϸ�!",
						"���� ����",JOptionPane.PLAIN_MESSAGE);
				printBookList();
			}else {		//���ϰ����� false�� �Ѿ�� ������ ����
				JOptionPane.showMessageDialog(null, "�ش� ������ �����ϴ�!",
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
		int row = tb.getSelectedRow();
		
		String name = (String)tb.getValueAt(row, 1);
		tf1.setText(name);
		
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
