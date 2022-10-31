package admin;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto_dao_db.Book_DAO;
import dto_dao_db.Book_DTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class CheckBook extends JPanel implements ActionListener{
	ArrayList<Book_DTO> bookList = null;
	Book_DAO bookDAO = new Book_DAO();
	
	void callBookList() {
		bookList = bookDAO.getBookList();
	}
	
	public CheckBook() {
		Vector<String> title = new Vector<>();
		title.add("책번호");
		title.add("책이름");
		title.add("저자");
		title.add("가격");
		
		callBookList();
		
		DefaultTableModel model = new DefaultTableModel(title,0);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
