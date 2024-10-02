package com.ohgiraffers.connection;

import com.mysql.cj.jdbc.Driver;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Application03 {
    public static void main(String[] args) {

        /* index 1. Properties 파일을 읽기 위해서 Properties 객체 생성 */

        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(new FileReader("src/main/java/com/ohgiraffers/connection/jdbc-config.properties"));
            System.out.println("prop = " + prop);

            String driver= prop.getProperty("driver");
            String url =prop.getProperty("url");
            String user=prop.getProperty("user");
            String password=prop.getProperty("password");

            Class.forName(driver);

            con = DriverManager.getConnection(url,user,password);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
