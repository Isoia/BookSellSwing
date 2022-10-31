package user;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto_dao_db.Order_DAO;
import dto_dao_db.Order_DTO;
import dto_dao_db.Order_DTO2;
import dto_dao_db.User_DAO;
public class SellList extends JPanel{
	ArrayList<Order_DTO> bookList = null;
	ArrayList<Order_DTO2> bookList2 = null;
	Order_DAO orderDAO = new Order_DAO();
	void sellUserList() {
		bookList2 = orderDAO.getSellList(User_DAO.id);
	}
	
	public SellList() {
		Vector<String> title = new Vector<>();
		title.add("책이름");
		title.add("구매자 이름");
		title.add("책 갯수");
		title.add("책 가격");
		
		sellUserList();
		
		DefaultTableModel model = new DefaultTableModel(title,0);
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		
		add(scroll);
		
		for(Order_DTO2 dto : bookList2) {
			Vector<String> list = new Vector<>();
			list.add(dto.getBookname());
			list.add(dto.getUsername());
			list.add(String.valueOf(dto.getBookcount()));
			list.add(String.valueOf(dto.getBookprice()));
			model.addRow(list);
		}
	}
}
