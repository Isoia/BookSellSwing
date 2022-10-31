package dto_dao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Book_DAO {
	DBConnect dbCon = new DBConnect();
	Connection con = dbCon.getConnection();
	Book_DTO bookDTO = null;
	User_DAO userDAO = new User_DAO();
	
	public ArrayList<Book_DTO> getBookList(){				//전체 도서 검색
		ArrayList<Book_DTO> bookList = new ArrayList<>();
		String sql = "select * from booklist";
		int booknum = 0;
		String bookname = null;
		String bookpub = null;
		int bookprice = 0;
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				booknum = rs.getInt("booknum");
				bookname = rs.getString("bookname");
				bookpub = rs.getString("bookpub");
				bookprice = rs.getInt("bookprice");
				bookList.add(new Book_DTO(booknum,bookname,bookpub,bookprice));
				System.out.println(bookname);
			}
			System.out.println("---------------------");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookList;
	}
	
	public ArrayList<Book_DTO> getSearchList(String bookName){		// 특정 도서 검색
		ArrayList<Book_DTO> bookSearchList = null;
		int i = 1;
		String sql = "select * from booklist where bookname like ?";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookName+"%");
			ResultSet rs = pstmt.executeQuery();
//			if(rs.next() == true) {
//				bookSearchList = new ArrayList<>();
//				rs.beforeFirst();
//			}
			while(rs.next()) {
				if(i == 1) {
					bookSearchList = new ArrayList<>();
					i++;
				}
				bookSearchList.add(new Book_DTO(rs.getInt("booknum"), rs.getString("bookname"), 
						rs.getString("bookpub"), rs.getInt("bookprice")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NullPointerException e) {
			
		}
		return bookSearchList;
	}
	
	public Boolean removeBook(String bookname) {			// 도서 삭제
		String sql = "select * from booklist where bookname = ?";	// 해당 도서가 있나 확인
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,bookname);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {				// 해당 도서가 있으면
				sql = "delete from booklist where bookname = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bookname);
				
				int row = pstmt.executeUpdate();
				return true;
			}else{						// 해당 도서가 없으면
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean sellBook(String bookname, int count) {		// 책 구매
		String sql = "select bookprice,booknum from booklist where bookname = ?";
		int price = 0;
		int num = 0;
		int lastOrder = 0;
		PreparedStatement pstmt;
		try {										//가격 갖고와서 갯수만큼 계산
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bookname);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				price = rs.getInt("bookprice");
				price *= count;
				num = rs.getInt("booknum");
			}
			
			sql = "select ordernum from orderlist";		//주문번호를 추가하기 위해(기본값) orderlist불러오기
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<Integer> listnum = new ArrayList<>();
			while(rs.next()) {
				listnum.add(rs.getInt("ordernum"));
			}
			 int ordernum = (int)(Math.random()*100000);
			for(int i=0; i<listnum.size(); i++) {
				 for(int j=0; j<i; j++) {
					 if(ordernum == listnum.get(i)) {
						 i--;
						 ordernum = (int)(Math.random()*100000);
					 }
				 }
			}
			
			sql = "insert into orderlist values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, ordernum);
			pstmt.setInt(2, num);
			pstmt.setString(3, bookname);
			pstmt.setString(4, User_DAO.id);
			pstmt.setInt(5, count);
			pstmt.setInt(6, price);
			System.out.println("7");
			System.out.println(User_DAO.id);
			int row = pstmt.executeUpdate();
			System.out.println("8");
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			return false;
		}
	}
}
