package com.ohgiraffers.section01.statement;

import com.ohgiraffers.mode.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {



        Connection con = getConnection();

        Statement stmt = null;

        ResultSet rset = null;

        EmployeeDTO emp = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("번호 ㄱ");
        String empId = sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = '" + empId +"'";

        try {
            stmt = con.createStatement();
            rset = stmt.executeQuery(query);

            /*조회한 결과를 객체에 담기 */
            if(rset.next());
            emp = new EmployeeDTO();

            emp.setEmpId(rset.getString("EMP_ID"));
            emp.setEmpName(rset.getString("EMP_NAME"));
            emp.setEmpNo(rset.getString("EMP_NO"));
            emp.setEmail(rset.getString("EMAIL"));
            emp.setPhone(rset.getString("phone"));
            emp.setDeptCode(rset.getString("DEPT_CODE"));
            emp.setJobCode(rset.getString("JOB_CODE"));
            emp.setSaLevel(rset.getString("SAL_LEVEL"));
            emp.setSalary(rset.getString("SALARY"));
            emp.setBonus(rset.getString("BONUS"));
            emp.setManagerID(rset.getString("MANAGER_ID"));
            emp.setHireDate(rset.getDate("HIRE_DATE"));
            emp.setEntDate(rset.getDate("ENT_DATE"));
            emp.setEntYn(rset.getString("ENT_YN"));

            System.out.println("emp =" +emp );


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(rset);
            close(stmt);

        }


    }
}
