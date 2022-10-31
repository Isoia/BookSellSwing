package admin;
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
import dto_dao_db.Order_DAO;
import dto_dao_db.Order_DTO;
import dto_dao_db.User_DAO;
public class SellList extends JPanel implements ActionListener, MouseListener{
	ArrayList<Order_DTO> sellerList = null;
	Order_DAO orderDAO = new Order_DAO();
	JLabel lb1, lb2;
	JTextField tf1, tf2;
	JButton btn;
	
	void callSellerList() {
		sellerList = orderDAO.getAllSellList();
	}
	
	public SellList() {
		JPanel jp1 = new JPanel(new FlowLayout());
		Book_DAO bookDAO = new Book_DAO();
		setLayout(new FlowLayout());
		
		add(jp1);
		
		Vector<String> title = new Vector<>();
		title.add("주문번호");
		title.add("책번호");
		title.add("책이름");
		title.add("사용자ID");
		title.add("구매갯수");
		title.add("가격");
		
		callSellerList();
		
		DefaultTableModel model = new DefaultTableModel(title,0);
		JTable table = new JTable(model);
		table.addMouseListener(this);
		
		JScrollPane scroll = new JScrollPane(table);
		
		this.add(scroll);
		
		for(Order_DTO dto : sellerList) {
			Vector<String> seller = new Vector<>();
			seller.add(String.valueOf(dto.getOrdernum()));
			seller.add(String.valueOf(dto.getBooknum()));
			seller.add(dto.getBookname());
			seller.add(dto.getMemid());
			seller.add(String.valueOf(dto.getBookcount()));
			seller.add(String.valueOf(dto.getBookprice()));
			
			model.addRow(seller);
		}
	}
	public static void Main(String[] args) {
		new SellList();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
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
