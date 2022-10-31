package dto_dao_db;

public class User_DTO {
	private String memid;
	private String memname;
	private String mempw;
	private String memphone;
	private String memaddr;
	
	
	
	public User_DTO(String memid, String memname, String mempw, String memphone, String memaddr) {
		super();
		this.memid = memid;
		this.memname = memname;
		this.mempw = mempw;
		this.memphone = memphone;
		this.memaddr = memaddr;
	}
	
	public String getMemid() {
		return memid;
	}
	public void setMemid(String memid) {
		this.memid = memid;
	}
	public String getMemname() {
		return memname;
	}
	public void setMemname(String memname) {
		this.memname = memname;
	}
	public String getMempw() {
		return mempw;
	}
	public void setMempw(String mempw) {
		this.mempw = mempw;
	}
	public String getMemphone() {
		return memphone;
	}
	public void setMemphone(String memphone) {
		this.memphone = memphone;
	}
	public String getMemaddr() {
		return memaddr;
	}
	public void setMemaddr(String memaddr) {
		this.memaddr = memaddr;
	}
	
	

}
