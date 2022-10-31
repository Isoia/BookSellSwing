package admin;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.protocol.Resultset;

import dto_dao_db.DBConnect;

public class AddBook extends JPanel implements ActionListener{
	JTextField numInput;
	JTextField nameInput;
	JTextField pubInput;
	JTextField priceInput;
	
	DBConnect dbcon = new DBConnect();
	Connection con = dbcon.getConnection();
	
	public AddBook() {
		
		JButton join = new JButton("추가");
		
		numInput = new JTextField(10);
		nameInput = new JTextField(10);
		pubInput = new JTextField(10);
		priceInput = new JTextField(10);
		
		JPanel idPanel = new JPanel();
		idPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		idPanel.add(new JLabel("책번호 : "));
		idPanel.add(numInput);
			
		JPanel pwPanel = new JPanel();
		pwPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pwPanel.add(new JLabel("책이름 : "));
		pwPanel.add(nameInput);
			
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		namePanel.add(new JLabel("저   자 : "));
		namePanel.add(pubInput);
			
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		phonePanel.add(new JLabel("가   격 : "));
		phonePanel.add(priceInput);
			
		JPanel formPanel = new JPanel();
		formPanel.setLayout(new GridLayout(4, 1));
		formPanel.add(idPanel);
		formPanel.add(pwPanel);
		formPanel.add(namePanel);
		formPanel.add(phonePanel);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.add(formPanel);
		
		JPanel panel = new JPanel();
		join.addActionListener(this);
		panel.add(join);
		
		add(contentPanel, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
			
		setSize(400,300);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AddBook();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String sql = "select bookname from booklist where bookname = ?";
		int k = 0;
		JButton btn = (JButton)e.getSource();
		PreparedStatement pstmt;
		ResultSet rs;
		if(btn.getText().equals("추가")) {
			if(numInput.getText().equals("") || nameInput.getText().equals("")
					|| pubInput.getText().equals("") || priceInput.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "빈칸이 있습니다! 다시 확인하세요",
						"추가 실패",JOptionPane.PLAIN_MESSAGE);		
			}else {			//공백이 있을 시 
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, nameInput.getText());
					rs = pstmt.executeQuery();
					if(rs.next()) {		//중복된 책이 있을 시 
						JOptionPane.showMessageDialog(null, "중복된 도서입니다!",
								"추가 실패",JOptionPane.PLAIN_MESSAGE);
					}else {		// 없을 시
						sql = "select booknum from booklist";		// 기본키인 책번호 중복 확인
						pstmt = con.prepareStatement(sql);
						rs = pstmt.executeQuery();
						while(rs.next()) {
							int num = Integer.parseInt(numInput.getText());
							if(num == rs.getInt("booknum")) {
								k = 1;
							}
						}
						if(k == 0) {
							sql = "insert into booklist values(?,?,?,?)";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, Integer.parseInt(numInput.getText()));
							pstmt.setString(2, nameInput.getText());
							pstmt.setString(3, pubInput.getText());
							pstmt.setInt(4, Integer.parseInt(priceInput.getText()));
							
							int row = pstmt.executeUpdate();
							JOptionPane.showMessageDialog(null, "추가 완료!",
													"추가 성공",JOptionPane.PLAIN_MESSAGE);
							new BookFunction();
						}
						else {
							JOptionPane.showMessageDialog(null, "책번호가 중복입니다!",
									"추가 실패",JOptionPane.PLAIN_MESSAGE);
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		
	}

}
