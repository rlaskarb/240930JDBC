package com.ohgiraffers.section02.preparedstatement;

import com.ohgiraffers.mode.dto.EmployeeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JdbcTemplate.close;
import static com.ohgiraffers.common.JdbcTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        //
        EmployeeDTO emp = null;

        Scanner sc =new Scanner(System.in);
        System.out.print("사번을 입력하세욧");
        String empId =sc.nextLine();

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? ";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1,empId);
            rset = pstmt.executeQuery();
            if(rset.next()){
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


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(rset);
            close(pstmt);
            close(con);
        }

        System.out.println("emp = " + emp );


    }
}
