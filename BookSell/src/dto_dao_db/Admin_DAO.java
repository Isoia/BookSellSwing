package dto_dao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin_DAO {
	DBConnect dbCon = new DBConnect();
	Connection con = dbCon.getConnection();
	public static String id = null;
	public Boolean LoginCheck(String id, String pw) {	//로그인 id pw db에 체크
		String sql = "select * from adminlist";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("adminid").equals(id) && rs.getString("adminpw").equals(pw)) {
					this.id = id;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean overlapId(String id) {				//관리자 가입 할 때 중복되는 id가 있나 확인
		String sql = "select adminid from adminlist";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("adminid").equals(id)) {
					System.out.println("false");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void JoinMember(Admin_DTO admin) {					// 회원가입
		String sql = "insert into adminlist values(?,?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, admin.getAdminid());
			pstmt.setString(2, admin.getAdminpw());
			int row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public String getAdmin() {
		return this.id;
	}
}
