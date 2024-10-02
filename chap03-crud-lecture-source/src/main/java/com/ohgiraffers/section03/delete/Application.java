package com.ohgiraffers.section03.delete;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.section01.common.JdbcTemplate.close;
import static com.ohgiraffers.section01.common.JdbcTemplate.getConnection;

public class Application {
    public static void main(String[] args) {

        Scanner sc =new Scanner(System.in);
        //1개의 행을 삭제 할수 있는 값만 있으면 된다.
        System.out.print("삭제할 메뉴코드를 입력 해주세요 : ");
        int menuCode = sc.nextInt();

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml")
            );

            String query = prop.getProperty("deleteMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,menuCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }
        if(result > 0){
            System.out.println(menuCode + "번 삭제 성공");
        }else{
            System.out.println("삭제실패");
        }


    }
}
