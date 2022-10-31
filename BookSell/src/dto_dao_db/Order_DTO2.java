package dto_dao_db;

public class Order_DTO2 {
	private String bookname;
	private String username;
	private int bookcount;
	private int bookprice;
	
	public Order_DTO2(String bookname, String username, int bookcount, int bookprice) {
		super();
		this.bookname = bookname;
		this.username = username;
		this.bookcount = bookcount;
		this.bookprice = bookprice;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
