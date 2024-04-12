package baitapjava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
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
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class baiTapJava extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpass;
	String secretKey = "MySecretKey";
    String salt = "MySalt";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					baiTapJava frame = new baiTapJava();
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
	public baiTapJava() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 577, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtusername = new JTextField();
		txtusername.setBounds(165, 84, 315, 43);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tài Khoản:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(37, 84, 133, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblMtKhu = new JLabel("Mật Khẩu:");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMtKhu.setBounds(37, 184, 133, 43);
		contentPane.add(lblMtKhu);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(165, 188, 315, 39);
		contentPane.add(txtpass);
		
		JButton btnNewButton = new JButton("ĐĂNG NHẬP");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnNewButton_actionPerformed(e);
			}
		});
		btnNewButton.setBounds(305, 262, 185, 43);
		contentPane.add(btnNewButton);
		
		JButton btnngK = new JButton("Đăng Kí");
		btnngK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				do_btnngK_actionPerformed(e);
			}
		});
		btnngK.setBounds(66, 262, 185, 43);
		contentPane.add(btnngK);
	}
	protected void do_btnNewButton_actionPerformed(ActionEvent e) {
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,user,password);
			
			String sql = "select*from dbo.baitapjava where taikhoan =? and matkhau =?";
			PreparedStatement ps = conn.prepareCall(sql);
			ps.setString(1, txtusername.getText());
			ps.setString(2, txtpass.getText());
			rs= ps.executeQuery();
			if(txtusername.getText().equals("")||txtpass.getText().equals("")) {
				JOptionPane.showMessageDialog(this,"Chưa nhập tài khoản và mật khẩu" );
				
			}else if(rs.next()) {
				JOptionPane.showMessageDialog(this, "DANG NHAP THANH CONG!");
				
			}
			else {
				JOptionPane.showMessageDialog(this, "dang nhap that bai");
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
		
	
	protected void do_btnngK_actionPerformed(ActionEvent e) {
		formdangki k = new formdangki();
		k.setVisible(true);
		
		
		
	}
}
