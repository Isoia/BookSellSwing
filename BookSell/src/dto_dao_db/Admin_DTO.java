package dto_dao_db;

public class Admin_DTO {
	private String adminid;
	private String adminpw;
	
	public Admin_DTO(String adminid,String adminpw) {
		this.adminid = adminid;
		this.adminpw = adminpw;
	}

	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	public String getAdminpw() {
		return adminpw;
	}

	public void setAdminpw(String adminpw) {
		this.adminpw = adminpw;
	}
	
	
}
