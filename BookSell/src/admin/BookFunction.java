package admin;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dto_dao_db.Book_DAO;
import dto_dao_db.Book_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class BookFunction extends JPanel implements ActionListener{
	Book_DAO bookDAO = new Book_DAO();
	ArrayList<Book_DTO> bookList = new ArrayList<>();
	DefaultTableModel model;
	
	void callBookList() {
		bookList = bookDAO.getBookList();
	}
	
	public BookFunction() {
		setLayout(new FlowLayout());
		JButton btn1 = new JButton("조회");
		btn1.addActionListener(this);
		add(btn1);
		
		JButton btn2 = new JButton("추가");
		btn2.addActionListener(this);
		add(btn2);
		
		JButton btn3 = new JButton("삭제");
		btn3.addActionListener(this);
		add(btn3);
		
		callBookList();
		
		JPanel list = new JPanel();
		Vector<String> title = new Vector<>();
		title.add("책번호");
		title.add("책이름");
		title.add("저자");
		title.add("가격");
		
		model = new DefaultTableModel(title,0);
		JTable table = new JTable(model);
		
		JScrollPane scroll = new JScrollPane(table);
		
		this.add(scroll);
		
		for(Book_DTO dto : bookList) {
			Vector<String> book = new Vector<>();
			book.add(String.valueOf(dto.getBooknum()));
			book.add(dto.getBookname());
			book.add(dto.getBookpub());
			book.add(String.valueOf(dto.getBookprice()));
			
			model.addRow(book);
		}
	}

	public static void Main(String[] args) {
		new BookFunction();
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		JButton btn = (JButton)e.getSource();
		MainFrame mf = (MainFrame)btn.getTopLevelAncestor();
		
		if(btn.getText().equals("조회")) {
			mf.viewPanel(new CheckBook());
		}else if(btn.getText().equals("추가")) {
			if(model.getColumnCount() != 0) {
				for(int i=0; i<model.getRowCount();i++) {
					model.removeRow(i);
				}
			}
			new AddBook();		
		}else {
			new RemoveBook();
		}
	}
}
