package mz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
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
   private JPanel list_G;
   private JPanel contentPanel;

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
      frame3.setBounds(100, 100, 1102, 999);
      frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame3.setVisible(true);
      frame3.setLocationRelativeTo(null);
      frame3.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());
      frame3.setTitle("맛-ZIP");


      // 전체적인 묶음용 패널임 신경 ㄴㄴ
      JPanel panel = new JPanel();
      panel.setBackground(new Color(255, 255, 255));
      panel.setLayout(null);
      panel.setPreferredSize(new Dimension(1000, 550));

      // 상단 홈버튼
      JButton btn_Home = new JButton("home");
      btn_Home.setIcon(new ImageIcon("./src/Img/home2.png"));
      btn_Home.setBounds(989, 10, 67, 62);
      btn_Home.setBorderPainted(false);
      btn_Home.setBackground(new Color(255, 255, 255));
      btn_Home.setForeground(new Color(255, 255, 255, 0));
      panel.add(btn_Home);
      // 첫페이지로 이동
      btn_Home.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

            new MZ_home_p1();
            frame3.setVisible(false);
         }
      });

      // 상단 뒤로가기 버튼
      JButton btn_back = new JButton("back");
      btn_back.setForeground(new Color(255, 255, 255, 0));

      // 직전 페이지로 이동
      btn_back.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
        	 if(MZ_main.code3.equals(null)) {
            new MZ_list_p2();
            frame3.setVisible(false);
            }else {
            	 new MZ_search();
               frame3.setVisible(false);
            }
         }
      });

      btn_back.setIcon(new ImageIcon(("./src/Img/arrow_back.png")));
      btn_back.setBounds(902, 10, 67, 62);
      btn_back.setBorderPainted(false);
      btn_back.setBackground(new Color(255, 255, 255));
      panel.add(btn_back);

      Connection conn = null;

      try {
         Class.forName("com.mysql.cj.jdbc.Driver");

         String url = "jdbc:mysql://222.119.100.81:3382/mz";
         String user1 = "bong";
         String passwd = "mz1234";
         conn = DriverManager.getConnection(url, user1, passwd);

         PreparedStatement pstmt = null;
         ResultSet rs = null;
         String title = MZ_main.code2;

         // SQL 쿼리문 작성
         String sql = "SELECT * FROM mz_tbl where mz_title = ?";

         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, title);
         System.out.println(title);

         rs = pstmt.executeQuery();

         if (rs.next()) {
            final String[] mz_url = { rs.getString("mz_url") };

            // 가게 이름 (리스트3 페이지 타이틀)
            JLabel store_Name = new JLabel(rs.getString("mz_title"));
            store_Name.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 54));
            store_Name.setBounds(29, 291, 500, 79);
            panel.add(store_Name);

            // 최상단 메인 사진
            JLabel main_img = new JLabel();
            ImageIcon icon = new ImageIcon(rs.getBytes("mz_img3"));
            if (icon != null)
               main_img.setIcon(icon);
            main_img.setBackground(new Color(255, 255, 255));
            main_img.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 25));
            main_img.setBounds(1, 0, 1087, 300);
            panel.add(main_img);

            // 전화 버튼
            JButton btn_call = new JButton("");
            btn_call.setIcon(new ImageIcon(("./src/Img/call.png")));
            btn_call.setBounds(29, 375, 46, 46);
            btn_call.setBorderPainted(false);
            btn_call.setBackground(new Color(255, 255, 255));
            btn_call.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  try {
                     // Google의 URL을 URI 객체로 생성
                     if (mz_url[0] != null) {
                        URI uri = new URI(mz_url[0]);
                        // Desktop 클래스를 사용해서 기본 브라우저에서 해당 URL을 열기
                        Desktop.getDesktop().browse(uri);
                     }
                  } catch (Exception ex) {
                     ex.printStackTrace();
                  }
               }
            });
            panel.add(btn_call);

            // 지도 버튼
            JButton btn_lo = new JButton("");
            btn_lo.setIcon(new ImageIcon(("./src/Img/location1.png")));
            btn_lo.setBounds(82, 375, 46, 46);
            btn_lo.setBorderPainted(false);
            btn_lo.setBackground(new Color(255, 255, 255));
            btn_lo.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                  try {
                     // Google의 URL을 URI 객체로 생성
                     if (mz_url[0] != null) {
                        URI uri = new URI(mz_url[0]);
                        // Desktop 클래스를 사용해서 기본 브라우저에서 해당 URL을 열기
                        Desktop.getDesktop().browse(uri);
                     }
                  } catch (Exception ex) {
                     ex.printStackTrace();
                  }
               }
            });
            panel.add(btn_lo);

            // 좋아요 버튼
            JButton btn_like = new JButton("");
            btn_like.setIcon(new ImageIcon("./src/Img/favorite1.png"));
            btn_like.setBounds(136, 375, 46, 46);
            btn_like.setBorderPainted(false);
            btn_like.setBackground(new Color(255, 255, 255));

            btn_like.addActionListener(new ActionListener() {
               int count = 0;

               @Override
               public void actionPerformed(ActionEvent e) {
                  count++;
                  if (count % 2 == 1) {
                     btn_like.setIcon(new ImageIcon("./src/Img/favorite2.png"));
                  } else {
                     btn_like.setIcon(new ImageIcon("./src/Img/favorite1.png"));
                  }
               }
            });
            panel.add(btn_like);

            // 리뷰 버튼
            JButton btn_review = new JButton("리뷰");
            btn_review.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
            btn_review.setBounds(29, 458, 1027, 59);
            btn_review.setBackground(new Color(255, 255, 255));
//            btn_review.addActionListener(new ActionListener() {
//               @Override
//               public void actionPerformed(ActionEvent e) {
//                  new Mz_board();
//                  frame.setVisible(false);
//               }
//            });
            panel.add(btn_review);

            // 별
            String[] stars = { "★", "★★", "★★★", "★★★★", "★★★★★" };

            JComboBox score = new JComboBox(stars);
            score.setForeground(new Color(255, 199, 7));
            score.setModel(new DefaultComboBoxModel(new String[] { "★★★★★", "★★★★", "★★★", "★★", "★" }));
            score.setSelectedIndex(4);
            score.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
            score.setBounds(204, 380, 190, 41);
            score.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                  String selectedItem = score.getSelectedItem().toString();
                  int a = selectedItem.length();
                  MZ_DB_Update dpUp = new MZ_DB_Update();
                  dpUp.update("update mz_tbl set mz_star=(mz_star+" + a
                        + "), mz_star_count=(mz_star_count +1) where mz_title = '" + MZ_main.code2 + "'");
               }
            });
            panel.add(score);
         }

         frame3.getContentPane().add(panel, BorderLayout.NORTH);

         // 스크롤 안에 부속재료들 묶음용
         JPanel contentPanel = new JPanel();
         contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
         contentPanel.setBackground(new Color(255, 255, 255));
         

         int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
         int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
         JScrollPane scrollPane = new JScrollPane(v, h);
         
         scrollPane.setViewportView(contentPanel);
         scrollPane.getVerticalScrollBar().setUnitIncrement(16);
         frame3.getContentPane().add(scrollPane, BorderLayout.CENTER);

         PreparedStatement pstmt2 = null;
         ResultSet rs2 = null;
         sql = "SELECT * FROM " + MZ_main.code + "_mz_tbl where mz_title = ?";
         pstmt2 = conn.prepareStatement(sql);
         pstmt2.setString(1, title);
         rs2 = pstmt2.executeQuery();
         

         while (rs2.next()) { 

            list_G = new JPanel(new GridBagLayout());
            list_G.setBackground(new Color(255, 255, 255));
            GridBagConstraints c = new GridBagConstraints();
            
            
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.NORTH;
            
            JPanel j1 = new JPanel(new GridLayout(0, 1));
            j1.setBackground(new Color(255, 255, 255));
            j1.setPreferredSize(new Dimension(400, 250));
           
            
            JButton btn_menu = new JButton(rs2.getString("mz_menu"));
            btn_menu.setHorizontalAlignment(SwingConstants.LEFT);
            btn_menu.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 35));
            btn_menu.setBorderPainted(false);
            btn_menu.setBackground(new Color(255, 255, 255));
            btn_menu.setBackground(Color.white);
            j1.add(btn_menu);
            
            JLabel explanation = new JLabel(rs2.getString("mz_img_text"));
            explanation.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
            explanation.setBackground(Color.white);
            j1.add(explanation);
            
            list_G.add(j1);
            
            
            // 메뉴 이름
            // 음식사진
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.gridheight = 1;
            c.weightx = 0.5;
            c.anchor = GridBagConstraints.EAST;
            JLabel sub_img = new JLabel();
            sub_img.setIcon(new ImageIcon(rs2.getBytes("mz_img")));
            sub_img.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 0));
            sub_img.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 26));
            sub_img.setBackground(Color.blue);
            list_G.add(sub_img);
            
            contentPanel.add(list_G);

         }
         
         
         if (rs2 != null)
            rs2.close();
         if (pstmt2 != null)
            pstmt2.close();

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