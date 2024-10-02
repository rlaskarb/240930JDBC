package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {

        /*comment
        *  ?
        * -placeholder
        * -? 의 갯수, 시작값 (1)
        * */


        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print(" 사번을 입력 하세욧 " );
        String empId = sc.nextLine();

        String query = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? ";

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1,empId);

            rset =pstmt.executeQuery();

            if(rset.next()){
                System.out.println(rset.getString("EMP_ID") + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(pstmt);
            close(rset);
        }

    }
}
