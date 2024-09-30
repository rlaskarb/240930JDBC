package com.ohgiraffers.section02.template;

import java.sql.Connection;

import static com.ohgiraffers.section02.template.JdbcTemplate.getConnection;
import static com.ohgiraffers.section02.template.JdbcTemplate.close;

public class Application {
    public static void main(String[] args) {

        Connection con = getConnection();

        System.out.println("con = " + con);
        
        close(con);

    }
}
