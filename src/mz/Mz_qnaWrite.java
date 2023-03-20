package mz;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


public class Mz_qnaWrite extends JDialog{

	final JPanel contentPanel = new JPanel();
	JTextField txtTitle;
	JTextField txtUser;


	/**
	 * Launch the application.
	 */
	
	
	
	public static void main(String[] args) {
		
						
		
		try {
			Mz_qnaWrite dialog = new Mz_qnaWrite(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Mz_qnaWrite(JFrame owner) {
		this.setTitle("게시물 작성");
		setBounds(950, 300, 567, 405);
		contentPanel.setBackground(new Color(255, 242, 197));
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(137, 36, 341, 21);
		txtTitle.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		txtTitle.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
		contentPanel.add(txtTitle);
		txtTitle.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setBounds(137, 95, 341, 21);
		txtUser.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		txtUser.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
		contentPanel.add(txtUser);
		txtUser.setColumns(10);
		
		JTextArea txtContent = new JTextArea();
		txtContent.setBounds(137, 154, 341, 153);
		txtContent.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		txtContent.setBorder(new LineBorder(new Color(255, 242, 197), 2, true));
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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			
			{
				JButton okButton = new JButton("저장");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						 okButton.setBackground(new Color(255,199,7));
						 okButton.setForeground(Color.WHITE);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						 okButton.setBackground(new Color(255,242,197));
						 okButton.setForeground(Color.BLACK);
					}
				});
				okButton.setBorderPainted(false);
				okButton.setBackground(new Color(255, 242, 197));
				okButton.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {												
						
					
						String ipTitle = txtTitle.getText();
						String ipUser = txtUser.getText();
						String ipContent= txtContent.getText();
												
						Mz_qnaData.InputTitle = txtTitle.getText();
						Mz_qnaData.InputUser = txtUser.getText();
						Mz_qnaData.InputContent = txtContent.getText();
						
												
						txtTitle.setText("");
						txtUser.setText("");
						txtContent.setText("");
						
						System.out.println(Mz_qnaData.InputTitle);
						System.out.println(Mz_qnaData.InputUser);
						System.out.println(Mz_qnaData.InputContent);
						
						Connection conn = null;
						
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							
							conn = DriverManager.getConnection(
								"jdbc:mysql://222.119.100.81:3382/mz",
								"bong",
								"mz1234"
							);
							
							String sql = "" +
									"INSERT INTO qna_tbl(qna_name, qna_title,qna_date, qna_post )"
									+ " values(?, ?, now(),? )";
							PreparedStatement pstmt = conn.prepareStatement(sql);
							pstmt.setString(1, Mz_qnaData.InputUser);
							pstmt.setString(2, Mz_qnaData.InputTitle);			
							pstmt.setString(3, Mz_qnaData.InputContent);
							
							
							pstmt.executeUpdate();
							
							pstmt.close();
							
							DefaultTableModel tableModel = (DefaultTableModel)Mz_qnaBoard.jTable.getModel();
							tableModel.setNumRows(0);
							
							String sql1 = "" +
									"SELECT qna_uid, qna_name, qna_title, qna_date, qna_hit, qna_post " +
									"FROM qna_tbl ";
							Statement st = conn.createStatement();
							ResultSet rs = st.executeQuery(sql1);   // 쿼리 실행후 결과 값을 resultset에 담아 두기
							
							while(rs.next()){            // 각각 값을 가져와서 테이블값들을 추가
								tableModel.addRow(
										new Object[]{rs.getInt("qna_uid"),rs.getString("qna_name"),
												rs.getString("qna_title"),rs.getString("qna_date"),
												rs.getInt("qna_hit") , rs.getString("qna_post")
										});
							}
								
//						model=new DefaultTableModel(Mz_board.getJTable().tableModel ,columnNames);    
//						Mz_board.jTable.setModel(model);
//						Mz_board.jTable.getColumn("렌더링셀").setCellRenderer(new ButtonRenderer());
//						Mz_board.jTable.getColumn("렌더링셀").setCellEditor(new ButtonEditor(new JCheckBox()));	

							
							
							dispose();
							
						} catch (Exception e1) {
							e1.printStackTrace();			
						} finally {
							if(conn != null) {

								try {
									conn.close();
								} catch (SQLException e1) {}
							}
						}
						
						
					} 
				});
				okButton.setActionCommand("저장");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				
			}
			
			
			
			
			{
				JButton cancelButton = new JButton("취소");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						cancelButton.setBackground(new Color(255,199,7));
						cancelButton.setForeground(Color.WHITE);
					}
					@Override
					public void mouseExited(MouseEvent e) {
						cancelButton.setBackground(new Color(255,242,197));
						cancelButton.setForeground(Color.BLACK);
					}
				});
				cancelButton.setBorderPainted(false);
				cancelButton.setBackground(new Color(255, 242, 197));
				cancelButton.setFont(new Font("배달의민족 한나체 Pro", Font.PLAIN, 16));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("취소");
				buttonPane.add(cancelButton);
			}
		}
		
	}
}

