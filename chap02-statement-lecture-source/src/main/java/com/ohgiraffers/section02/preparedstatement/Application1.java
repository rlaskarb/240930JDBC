package com.ohgiraffers.section02.preparedstatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        /*comment
        * PreparedStatement <-- 준비된 Statement
        * Statement 는 SQL 쿼리문을 실행 할 때마다 SQL 문이
        * DBMS 에 전송이 되어 DBMS 에서 SQL 문을 파싱하고 컴파일을
        * 하는 과정을 거치게 된다.
        * PreparedStatement 는 최초의 한번 실행했을 시
        * SQL 문을 파싱하고 컴파일을 하지만,
        * 동일한 SQL 구문을 여려 번 실하게 되면
        * 최조의 컴파일한 SQL 구문을 재사용하게 된다.
        * 따라서 파싱하고 컴파일하는 과정을 생략학게 되어
        * 성능이 향상이 된다.*/

        Connection con = getConnection();

        //쿼리문을 실행
        PreparedStatement pstmt = null;

        //쿼리문을 실행 결과
        ResultSet rset = null;

        String query = "SELECT EMP_ID,EMP_NAME FROM EMPLOYEE";

        try {
            pstmt = con.prepareStatement(query);
            rset = pstmt.executeQuery();

            while (rset.next()){
                System.out.println(rset.getString("EMP_ID")+ "번 " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rset);
            close(con);
            close(pstmt);
        }


    }
}
