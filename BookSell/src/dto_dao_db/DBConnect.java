package dto_dao_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private Connection con = null;
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/bookselldb?serverTimezone=Asia/Seoul";
			String id = "root";
			String pw = "1234";
			
			con = DriverManager.getConnection(url,id,pw);
			
		} catch (ClassNotFoundException e) {
			System.out.println("����̹� �ε� ����");
		} catch (SQLException e) {
			System.out.println("���� ����");
		}
		return con;
	}

}
