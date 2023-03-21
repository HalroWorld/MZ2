package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.*;

public class Mz_qnaRead extends JDialog {

	private final JPanel contentPanel = new JPanel();
	JTextField txtTitle;
	JTextField txtUser;

	public Mz_qnaRead() {
		db_qnaSelect();
	}

	public void db_qnaSelect() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("게시물 보기");
		this.setBounds(600, 500, 567, 405);
		this.setLocationRelativeTo(null);
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);
			String sql = "" + "SELECT * FROM qna_tbl WHERE qna_uid = ?";

			PreparedStatement pstmt = null;
			ResultSet rs = null;

			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Mz_qnaBoard.num);
			
			rs = pstmt.executeQuery();
			
			
			if (rs.next()) {
				contentPanel.setBackground(new Color(255, 242, 197));
				getContentPane().setLayout(new BorderLayout());
				contentPanel.setLayout(new FlowLayout());
				contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
				getContentPane().add(contentPanel, BorderLayout.CENTER);
				contentPanel.setLayout(null);

				txtTitle = new JTextField();
				txtTitle.setBounds(137, 36, 341, 21);
				txtTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				txtTitle.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
				txtTitle.setText((rs.getString("qna_title")));
				contentPanel.add(txtTitle);
				txtTitle.setColumns(10);

				txtUser = new JTextField();
				txtUser.setBounds(137, 95, 341, 21);
				txtUser.setText(rs.getString("qna_name"));
				txtUser.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				txtUser.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
				contentPanel.add(txtUser);
				txtUser.setColumns(10);

				JTextArea txtContent = new JTextArea();
				txtContent.setBounds(137, 154, 341, 153);
				txtContent.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
				txtContent.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
				txtContent.setText(rs.getString("qna_post"));
				contentPanel.add(txtContent);

				JLabel lblNewLabel = new JLabel("제목");
				lblNewLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
				lblNewLabel.setBounds(51, 39, 52, 15);
				contentPanel.add(lblNewLabel);

				JLabel lblNewLabel_1 = new JLabel("글쓴이");
				lblNewLabel_1.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
				lblNewLabel_1.setBounds(51, 98, 52, 15);
				contentPanel.add(lblNewLabel_1);

				JLabel lblNewLabel_2 = new JLabel("내용");
				lblNewLabel_2.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
				lblNewLabel_2.setBounds(51, 218, 52, 15);
				contentPanel.add(lblNewLabel_2);

				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);

				JButton cancelButton = new JButton("닫기");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("닫기");
				buttonPane.add(cancelButton);

			}
			rs.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("연결 끊기");
				} catch (SQLException e) {
				}
			}
		}
	}
}
