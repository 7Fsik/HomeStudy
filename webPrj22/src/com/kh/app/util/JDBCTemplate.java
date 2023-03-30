package com.kh.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTemplate {
   
   //커넥션 객체 얻기
   public static Connection getConnection() {
      Connection conn = null;
      try {
         //드라이버 준비
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         //conn
         String url = "jdbc:oracle:thin:@localhost:1521:xe";
         String id = "C##SEMI";
         String pwd = "1234";
         conn = DriverManager.getConnection(url, id, pwd);
         
         //setAutoCommit false
         conn.setAutoCommit(false);
      }catch(Exception e) {
         System.out.println("[ERROR] 커넥션 준비중 예외 발생 ...");
         e.printStackTrace();
      }
      return conn;
   }//method
   
   //커밋
   public static void commit(Connection conn) {
      try {
         if( conn != null && !conn.isClosed() ) {
            conn.commit();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   
   //롤백
   public static void rollback(Connection conn) {
      try {
         if( conn != null && !conn.isClosed() ) {
            conn.rollback();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   //close * 3   //conn , rs , stmt
   
   public static void close(Connection conn) {
      try {
         if( conn != null && !conn.isClosed() ) {
            conn.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public static void close(ResultSet rs) {
      try {
         if( rs != null && !rs.isClosed() ) {
            rs.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }
   
   public static void close(Statement stmt) {
      try {
         if( stmt != null && !stmt.isClosed() ) {
            stmt.close();
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
   }

}//class