package WindowBuilder.promotion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class RecentPopularPromotionDAO {
  private Connection conn = null;
  private PreparedStatement pstmt = null;
  private String sql = null;
  private ResultSet rs = null;
  private RecentPopularPromotionVO vo = null;
  
  public RecentPopularPromotionDAO() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/javagreen03";
      String user = "root";
      String password = "1234";
      conn = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 검색 실패 : " + e.getMessage());
    } catch (SQLException e) {
      System.out.println("DB 연결 실패 : " + e.getMessage());
    }
  }
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Vector searchList(RecentPopularPromotionVO searchVO) {
    Vector vData = new Vector();//제너릭과 VO는 테이블과 list에서 에러난다고 함(선생님)
    Vector record = null;
    StringBuffer sql = new StringBuffer("");
    try {
      sql.append("SELECT PART_CODE, SUBJECT, ORIGIN, CREATE_DATE, CONTENT ")
        .append(" FROM RECENT_POPULAR_PROMOTION")
        .append(" WHERE");
      if (0 < searchVO.getPartCode()) sql.append(" PART_CODE = ? AND");
      if (null != searchVO.getSubject()) sql.append(" SUBJECT = ? AND");
      if (null != searchVO.getOrigin()) sql.append(" ORIGIN = ? AND");
      if (null != searchVO.getCreateDateFrom() && null != searchVO.getCreateDateTo()) 
        sql.append(" (CREATE_DATE >= ? AND CREATE_DATE <= ?)");
      if ((sql.lastIndexOf("AND")+2) == (sql.length()-1))
        sql.replace(sql.lastIndexOf("AND"), sql.length(), "");
      if ((sql.lastIndexOf("WHERE")+4) == (sql.length()-1))
        sql.replace(sql.lastIndexOf("WHERE"), sql.length(), "");
      sql.append(" ORDER BY CREATE_DATE DESC");
      pstmt = conn.prepareStatement(sql.toString());
      int idx = 0;
      if (0 < searchVO.getPartCode()) pstmt.setInt(++idx, searchVO.getPartCode());
      if (null != searchVO.getSubject()) pstmt.setString(++idx, searchVO.getSubject());
      if (null != searchVO.getOrigin()) pstmt.setString(++idx, searchVO.getOrigin());
      if (null != searchVO.getCreateDateFrom() && null != searchVO.getCreateDateTo()) { 
        pstmt.setString(++idx, searchVO.getSubject());
        pstmt.setString(++idx, searchVO.getSubject());
      }
      rs = pstmt.executeQuery();
      while(rs.next()) {
        record = new Vector();
        record.add(rs.getInt("PART_CODE"));
        record.add(rs.getString("SUBJECT"));
        record.add(rs.getString("ORIGIN"));
        record.add(rs.getString("CREATE_DATE"));
        if (250 > rs.getString("CONTENT").length()) 
          record.add(rs.getString("CONTENT"));
        else
          record.add(rs.getString("CONTENT").substring(0, 250-1));
        vData.add(record);
      }
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      rsClose();
      pstmtClose();
    }
    return vData;
  }
  public RecentPopularPromotionVO search(RecentPopularPromotionVO searchVO) {
    try {
      sql = "SELECT CREATE_DATE, PART_CODE, SUBJECT, ORIGIN, CONTENT, PICTURE"
          + " FROM RECENT_POPULAR_PROMOTION"
          + " WHERE CREATE_DATE = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, searchVO.getCreateDate());
      rs = pstmt.executeQuery();
      while(rs.next()) {
        vo = new RecentPopularPromotionVO();
        vo.setCreateDate(rs.getString("CREATE_DATE"));
        vo.setPartCode(rs.getInt("PART_CODE"));
        vo.setSubject(rs.getString("SUBJECT"));
        vo.setOrigin(rs.getString("ORIGIN"));
        vo.setContent(rs.getString("CONTENT"));
        vo.setPicture(rs.getBytes("PICTURE"));
      }
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      rsClose();
      pstmtClose();
    }
    return vo;
  }
  public int insert(RecentPopularPromotionVO vo) throws SQLException {
    int result = 0;
//    try {
      sql = "INSERT INTO RECENT_POPULAR_PROMOTION"
          + " VALUES (default, ?, ?, ?, ?, ?, null)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, vo.getPartCode());
      pstmt.setString(2, vo.getSubject());
      pstmt.setString(3, vo.getOrigin());
      pstmt.setString(4, vo.getContent());
      pstmt.setBytes(5, vo.getPicture());
      //pstmt.setBytes(6, vo.getPictureOrg());
      result = pstmt.executeUpdate();
      if (0 < result) System.out.println("최신소식홍보물 등록 성공");
      else System.out.println("최신소식홍보물 등록 실패");
//    } catch (SQLException e) {
//      System.out.println("SQL 에러 : " + e.getMessage());
//    } finally {
      pstmtClose();
//    }
    return result;  
  }
  
  public int update(RecentPopularPromotionVO vo) {
    StringBuffer sql = new StringBuffer("");
    int result = 0;
    if (null == vo.getSubject() && null == vo.getOrigin()) return result;
    try {
      sql.append("UPDATE RECENT_POPULAR_PROMOTION")
        .append(" SET");
      if (null != vo.getSubject()) sql.append(" SUBJECT = ?,");
      if (null != vo.getOrigin()) sql.append(" ORIGIN = ?");
      if (sql.lastIndexOf(",") == sql.length()-1)
        sql.replace(sql.lastIndexOf(","), sql.length(), "");
      sql.append(" WHERE CREATE_DATE = ?");
      pstmt = conn.prepareStatement(sql.toString());
      if (null != vo.getSubject()) pstmt.setString(1, vo.getSubject());
      if (null != vo.getOrigin()) pstmt.setString(2, vo.getOrigin());
      pstmt.setString(3, vo.getCreateDate());
      result = pstmt.executeUpdate();
      if (0 < result) System.out.println("최신소식홍보물 수정 성공");
      else System.out.println("최신소식홍보물 수정 실패");
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      pstmtClose();
    }
    return result;  
  }
  public int delete(RecentPopularPromotionVO vo) {
    int result = 0;
    try {
      sql = "DELETE FROM RECENT_POPULAR_PROMOTION"
          + " WHERE CREATE_DATE = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, vo.getCreateDate());
      result = pstmt.executeUpdate();
      if (0 < result) System.out.println("최신소식홍보물 삭제 성공");
      else System.out.println("최신소식홍보물 삭제 실패");
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      pstmtClose();
    }
    return result;  
  }
  private void rsClose() {
    try {
      if (null != rs) rs.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  private void pstmtClose() {
    try {
      if (null != pstmt) pstmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  @SuppressWarnings("unused")
  private void dbClose() {
    try {
      if (null != conn) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
