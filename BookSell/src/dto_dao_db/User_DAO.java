package dto_dao_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class User_DAO {
	DBConnect dbCon = new DBConnect();
	Connection con = dbCon.getConnection();
	public static String id = null;
	public Boolean LoginCheck(String id, String pw) {	//�α��� id pw db�� üũ
		String sql = "select * from memberlist";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("memid").equals(id) && rs.getString("mempw").equals(pw)) {
					this.id = id;
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Boolean overlapId(String id) {				//ȸ������ �� �� �ߺ��Ǵ� id�� �ֳ� Ȯ��
		String sql = "select memid from memberlist";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("memid").equals(id)) {
					System.out.println("false");
					return false;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public void JoinMember(User_DTO user) {					// ȸ������
		String sql = "insert into memberlist values(?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getMemid());
			pstmt.setString(2, user.getMemname());
			pstmt.setString(3, user.getMempw());
			pstmt.setString(4, user.getMemphone());
			pstmt.setString(5, user.getMemaddr());
			int row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<User_DTO> getUserList(){		//��ü ȸ�� ��ȸ
		String sql = "select * from memberlist";
		ArrayList<User_DTO> userList = new ArrayList<>();
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String memid = rs.getString("memid");
				String memname = rs.getString("memname");
				String mempw = rs.getString("mempw");
				String memphone = rs.getString("memphone");
				String memaddr = rs.getString("memaddre");
				userList.add(new User_DTO(memid,memname,mempw,memphone,memaddr));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public boolean updateUser(User_DTO user, String notChangeName) {		//����� ���� ����
		String sql = "update memberlist set memid = ?, memname = ?, mempw = ?, memphone = ?, memaddre = ? where memname = ?";
		String beforeName = notChangeName;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,user.getMemid());
			pstmt.setString(2, user.getMemname());
			pstmt.setString(3, user.getMempw());
			pstmt.setString(4, user.getMemphone());
			pstmt.setString(5, user.getMemaddr());
			pstmt.setString(6, beforeName);
			
			int row = pstmt.executeUpdate();
			if(row >= 1) {		//���������� update�� �ݿ� ������
				return true;
			}
			else {				// update�� ������ �ȉ�����
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean removeUser(String name) {
		String sql = "select memname from memberlist where memname = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {		//�̸��� ������
				sql = "delete from memberlist where memname = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.executeUpdate();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public User_DTO findUser(String name) {					//Ư�� ����ã��
		String sql = "select * from memberlist where memname = ?";
		String memid,memname,mempw,memphone,memaddr;
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			memid = rs.getString("memid");
			memname = rs.getString("memname");
			mempw = rs.getString("mempw");
			memphone = rs.getString("memphone");
			memaddr = rs.getString("memaddre");
			User_DTO user = new User_DTO(memid,memname,mempw,memphone,memaddr);
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public String getUser() {
		return this.id;
	}
}
