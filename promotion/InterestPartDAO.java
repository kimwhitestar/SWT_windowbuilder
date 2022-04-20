package WindowBuilder.promotion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class InterestPartDAO {
  private Connection conn = null;
  private Statement stmt = null;
  private String sql = null;
  private ResultSet rs = null;
  private InterestPartVO vo = null;
  
  public InterestPartDAO() {
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
  @SuppressWarnings("rawtypes")
  public List<InterestPartVO> search() {
    List<InterestPartVO> listVO = new ArrayList<>();
    try {
      sql = "SELECT PART_CODE, PART_NAME FROM INTEREST_PART";
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql);
      while(rs.next()) {
        vo = new InterestPartVO();
        vo.setPartCode(rs.getInt("PART_CODE"));
        vo.setPartName(rs.getString("PART_NAME"));
        listVO.add(vo);
      }
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      rsClose();
      stmtClose();
    }
    return listVO;
  }
  public int insert(InterestPartVO vo) {
    int result = 0;
    try {
      sql = "INSERT INTO INTEREST_PART VALUES (default, '"+ vo.getPartName() +"')";
      stmt = conn.createStatement();
      result = stmt.executeUpdate(sql);
      if (0 < result) System.out.println("관심분야 입력 성공");
      else System.out.println("관심분야 입력 실패");
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      stmtClose();
    }
    return result;
  }
  public int delete(InterestPartVO vo) {
    int result = 0;
    try {
      sql = "DELETE FROM INTEREST_PART WHERE PART_CODE = '"+ vo.getPartName() +"')";
      stmt = conn.createStatement();
      result = stmt.executeUpdate(sql);
      if (0 < result) System.out.println("관심분야 삭제 성공");
      else System.out.println("관심분야 삭제 실패");
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      stmtClose();
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
  private void stmtClose() {
    try {
      if (null != stmt) stmt.close();
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
