package dto_dao_db;

public class Book_DTO {
	private int booknum;
	private String bookname;
	private String bookpub;
	private int bookprice;
	
	
	
	public Book_DTO(int booknum, String bookname, String bookpub, int bookprice) {
		super();
		this.booknum = booknum;
		this.bookname = bookname;
		this.bookpub = bookpub;
		this.bookprice = bookprice;
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
	public String getBookpub() {
		return bookpub;
	}
	public void setBookpub(String bookpub) {
		this.bookpub = bookpub;
	}
	public int getBookprice() {
		return bookprice;
	}
	public void setBookprice(int bookprice) {
		this.bookprice = bookprice;
	}
	
	
	
}
