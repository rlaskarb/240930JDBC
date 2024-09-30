package com.ohgiraffers.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application02 {

    public static void main(String[] args) {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/employee";
        String user = "ohgiraffers";
        String password = "ohgiraffers";



        /*index 2. 사용할 드러이버 등록*/
        Connection con = null;

        /*intdex 33 드라이버 메니저로 Connection */

        try {
            Class.forName(driver);

            /*index*/
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
            /*intdex 4.사용한 커넥션 자원 닫기 */
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
