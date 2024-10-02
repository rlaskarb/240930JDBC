package com.ohgiraffers.section01.insert;

import com.ohgiraffers.section01.mode.dto.MenuDTO;

import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.section01.common.JdbcTemplate.close;
import static com.ohgiraffers.section01.common.JdbcTemplate.getConnection;

public class Application2 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("신규메뉴의 이름을 입력하세요 : ");
        String menuName = sc.nextLine();
        System.out.println();
        System.out.print("신규 메뉴의 가격을 입력하세요 : ");
        int menuPrice = sc.nextInt();
        System.out.println();
        System.out.print("신규메뉴의 카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.println();
        System.out.print("판매여부 지정하세요(Y/N) : ");
        sc.nextLine();
        String oderableStatus =sc.nextLine().toUpperCase();

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        MenuDTO newMenu = new MenuDTO();
        newMenu.setMenuName(menuName);
        newMenu.setMenuPrice(menuPrice);
        newMenu.setCategoryCode(categoryCode);
        newMenu.setOrderableStatus(oderableStatus);

        Properties prop = new Properties();

         int result =0;

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));

            String query = prop.getProperty("insertMenu");

            pstmt =con.prepareStatement(query);
            pstmt.setString(1,newMenu.getMenuName());
            pstmt.setInt(2,newMenu.getMenuPrice());
            pstmt.setInt(3,newMenu.getCategoryCode());
            pstmt.setString(4,newMenu.getOrderableStatus());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
        }

        if(result >0){
            System.out.println("등록성공");
        }else {
            System.out.println("등록 실패");
        }

    }

}
