package mz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MZ_DB_Update {
	public void update(String sql) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";

			conn = DriverManager.getConnection(url, user1, passwd);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			int rows = pstmt.executeUpdate();
			System.out.println("수정된 행 수: " + rows);
			pstmt.close();
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
}