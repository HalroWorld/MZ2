package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;


public class MZ_list_p2 {
	// 패널, 프레임 변수 생성
	private JFrame frame2;
	private JPanel title_G;
	private JLabel title;
	private JButton btn_home;
	private JScrollPane scrollPane;
	private JPanel list_G;
	public static String code2;

	MZ_list_p2() {
		list_p2();
	}

	public void list_p2() {

		// 프레임 호출(이미지 추가, 컬러 추가, 사이즈 및 위치 조정)
		frame2 = new JFrame();
		// 프레임 사이즈 지정(x, y,너비,높이)
		frame2.setBounds(100, 100, 1102, 999);
		// 배경색 흰색
		frame2.getContentPane().setBackground(new Color(255, 255, 255));
		// x버튼 닫기 기능
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 아이콘 이미지
		frame2.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());
		// 이름 지정
		frame2.setTitle("맛-ZIP");
		// 레이아웃을 보더레이아웃으로 지정하고 상하좌우 여백을 50씩 줍니다.
		frame2.getContentPane().setLayout(new BorderLayout(50, 50));
		// 프레임 사이즈 고정
		frame2.setResizable(false);
		// frame2 창 띄우자마자 센터로 옮김
		frame2.setLocationRelativeTo(null);
		// frame2 창 띄우기
		frame2.setVisible(true);

		// 패널 title_G 호출(사이즈 및 위치 조정, 프레임에 추가)
		title_G = new JPanel();
//   title_G.setPreferredSize(new Dimension(100, 200));
		title_G.setBackground(Color.WHITE);

		title_G.setLayout(new GridLayout(0, 3, 10, 10));

		// title 라벨 생성(K-푸드 위치 조정, 폰트 변경, 패널에 추가)
		title = new JLabel(MZ_main.code + "- 푸드");
		title.setHorizontalAlignment(SwingConstants.LEFT);
		title.setBackground(new Color(255, 255, 255));
		title.setForeground(Color.black);
		title.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 99));
		title_G.add(title);

		frame2.getContentPane().add(title_G, BorderLayout.NORTH);

		//
		JPanel noGrid1 = new JPanel();
		noGrid1.setBackground(new Color(255, 255, 255));
		title_G.add(noGrid1);

		// btn_home 버튼 생성(위치 조정, 이미지 추가, 폰트 변경, 패널에 추가)
		// 텍스트 변경 안되고 이미지 수정 필요
		btn_home = new JButton("home");
		btn_home.setHorizontalAlignment(SwingConstants.CENTER);
		btn_home.setIcon(new ImageIcon("./src/Img/home2.png"));
		btn_home.setBackground(new Color(255, 255, 255));
		btn_home.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MZ_home_p1();
				frame2.setVisible(false);
			}
		});
		btn_home.setBorderPainted(false);
		title_G.add(btn_home);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBackground(new Color(255, 255, 255));

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		scrollPane = new JScrollPane(v, h);

		scrollPane.setViewportView(contentPanel);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);
		frame2.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// db 호출
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);

			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = "select * from mz_tbl where mz_code = ?";
			String code = MZ_main.code;

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				list_G = new JPanel(new GridBagLayout());
				list_G.setBackground(new Color(255, 255, 255));

				GridBagConstraints c = new GridBagConstraints();

				// 첫번째 행
				c.gridx = 0;
				c.gridy = 0;
				c.gridwidth = 3;
				c.gridheight = 3;
				c.fill = GridBagConstraints.BOTH;
				c.anchor = GridBagConstraints.WEST;
				JLabel img1 = new JLabel();
				img1.setIcon(new ImageIcon(rs.getBytes("mz_img")));
				// 여백 위,왼,아,오
				img1.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 0));
				img1.setBackground(new Color(255, 255, 255));
				list_G.add(img1, c);

				c.gridx = 3;
				c.gridy = 0;
				c.gridwidth = 3;
				c.gridheight = 3;
				c.fill = GridBagConstraints.BOTH;
				JLabel img2 = new JLabel();
				img2.setIcon(new ImageIcon(rs.getBytes("mz_img2")));
				img2.setBackground(new Color(255, 255, 255));
				list_G.add(img2, c);

				// 두번째 행
				c.gridx = 0;
				c.gridy = 3;
				c.gridwidth = 2;
				c.gridheight = 1;
				c.weightx = 0.1;
				c.fill = GridBagConstraints.BOTH;

				final String[] title = { rs.getString("mz_title") };

				JButton btn_storeName = new JButton(title[0]);
				btn_storeName.setForeground(new Color(0, 0, 0));
				btn_storeName.setHorizontalAlignment(SwingConstants.LEFT);
				btn_storeName.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 40));

				btn_storeName.setBorderPainted(false);
				btn_storeName.setBackground(new Color(255, 255, 255));
				list_G.add(btn_storeName, c);
				btn_storeName.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						try {
							MZ_DB_Update dpUp = new MZ_DB_Update();
							dpUp.update("update mz_tbl set mz_hit=(mz_hit+1) where mz_title = '" + title[0] + "';");
							mz.MZ_main.code2 = title[0];
							MZ_menu_p3.main(null);

							frame2.setVisible(false);
						} catch (Exception e1) {

							e1.printStackTrace();
						}

					}
				});

				c.gridx = 2;
				c.gridy = 3;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;

				JLabel score = new JLabel("별점");
				score.setBackground(new Color(255, 255, 255));
				score.setForeground(new Color(39, 39, 39));
				score.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
				score.setBackground(new Color(255, 255, 255));

				list_G.add(score, c);

				c.gridx = 3;
				c.gridy = 3;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;

				JLabel star = new JLabel();

				int total = rs.getInt("mz_star");
				int count = rs.getInt("mz_star_count");
				int avg = 0;
				double avg2 = ((int) (total / (double) count * 100)) / (double) 100;
				String result;

				if (count == 0) {
					result = "";
				} else {
					avg = (int) total / count;
				}

				switch (avg) {
				case 5:
					result = "★★★★★ (" + avg2 + ")";
					break;
				case 4:
					result = "★★★★ (" + avg2 + ")";
					break;
				case 3:
					result = "★★★ (" + avg2 + ")";
					break;
				case 2:
					result = "★★ (" + avg2 + ")";
					break;
				case 1:
					result = "★ (" + avg2 + ")";
					break;
				default:
					result = "평점을 남겨주세요.";
					break;
				}
				System.out.print(result);
				star.setForeground(new Color(255, 199, 7));
				star.setFont(new Font("맑은 고딕", Font.PLAIN, 25));
				star.setText(result);
				list_G.add(star, c);

				c.gridx = 4;
				c.gridy = 3;
				c.gridwidth = 2;
				c.gridheight = 1;
				c.weightx = 0.1;

				JButton btn_More = new JButton("더보기");
				btn_More.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
				btn_More.setBorderPainted(false);
				btn_More.setBackground(new Color(255, 255, 255));
				btn_More.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							MZ_DB_Update dpUp = new MZ_DB_Update();
							dpUp.update("update mz_tbl set mz_hit=(mz_hit+1) where mz_title = '" + title[0] + "';");
							mz.MZ_main.code2 = title[0];
							MZ_menu_p3.main(null);
							frame2.setVisible(false);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});

				list_G.add(btn_More, c);

				c.gridx = 0;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				JLabel open_H = new JLabel("영업시간");
				open_H.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
				open_H.setForeground(Color.black);
				open_H.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 0));
				open_H.setBackground(new Color(255, 255, 255));
				list_G.add(open_H, c);

				c.gridx = 1;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				c.fill = GridBagConstraints.BOTH;
				JLabel Hour = new JLabel(rs.getString("mz_hours"));
				Hour.setForeground(new Color(39, 39, 39));
				Hour.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
				list_G.add(Hour, c);

				c.gridx = 2;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				JLabel reveiw = new JLabel("리뷰");
				reveiw.setBackground(new Color(255, 255, 255));
				reveiw.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));

				list_G.add(reveiw, c);

				c.gridx = 3;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				JLabel review_score = new JLabel(Integer.toString(rs.getInt("mz_review")));
				review_score.setForeground(new Color(255, 199, 7));
				review_score.setBackground(new Color(255, 255, 255));
				review_score.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
				list_G.add(review_score, c);

				c.gridx = 4;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				JLabel veiws = new JLabel("조회수");
				veiws.setBackground(new Color(255, 255, 255));
				veiws.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
				list_G.add(veiws, c);

				c.gridx = 5;
				c.gridy = 4;
				c.gridwidth = 1;
				c.gridheight = 1;
				c.weightx = 0.1;
				JLabel views_score = new JLabel(Integer.toString(rs.getInt("mz_hit")));
				views_score.setForeground(new Color(39, 39, 39));
				views_score.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
				veiws.setBackground(new Color(255, 255, 255));
				list_G.add(views_score, c);

				c.gridx = 0;
				c.gridy = 5;
				c.gridwidth = 1;
				c.gridheight = 1;
				JLabel lblNewLabel = new JLabel("주소");
				lblNewLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 0));
				lblNewLabel.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
				lblNewLabel.setBackground(new Color(255, 255, 255));

				list_G.add(lblNewLabel, c);

				c.gridx = 1;
				c.gridy = 5;
				c.gridwidth = 5;
				c.gridheight = 1;
				JLabel addr = new JLabel(rs.getString("mz_addr"));
				addr.setBackground(new Color(255, 255, 255));
				addr.setFont(new Font("맑은 고딕", Font.BOLD, 20));

				list_G.add(addr, c);

				contentPanel.add(list_G);
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

	}
}