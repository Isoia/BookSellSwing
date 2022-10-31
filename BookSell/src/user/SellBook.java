package user;
import java.awt.*;
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

import dto_dao_db.Book_DAO;
import dto_dao_db.Book_DTO;
public class SellBook extends JPanel implements ActionListener, MouseListener{
	ArrayList<Book_DTO> bookList = null;
	Book_DAO bookDAO = new Book_DAO();
	JLabel lb1, lb2;
	JTextField tf1, tf2;
	JButton btn;
	
	void callBookList() {
		bookList = bookDAO.getBookList();
	}
	
	public SellBook() {
		JPanel jp1 = new JPanel(new FlowLayout());
		Book_DAO bookDAO = new Book_DAO();
		setLayout(new FlowLayout());
		lb1 = new JLabel("책 이름");
		lb2 = new JLabel("구매갯수");
		
		tf1 = new JTextField(15);
		tf2 = new JTextField(5);
		
		btn = new JButton("구매");
		btn.addActionListener(this);
		
		jp1.add(lb1);
		jp1.add(tf1);
		jp1.add(lb2);
		jp1.add(tf2);
		jp1.add(btn);
		
		add(jp1);
		
		Vector<String> title = new Vector<>();
		title.add("책번호");
		title.add("책이름");
		title.add("저자");
		title.add("가격");
		
		callBookList();
		
		DefaultTableModel model = new DefaultTableModel(title,0);
		JTable table = new JTable(model);
		table.addMouseListener(this);
		
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
		String bookname = tf1.getText();
		int count = Integer.parseInt(tf2.getText());
		
		if(bookDAO.sellBook(bookname, count)) {
			JOptionPane.showMessageDialog(null, "책 구매 완료!",
					"구매 완료!",JOptionPane.PLAIN_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(null, "그런 책은 없습니다!",
					"구매 실패",JOptionPane.PLAIN_MESSAGE);
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
