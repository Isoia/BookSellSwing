package dto_dao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Order_DAO {
	DBConnect dbCon = new DBConnect();
	Connection con = dbCon.getConnection();
	
	public ArrayList<Order_DTO> getAllSellList(){
		ArrayList<Order_DTO> sellerList = new ArrayList<>();
		String sql = "select * from orderlist";
		int ordernum;
		int booknum;
		String bookname;
		String memid;
		int bookcount;
		int price;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				ordernum = rs.getInt("ordernum");
				booknum = rs.getInt("booknum");
				bookname = rs.getString("bookname");
				memid = rs.getString("memid");
				bookcount = rs.getInt("bookcount");
				price = rs.getInt("bookprice");

				Order_DTO user = new Order_DTO(ordernum,booknum,bookname,memid,bookcount,price);
				sellerList.add(user);
				System.out.println(bookname);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sellerList;
	}
	
	public ArrayList<Order_DTO2> getSellList(String id){		//유저의 구매목록을 가져옴(4개의 항목만)
		ArrayList<Order_DTO2> booklist = new ArrayList<>();
		Order_DTO2 user;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql = "";
		String memname = null;
		try {
			
			sql = "select memname from memberlist where memid = ?";		
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			rs.next();
			memname = rs.getString("memname");
			
			sql = "select * from orderlist where memid = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new Order_DTO2(rs.getString("bookname")
						,memname,rs.getInt("bookcount"),rs.getInt("bookprice"));
				booklist.add(user);
			}
			return booklist;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
