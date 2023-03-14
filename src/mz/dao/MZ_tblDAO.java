package mz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mz.MZ_tbl;

public class MZ_tblDAO {
    private Connection conn;
    
    public MZ_tblDAO(Connection conn) {
        this.conn = conn;
    }
    
    // MZ_tbl 테이블에서 모든 레코드를 조회하여 List<MZ_tbl> 형태로 반환하는 메서드
    public List<MZ_tbl> getAllMZ_tbl() throws SQLException {
        List<MZ_tbl> mzList = new ArrayList<>();
        String sql = "SELECT * FROM MZ_tbl";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MZ_tbl mz = new MZ_tbl();
                mz.setMzUid(rs.getInt("mzUid"));
                mz.setUserName(rs.getString("userName"));
                mz.setMzCode(rs.getString("MzCode"));
                mz.setMzTitle(rs.getString("MzTitle"));
                mz.setMzStar(rs.getDouble("MzStar"));
                mz.setMzHours(rs.getString("MzHours"));
                mz.setMzHit(rs.getInt("MzHit"));
                mz.setMzReview(rs.getInt("MzReview"));
                mz.setMzAddr(rs.getString("MzAddr"));
                mz.setMzImg(rs.getBlob("mzImg"));
                mz.setMzImg2(rs.getBlob("mzImg2"));
                mz.setMzImg3(rs.getBlob("mzImg3"));
                mz.setMzStarCount(rs.getInt("mzStarCount"));
                mzList.add(mz);
            }
        }
        return mzList;
    }
    
    // MZ_tbl 테이블에 새로운 레코드를 추가하는 메서드
    public void addMZ_tbl(MZ_tbl mz) throws SQLException {
        String sql = "INSERT INTO MZ_tbl (userName, MzCode, MzTitle, MzStar, MzHours, MzHit, MzReview, MzAddr, mzImg, mzImg2, mzImg3, mzStarCount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, mz.getUserName());
            pstmt.setString(2, mz.getMzCode());
            pstmt.setString(3, mz.getMzTitle());
            pstmt.setDouble(4, mz.getMzStar());
            pstmt.setString(5, mz.getMzHours());
            pstmt.setInt(6, mz.getMzHit());
            pstmt.setInt(7, mz.getMzReview());
            pstmt.setString(8, mz.getMzAddr());
            pstmt.setBlob(9, mz.getMzImg());
            pstmt.setBlob(10, mz.getMzImg2());
            pstmt.setBlob(11, mz.getMzImg3());
            pstmt.setInt(12, mz.getMzStarCount());
            pstmt.executeUpdate();
        }
    }
    
 // MZ_tbl 테이블에서 mzUid 값으로 레코드를 조회하는 메서드
    public MZ_tbl getMZ_tblById(int mzUid) throws SQLException {
        String sql = "SELECT * FROM MZ_tbl WHERE mzUid = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, mzUid);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    MZ_tbl mz = new MZ_tbl();
                    mz.setMzUid(rs.getInt("mzUid"));
                    mz.setUserName(rs.getString("userName"));
                    mz.setMzCode(rs.getString("MzCode"));
                    mz.setMzTitle(rs.getString("MzTitle"));
                    mz.setMzStar(rs.getDouble("MzStar"));
                    mz.setMzHours(rs.getString("MzHours"));
                    mz.setMzHit(rs.getInt("MzHit"));
                    mz.setMzReview(rs.getInt("MzReview"));
                    mz.setMzAddr(rs.getString("MzAddr"));
                    mz.setMzImg(rs.getBlob("mzImg"));
                    mz.setMzImg2(rs.getBlob("mzImg2"));
                    mz.setMzImg3(rs.getBlob("mzImg3"));
                    mz.setMzStarCount(rs.getInt("mzStarCount"));
                    return mz;
                }
            }
        }
        return null;
    }
}