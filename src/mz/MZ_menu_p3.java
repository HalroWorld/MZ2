package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class MZ_menu_p3 {

	private JFrame frame3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MZ_menu_p3();
			}
		});
	}

	public MZ_menu_p3() {
		menu_P3();
	}
	
	private void menu_P3() {
		frame3 = new JFrame();
		frame3.getContentPane().setLayout(new BorderLayout(0, 0));
		frame3.setBounds(100, 100, 1102, 1270);
		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.setVisible(true);
		frame3.setLocationRelativeTo(null);
		frame3.setIconImage(new ImageIcon("src/mz/mzImg/mzduck.png").getImage());
		frame3.setTitle("맛-ZIP");
		
		
		
		// 전체적인 묶음용 패널임 신경 ㄴㄴ
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setLayout(null);
		panel.setPreferredSize(new Dimension(1000, 600));
		

		// 상단 홈버튼
		JButton btn_Home = new JButton("home");
		btn_Home.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/home.png")));
		btn_Home.setBounds(989, 10, 67, 62);
		btn_Home.setBorderPainted(false);
		btn_Home.setBackground(new Color(255, 255, 255));
		panel.add(btn_Home);

		// 첫페이지로 이동
		btn_Home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
					new MZ_home_p1_test();
					frame3.setVisible(false);
				}
			});
	

		// 상단 뒤로가기 버튼
		JButton btn_back = new JButton("back");
		btn_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MZ_list_p2();
				frame3.setVisible(false);
			}
		});
		
		btn_back.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("../Img/arrow_back.png")));
		btn_back.setBounds(902, 10, 67, 62);
		btn_back.setBorderPainted(false);
		btn_back.setBackground(new Color(255, 255, 255));
		panel.add(btn_back);

		// 직전 페이지로 이동
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					new MZ_list_p2();
					frame3.setVisible(false);
				}
		});
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String title = "검색하고자 하는 제목";
      
      // SQL 쿼리문 작성
      String sql = "SELECT id FROM mytable WHERE title = ?";
      
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, title);
      
      rs = pstmt.executeQuery();
      int id;
      if (rs.next()) {
        id = rs.getInt("id");
        System.out.println("검색 결과: " + id);
      } else {
        System.out.println("검색 결과가 없습니다.");
      }
      
			sql = "select * from " + MZ_home_p1_test.code +"mz_tbl where mz_uid='" + id + "'";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				// 가게 이름 (리스트3 페이지 타이틀)
				JLabel store_Name = new JLabel);
				store_Name.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 54));
				store_Name.setBounds(29, 291, 500, 79);
				panel.add(store_Name);
				
				// 최상단 메인 사진
				JLabel main_img = new JLabel();
				main_img.setIcon(new ImageIcon(db.path[2]));
				main_img.setBackground(new Color(255, 255, 255));
				main_img.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 25));
				main_img.setBounds(1, 0, 1087, 300);
				panel.add(main_img);
				
				// 전화 버튼
				JButton btn_call = new JButton("");
				btn_call.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/call.png")));
				btn_call.setBounds(29, 375, 46, 46);
				btn_call.setBorderPainted(false);
				btn_call.setBackground(new Color(255, 255, 255));
				panel.add(btn_call);
				
				// 지도 버튼
				JButton btn_lo = new JButton("");
				btn_lo.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/location1.png")));
				btn_lo.setBounds(82, 375, 46, 46);
				btn_lo.setBorderPainted(false);
				btn_lo.setBackground(new Color(255, 255, 255));
				btn_lo.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Mz_board_map();
//				frame.setVisible(false);
					}
				});
				panel.add(btn_lo);
				
				// 좋아요 버튼
				JButton btn_like = new JButton("");
				btn_like.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/favorite1.png")));
//		btn_like.setPressedIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/favorite2.png")));
				btn_like.setBounds(136, 375, 46, 46);
				btn_like.setBorderPainted(false);
				btn_like.setBackground(new Color(255, 255, 255));
				
				btn_like.addActionListener(new ActionListener() {
					int count = 0;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						count++;
						if (count % 2 == 1) {
							btn_like.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/favorite2.png")));
						} else {
							btn_like.setIcon(new ImageIcon(MZ_menu_p3.class.getResource("/mz/mzImg/favorite1.png")));
						}
					}
				});
				panel.add(btn_like);
				
				// 리뷰 버튼
				JButton btn_review = new JButton("리뷰");
				btn_review.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
				btn_review.setBounds(29, 458, 1027, 59);
				btn_review.setBackground(new Color(255, 255, 255));
				btn_review.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						new Mz_board();
//			frame.setVisible(false);
					}
				});
				panel.add(btn_review);
				
				String[] stars = { "★", "★★", "★★★", "★★★★", "★★★★★" };
				
				JComboBox score = new JComboBox(stars);
				score.setForeground(new Color(255, 199, 7));
//		score.setModel(new DefaultComboBoxModel(new String[] {"★★★★★","★★★★","★★★","★★","★"}));
				score.setSelectedIndex(4);
				score.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
				score.setBounds(204, 380, 190, 41);
//		score.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				String selectedItem = score.getSelectedItem().toString();
//				int a = selectedItem.length();
//				dbUp.update(
//						"update k_mz_tbl set mz_star=(mz_star+" + a + "), mz_star_count=(mz_star_count +1) where mz_uid = 1;");
//			}
//		});
				panel.add(score);
			}
			
		
		}
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();

		} catch (SQLException e) {
			System.out.println("데이터베이스 연결이 실패했습니다.<br>");
			System.out.println("SQLException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		// 스크롤
		
		
		// 스크롤 안에 부속재료들 묶음용
		JScrollPane scrollPane = new JScrollPane();
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setPreferredSize(new Dimension(1000, 500));
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(null);

		for (int i = 0; i < 2; i++) {
			int b = 300 * i;
			// 메뉴 이름

			JButton btn_menu = new JButton(db2.menu[i]);

			btn_menu.setHorizontalAlignment(SwingConstants.LEFT);
			btn_menu.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 35));
			btn_menu.setBounds(33, 50 + b, 359, 63);
			btn_menu.setBorderPainted(false);
			btn_menu.setBackground(new Color(255, 255, 255));
			panel_1.add(btn_menu);

			// 메뉴 설명
			JLabel explanation = new JLabel(db2.text[i]);
			explanation.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
			explanation.setBounds(45, 130 + b, 322, 63);
			panel_1.add(explanation);

			// 음식사진
			JLabel sub_img = new JLabel();
			sub_img.setIcon(new ImageIcon(db.path[i]));
			sub_img.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 26));
			sub_img.setBounds(487, 50 + b, 562, 219);
			panel_1.add(sub_img);

			JLabel line = new JLabel(
					"-------------------------------------------------------------------------------------------------------------------------------------------");
			line.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			line.setBounds(0, 250 + b, 1200, 100);
			line.setForeground(new Color(255, 199, 7));
			panel_1.add(line);
		}

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane scrollPane = new JScrollPane(panel_1, v, h);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		frame3.getContentPane().add(panel, BorderLayout.NORTH);
		frame3.getContentPane().add(scrollPane, BorderLayout.CENTER);
	}
}
