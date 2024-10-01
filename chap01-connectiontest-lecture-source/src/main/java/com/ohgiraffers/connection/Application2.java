package com.ohgiraffers.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application2 {
    public static void main(String[] args) {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String password = "ohgiraffers";

        /*index  1. Connection  객체선언 */
        Connection con = null;



        try {
            /*index 2. 사용할 드라이버 등록*/
            Class.forName(driver);

            /*index 3. 드라이버 메니저로 Connection */
            con=DriverManager.getConnection(url,user,password);

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
         /*index 4. 사용한 커넥션 자원 닫기 */
        }finally{
            if(con != null){
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
