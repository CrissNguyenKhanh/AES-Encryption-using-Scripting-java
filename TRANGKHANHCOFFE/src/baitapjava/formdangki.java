package baitapjava;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;
public class formdangki extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txttaikhoan;
	private JPasswordField txtpass1;
	private JPasswordField txtpss2;
   private ArrayList<taikhoanmatkhau>  list;
   String secretKey = "MySecretKey";
   String salt = "MySalt";
	/**
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formdangki frame = new formdangki();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	   String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	   String url ="jdbc:sqlserver://localhost:1433;databaseName=Tutorial";
	   String user = "sa";
	   String password = "123456789";
	   Statement st;
	   ResultSet rs;
	   
	public formdangki() {
		list = new ArrayList<taikhoanmatkhau>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 42, 101, 39);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Tài Khoản:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(33, 67, 133, 43);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblMtKhu = new JLabel("Mật Khẩu:");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhu.setBounds(33, 156, 133, 43);
		contentPane.add(lblMtKhu);
		
		txttaikhoan = new JTextField();
		txttaikhoan.setBounds(193, 75, 264, 34);
		contentPane.add(txttaikhoan);
		txttaikhoan.setColumns(10);
		
		txtpass1 = new JPasswordField();
		txtpass1.setBounds(193, 164, 264, 34);
		contentPane.add(txtpass1);
		
		JLabel lblXcNhnMt = new JLabel("Xác Nhận Mật Khẩu:");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblXcNhnMt.setBounds(10, 249, 166, 43);
		contentPane.add(lblXcNhnMt);
		
		txtpss2 = new JPasswordField();
		txtpss2.setBounds(193, 249, 264, 28);
		contentPane.add(txtpss2);
		
		JButton btnNewButton = new JButton("Đăng KÍ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(154, 333, 157, 61);
		contentPane.add(btnNewButton);
		loaddatatoarraylist();
	}
	public void loaddatatoarraylist() {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,password);
			
			String sql = "select * from dbo.baitapjava";
		    PreparedStatement ps =conn.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    list.clear();
		    while(rs.next()) {
		    	String username = rs.getString(1);
		    	String password = rs.getString(2);
		    	taikhoanmatkhau k = new taikhoanmatkhau(username, password);
		    	
		    	list.add(k);
		    }
		    conn.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
}

	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		if(txtpss2.getText() .equalsIgnoreCase(txtpass1.getText())) {
			try {

				int row =0;
				Class.forName(driver);
				String originalString = txttaikhoan.getText();
				 String originalstring2 =  txtpass1.getText();
				 String encryptedString = AES256.encrypt(originalString, secretKey, salt);
				 String encryptedString2 = AES256.encrypt(originalstring2, secretKey, salt);
				Connection conn = DriverManager.getConnection(url,user,password);
				String sql = " INSERT INTO dbo.baitapjava values(?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, encryptedString);
				ps.setString(2, encryptedString2);
				
				
				row = ps.executeUpdate();
				if(row > 0 ) {
					JOptionPane.showMessageDialog(this, "THÊM THÀNH CÔNG");
					
				}else {
					JOptionPane.showConfirmDialog(this, "THÊM THẤT BẠI!!");
				}
			} catch (Exception e2) {
				e2.printStackTrace();
				JOptionPane.showMessageDialog(this, "error");
			}
			loaddatatoarraylist();
			} else {
				JOptionPane.showMessageDialog(null, "vui long nhap lai cho dung mat khau");
			}
		}
	}

