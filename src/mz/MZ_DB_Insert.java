package mz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MZ_DB_Insert {
	public void insert(String name, String favorite) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";

			conn = DriverManager.getConnection(url, user1, passwd);
			
			String sql = "INSERT INTO user_favorite_tbl(user_name, favorite) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, favorite);
			
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
	
	public void delete(String favorite, String name) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);
			// SQL 쿼리문 작성
			String sql = "DELETE FROM user_favorite_tbl WHERE favorite = ? AND user_name = ?;";
			
			PreparedStatement pstmt = null;
			
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite);
			pstmt.setString(2, name);
			
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
	
	public int selete(String favorite, String name) {

		Connection conn = null;
		int num = 0;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";
			conn = DriverManager.getConnection(url, user1, passwd);
			// SQL 쿼리문 작성
			String sql ="SELECT * FROM user_favorite_tbl where favorite = ? AND user_name = ?";
			
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite);
			pstmt.setString(2, name);
			
					
			rs =	pstmt.executeQuery();
			while (rs.next()) {
				num = rs.getInt("good");
			}
			
	
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
		return num;
	}
	
	public void update(String favorite, String name) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";

			conn = DriverManager.getConnection(url, user1, passwd);
			String sql = "update user_favorite_tbl set good= (good + 1) where favorite = ? AND user_name = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, favorite);
			pstmt.setString(2, name);
			
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
	public void updateM(String favorite) {

		Connection conn = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			String url = "jdbc:mysql://222.119.100.81:3382/mz";
			String user1 = "bong";
			String passwd = "mz1234";

			conn = DriverManager.getConnection(url, user1, passwd);
			String sql = "update user_favorite_tbl set good= (good - 1) where favorite = ?";
			
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
