package mz;

import java.sql.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Mz_qnaBoard extends JFrame {
	public static JTable jTable;
	private JPanel pSouth;
	private JButton btnInsert, btnDelete, btnOpen;
	private Connection conn;
	static Qna_tbl boardList = new Qna_tbl();
	static int num = 0;

	String a, b, c;
	private JButton btnopen;
//   private JButton btnNewButton;

	// 메인 윈도우 설정
	public Mz_qnaBoard() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(getJTable());
		scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setAlignmentY(Component.TOP_ALIGNMENT);
		scrollPane.setBackground(new Color(255, 255, 255));
		scrollPane.setForeground(new Color(255, 242, 197));
		scrollPane.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.getContentPane().add(getPSouth(), BorderLayout.SOUTH);
		this.setBounds(770, 250, 1102, 1270);
		this.setSize(1000, 800);
		this.setTitle("맛-ZIP");
		this.setIconImage(new ImageIcon("./src/Img/mzduck.png").getImage());

		 JButton btn_home = new JButton();
		  btn_home.setIcon(new ImageIcon("./src/Img/home2.png"));
		  btn_home.setBorderPainted(false);
		  btn_home.setBackground(new Color (255,255,255));
		  btn_home.addActionListener(new ActionListener() {
		     @Override
		     public void actionPerformed(ActionEvent e) {
		        dispose();
		        
		     }	
		  });
		  pSouth.add(btn_home);
	}

	// JTable 생성
	public JTable getJTable() {
		
		  
		if (jTable == null) {
			jTable = new JTable();
			final DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();
			tableModel.addColumn("번호");
			tableModel.addColumn("제목");
			tableModel.addColumn("글쓴이");
			tableModel.addColumn("날짜");
			tableModel.addColumn("조회수");

			jTable.getTableHeader().setReorderingAllowed(false); // 컬럼 헤드 고정
			jTable.getTableHeader().setBackground(new Color(255, 242, 197));
			jTable.getTableHeader().setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 30));
			jTable.setRowHeight(30);

			Connection conn = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url = "jdbc:mysql://222.119.100.81:3382/mz";
				String user1 = "bong";
				String passwd = "mz1234";

				conn = DriverManager.getConnection(url, user1, passwd);

				String sql = "" + "SELECT qna_uid, qna_title, qna_name, qna_date, qna_hit, qna_post " + "FROM qna_tbl ";
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(sql); // 쿼리 실행후 결과 값을 resultset에 담아 두기

				while (rs.next()) {
					// 각각 값을 가져와서 테이블값들을 추가
					tableModel.addRow(new Object[] { rs.getInt("qna_uid"), rs.getString("qna_title"), rs.getString("qna_name"),
							rs.getString("qna_date"), rs.getInt("qna_hit"), rs.getString("qna_post") });

				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
					}
				}
			}
		}
		return jTable;
	}

	// 사용자 입력 JPanel 생성
	public JPanel getPSouth() {
		if (pSouth == null) {
			pSouth = new JPanel();
			pSouth.setBackground(new Color(255, 255, 255));

			pSouth.setLayout(new GridLayout(3, 1));
			JPanel pButton = new JPanel();
			pButton.setBackground(new Color(255, 255, 255));
			pButton.add(getBtnInsert());
			pButton.add(getBtnOpen());
			pButton.add(getBtnDelete());
			pSouth.add(pButton);
//         pButton.add(getBtnNewButton());
		}
		return pSouth;
	}

	// 게시물 작성 다이얼로그

	public JButton getBtnInsert() {
		if (btnInsert == null) {
			btnInsert = new JButton();
			btnInsert.setBackground(new Color(255, 255, 255));
			btnInsert.setForeground(new Color(0, 0, 0));
			btnInsert.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
			btnInsert.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnInsert.setBackground(new Color(255, 199, 7));
					btnInsert.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnInsert.setBackground(Color.WHITE);
					btnInsert.setForeground(Color.BLACK);
				}
			});
			btnInsert.setText("추가");
			btnInsert.setBorderPainted(false);
			btnInsert.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					// 다이얼로그 띄우기
					new Mz_qnaWrite();
				}
			});
		}
		return btnInsert;
	}

	// 게시물 열기 버튼
	private JButton getBtnOpen() {
		if (btnopen == null) {
			btnopen = new JButton("게시물 열기");
			btnopen.setForeground(new Color(0, 0, 0));
			btnopen.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
			btnopen.setBackground(new Color(255, 255, 255));
			btnopen.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnopen.setBackground(new Color(255, 199, 7));
					btnopen.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnopen.setBackground(Color.WHITE);
					btnopen.setForeground(Color.BLACK);
				}
			});
			btnopen.setBorderPainted(false);
			btnopen.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MZ_DB_Update db = new MZ_DB_Update();
					// 게시물 다이얼로그 띄우기
					num = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
					db.update("update qna_tbl set qna_hit=(qna_hit + 1) where qna_uid = " + num);
					
					new Mz_qnaRead();
				}
			});
		}
		return btnopen;
	}

	// 삭제 버튼
	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton();
			btnDelete.setForeground(new Color(0, 0, 0));
			btnDelete.setBackground(new Color(255, 255, 255));
			btnDelete.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 20));
			btnDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					btnDelete.setBackground(new Color(255, 199, 7));
					btnDelete.setForeground(Color.WHITE);
				}

				@Override
				public void mouseExited(MouseEvent e) {
					btnDelete.setBackground(Color.WHITE);
					btnDelete.setForeground(Color.BLACK);
				}
			});
			btnDelete.setBorderPainted(false);
			btnDelete.setText("삭제");
			btnDelete.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					int rowIndex = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
					if (rowIndex == -1) // 선택안했을 때
					{
						JOptionPane.showMessageDialog(null, "게시물을 선택하세요", "경고", JOptionPane.WARNING_MESSAGE);
						// return;
					} else {

						if (rowIndex != -1) {
							num = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
							new Mz_qnaDel();
						}
					}
				}
			});
		}
		return btnDelete;
	}
	
 
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Mz_qnaBoard jFrame = new Mz_qnaBoard();
				jFrame.setVisible(true);
			}
		});
	}

}