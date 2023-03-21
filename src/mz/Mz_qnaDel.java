package mz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class Mz_qnaDel {

	public Mz_qnaDel() {
		del();
	}

	public void del() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);
			// SQL 쿼리문 작성
			String sql = "DELETE FROM qna_tbl WHERE qna_uid = ?";
			
			PreparedStatement pstmt = null;
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Mz_qnaBoard.num);
			
			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: " + rows);
			
			DefaultTableModel tableModel = (DefaultTableModel) Mz_qnaBoard.jTable.getModel();
			tableModel.setNumRows(0);

			String sql1 = "" + "SELECT * FROM qna_tbl ";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql1); // 쿼리 실행후 결과 값을 resultset에 담아 두기

			while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
				tableModel.addRow(new Object[] { rs.getInt("qna_uid"), rs.getString("qna_name"), rs.getString("qna_title"),
						rs.getString("qna_date"), rs.getInt("qna_hit"), rs.getString("qna_post") });
			}
		
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결이 실패했습니다.<br>");
			System.out.println("SQLException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
