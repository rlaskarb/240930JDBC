package com.ohgiraffers.section02.update;

import com.ohgiraffers.section01.mode.dto.MenuDTO;

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






        //update
        Scanner sc=new Scanner(System.in);

        System.out.println();
        System.out.print("변경하고 싶은 코드를 입력하세요");
        int menuCode = sc.nextInt();
        System.out.println();
        System.out.print("변경하고 싶은 메뉴의 이름을 입력하세요");
        sc.nextLine();
        String menuName = sc.nextLine();
        System.out.println();
        System.out.print("변경하고 싶은  가격를 입력하세요");
        int menuPrice = sc.nextInt();
        System.out.println();

        MenuDTO changerMenu = new MenuDTO();
        changerMenu.setMenuCode(menuCode);
        changerMenu.setMenuName(menuName);
        changerMenu.setMenuPrice(menuPrice);

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result =0;

        Properties prop = new Properties();



        try {
            prop.loadFromXML(
                    new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml")
            );

            String query = prop.getProperty("updateMenu");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,changerMenu.getMenuName());
            pstmt.setInt(2,changerMenu.getMenuPrice());
            pstmt.setInt(3,changerMenu.getMenuCode());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstmt);
            close(con);
        }
        if(result >0){
            System.out.println(changerMenu.getMenuCode()+" 번 메뉴 변경 성공");

        }else {
            System.out.println(" 실패");
        }
    }
}
