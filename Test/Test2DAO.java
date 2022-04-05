package WindowBuilder.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Test2DAO {
  Connection conn = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  String sql = "";
  Test3VO vo = null;

  //처음 DAO생성시에 Database 연결
  public Test2DAO() {
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
    } finally {
      rsClose();
      pstmtClose();
    }
    return vData;
  }
  @SuppressWarnings({ "rawtypes", "unused" })
  public void Test2Input(Test2VO vo2) {
//    Vector vData = new Vector();//제너릭Vector<E>은 에러난다고 함(선생님)
    try {
      sql = "insert into dbtest values (default,?,?,?,?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, vo2.getName());
      pstmt.setInt(2, vo2.getAge());
      pstmt.setString(3, vo2.getGender());
      pstmt.setString(4, vo2.getJoinday());
      int result = pstmt.executeUpdate();
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      pstmtClose();
    }
  }
  //개별자료 검색
  public Test2VO getSearch(int idx) {
    Test2VO vo = new Test2VO();
    try {
      sql = "select * from dbtest where idx = ?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setInt(1, idx);
      rs = pstmt.executeQuery();
      if(rs.next()) {
        vo.setIdx(idx);
        vo.setName(rs.getString("name"));
        vo.setAge(rs.getInt("age"));
        vo.setGender(rs.getString("gender"));
        vo.setJoinday(rs.getString("joinday"));
      }
    } catch (SQLException e) {
      System.out.println("SQL 에러 : " + e.getMessage());
    } finally {
      rsClose();
      pstmtClose();
    }
    return vo;
  }
  //조건검색
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public Vector getSearch(String cmbSearch, String txtStr) {
    Vector vData = new Vector();
    try {
      //select * from dbtest where age like '%1%' order by idx desc;
      sql = "select * from dbtest where "+cmbSearch+" like ? order by idx desc";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, "%"+txtStr+"%");
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
    } finally {
      rsClose();
      pstmtClose();
    }
    return vData;
  }
}