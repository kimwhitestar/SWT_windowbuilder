package WindowBuilder.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Test3DAO {
  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String sql = "";
  Test3VO vo = null;

  //처음 DAO생성시에 Database 연결
  public Test3DAO() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      String url = "jdbc:mysql://localhost:3306/javagreen";
      String user = "root";
      String password = "1234";
      conn = DriverManager.getConnection(url, user, password);
    } catch (ClassNotFoundException e) {
      System.out.println("드라이버 검색 실패");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("데이터베이스 연결 실패");
      e.printStackTrace();
    }
  }
  public void rsClose() {
    try {
      if (rs != null) rs.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void pstmtClose() {
    try {
      if (pstmt != null) pstmt.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  public void dbClose() {
    try {
      if (null != conn) conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
  //전체자료를 Vector에 담아 전송(JTable지원 Vector)
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Vector getList() {
    Vector vData = new Vector();//제너릭은 에러난다고 함(선생님)
    try {
      sql = "select * from dbtest order by idx desc";
      pstmt = conn.prepareStatement(sql);
      rs = pstmt.executeQuery();
      while(rs.next()) {
        Vector vo = new Vector();
        vo.add(rs.getInt("idx"));
        vo.add(rs.getString("name"));
        vo.add(rs.getInt("age"));
        vo.add(rs.getString("gender"));
        vo.add(rs.getString("joinday"));
        vData.add(vo);
      }
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    }
    return vData;
  }
  
}