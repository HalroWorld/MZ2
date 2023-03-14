package mz.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MZ_dbConn {
	public void conn() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";

			conn = DriverManager.getConnection(url, user1, passwd);
		} catch (SQLException e) {
			System.out.println("데이터베이스 연결이 실패했습니다.<br>");
			System.out.println("SQLException: " + e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
