package user;

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
public class SearchBook extends JPanel implements ActionListener{
	Book_DAO bookDAO = new Book_DAO();
	ArrayList<Book_DTO> bookList = new ArrayList<>();
	JTextField input;
	DefaultTableModel model;
	
	public SearchBook() {
		setLayout(new FlowLayout());
		JLabel name = new JLabel("책 이름 : ");
		input = new JTextField(15);
		JButton btn = new JButton("검색");
		btn.addActionListener(this);
		add(name);
		add(input);
		add(btn);
		
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
	}

	public static void Main(String[] args) {
		new SearchBook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = input.getText();
		if(model.getColumnCount() != 0) {
			for(int i=0; i<model.getRowCount();i++) {
				model.removeRow(i);
			}
		}
		try {
			bookList = bookDAO.getSearchList(name);
			for(Book_DTO dto : bookList) {
				Vector<String> searchList = new Vector<>();
				searchList.add(String.valueOf(dto.getBooknum()));
				searchList.add(dto.getBookname());
				searchList.add(dto.getBookpub());
				searchList.add(String.valueOf(dto.getBookprice()));
				model.addRow(searchList);
			}
		}catch(Exception e1) {
			JOptionPane.showMessageDialog(null, "찾는 책은 없습니다!",
					"찾기 실패",JOptionPane.PLAIN_MESSAGE);
		}
	}
}
