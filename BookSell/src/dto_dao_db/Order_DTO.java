package dto_dao_db;

public class Order_DTO {
	private int ordernum;
	private int booknum;
	private String memid;
	private String bookname;
	private int bookcount;
	private int bookprice;
	
	public Order_DTO(int ordernum, int booknum, String bookname, String memid, int bookcount, int bookprice) {
		this.ordernum = ordernum;
		this.booknum = booknum;
		this.bookname = bookname;
		this.memid = memid;
		this.bookcount = bookcount;
		this.bookprice = bookprice;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

	public int getBooknum() {
		return booknum;
	}

	public void setBooknum(int booknum) {
		this.booknum = booknum;
	}
	
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public int getBookcount() {
		return bookcount;
	}

	public void setBookcount(int bookcount) {
		this.bookcount = bookcount;
	}

	public int getBookprice() {
		return bookprice;
	}

	public void setBookprice(int bookprice) {
		this.bookprice = bookprice;
	}
	
	
	
}
