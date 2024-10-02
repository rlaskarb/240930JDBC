package com.ohgiraffers.section01.insert;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.section01.common.JdbcTemplate.close;
import static com.ohgiraffers.section01.common.JdbcTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt =null;
        //ResultSet 은 조회 즉 Select 에서필요하다.

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            
            String query = prop.getProperty("insertMenu");
            System.out.println("query = " + query);

            Scanner sc=new Scanner(System.in);
            System.out.print("신규메뉴의 이름을 입력하세요");
            String menuName = sc.nextLine();
            System.out.println();
            System.out.print("신규 메뉴의 가격을 입력하세요");
            int menuPrice = sc.nextInt();
            System.out.println();
            System.out.print("신규메뉴의 카테고리 코드를 입력하세요");
            int categoryCode = sc.nextInt();
            System.out.println();
            System.out.print("판매여부 지정하세요(Y/N) :");
            sc.nextLine();
            String oderableStatus =sc.nextLine().toUpperCase();
            
            pstmt =con.prepareStatement(query);
            pstmt.setString(1,menuName);
            pstmt.setInt(2,menuPrice);
            pstmt.setInt(3,categoryCode);
            pstmt.setString(4,oderableStatus);
            
            result = pstmt.executeUpdate();
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }

        System.out.println("result = " + result);
        
        if(result >0){
            System.out.println("메뉴등록 성공");
        }else{
            System.out.println("알수없는 이유로 메뉴등록 실패");
        }
        
    }
}
