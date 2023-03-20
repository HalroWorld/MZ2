package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MZ_signUp {
	private JFrame frame;
	private JLabel usernameLabel, passwordLabel, ConfirmPasswordLabel;
	private JTextField usernameField;
	private JPasswordField passwordField, ConfirmPasswordField;
	private JButton CancelButton;
	private JButton signupButton;

	public MZ_signUp() {
		signUp();
	}

	public void signUp() {
		frame = new JFrame();
		frame.setBounds(100, 100, 602, 600);
		frame.getContentPane().setLayout(new GridLayout(3, 1, 0, 0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());
		frame.setTitle("맛-ZIP");

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel);
		JLabel MZ_logo = new JLabel("");

		// 로고 이미지 지정 및 불러오기
		MZ_logo.setIcon(new ImageIcon("./src/Img/mzducklogo1.png"));
		// 로고 가운데 정렬
		MZ_logo.setHorizontalAlignment(SwingConstants.CENTER);
		MZ_logo.setVerticalAlignment(SwingConstants.BOTTOM);
		panel.add(MZ_logo, BorderLayout.CENTER);

		JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel2.setBackground(new Color(255, 255, 255));

		frame.getContentPane().add(panel2);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		usernameLabel = new JLabel("아이디");
		usernameLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(usernameLabel, c);

		c.insets = new Insets(0, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		usernameField = new JTextField(10);
		usernameField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		usernameField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(usernameField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(20, 0, 0, 0);
		passwordLabel = new JLabel("비밀번호");
		passwordLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(passwordLabel, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 1;
		passwordField = new JPasswordField(10);
		passwordField.setEchoChar('*');
		passwordField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		passwordField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		passwordField.setForeground(Color.BLACK);
		panel2.add(passwordField, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20, 0, 0, 0);
		ConfirmPasswordLabel = new JLabel("비밀번호 확인");
		ConfirmPasswordLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		panel2.add(ConfirmPasswordLabel, c);

		c.insets = new Insets(20, 10, 0, 0);
		c.gridx = 1;
		c.gridy = 2;
		ConfirmPasswordField = new JPasswordField(10);
		ConfirmPasswordField.setEchoChar('*');
		ConfirmPasswordField.setBorder(new LineBorder(new Color(228, 228, 228), 2, true));
		ConfirmPasswordField.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 22));
		ConfirmPasswordField.setForeground(Color.BLACK);
		panel2.add(ConfirmPasswordField, c);

		JPanel panel3 = new JPanel();
		panel3.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(panel3);

		CancelButton = new JButton("취소");
		CancelButton.setForeground(new Color(2, 2, 2));
		CancelButton.setBackground(new Color(255, 255, 255));
		CancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MZ_login();
				frame.dispose();
			}
		});

		panel3.add(CancelButton);

		ActionListener signUp = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				char[] pass = passwordField.getPassword();
				String password = new String(pass);
				
				char[] confirmPass = ConfirmPasswordField.getPassword();
				String confirmPassword = new String(confirmPass);
				
				String id = usernameField.getText();
			
				Connection conn = null;
				try {
					if (password.equals(confirmPassword)) {

						Class.forName("com.mysql.cj.jdbc.Driver");
						String url = "jdbc:mysql://222.119.100.81:3382/mz";
						String user1 = "bong";
						String passwd = "mz1234";
						conn = DriverManager.getConnection(url, user1, passwd);
						
						
						//아이디 중복 확인
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						String sql = "select * from user_tbl where user_name = ?";
						

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						rs = pstmt.executeQuery();

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "이미 가입된 아이디입니다.", "아이디 확인", 1);
						}else{
																
						sql = "" + "insert into user_tbl (user_name, user_pwd)" + "values (?,?)";

						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, id);
						pstmt.setString(2, password);
						
						int rows = pstmt.executeUpdate();
						JOptionPane.showMessageDialog(null, "회원 가입이 완료되었습니다.", "회원가입", 1);
						new MZ_login();
						frame.dispose();
						System.out.println("저장된 행 수: " + rows);
					}																																										
					}else {
						JOptionPane.showMessageDialog(null, "비밀번호가 다릅니다. 다시 확인해주세요.", "비밀번호 확인", 1);						
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException e1) {
			
						e1.printStackTrace();
					}
				}
			}
		};

		signupButton = new JButton("회원가입");
		signupButton.setBackground(new Color(2, 2, 2));
		signupButton.setBackground(new Color(255, 255, 255));
		signupButton.addActionListener(signUp);
		panel3.add(signupButton);

	}
}
