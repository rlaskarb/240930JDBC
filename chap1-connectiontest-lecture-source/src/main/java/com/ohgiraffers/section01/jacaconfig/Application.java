package com.ohgiraffers.section01.jacaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.util.Date;

public class Application {

    private static String DRIVER = "com.mysql.jdbc.Driver";
    private static String URL = "jdbc:mysql://localhost/,menudb";
    private static String USER = "ohgiraffers";
    private static String PASSWORD = "ohgiraffers";


    public static void main(String[] args) {

        // MyBatis 기능을 사용하기 위한 환경설정


        /* index 1. 환경 구성 */

        /* comment
        * JdbcTransactionFactory : 코드 작성을 통해 수동으로 커밋을 조작(수동커밋)
        * ManagedTransactionFactory : JDBC 가 자동으로 커밋을 수행 (자동커밋)
        * ------------------------------------
        * PooledDataSource : ConnectionPool 사용
        * UnPooledDataSource : ConnectionPool 미사용 */

        //Environment 란 데이터베이스 접속에 관한 환경설정 정보를 가진 객체로 MyBatis 환경설정 객체를 생성하는 사용된다.
        //Environment 클래스는 기본 생성자를 제공하지 않는다.
        //1. 환경정보 이름: 생상할 환경정보의 이름을 설정한다.
        //2. 트랜잭션 매니저 종류는 두가지가 있고, 안전한 트랜잭션 관리를 위해 JdbcTransactionFactory 사용을 권장한다.
        //3. 케넥션풀 사용 여부에 따라 두가지 설정을 할 수 있고, 사용하는 경우 Connection 속도 및 메모리 효율이 증대 되는 장점이 있다.
        //   - 어떤 설정을 사용하든 생성 시 데이터베이스 연결을 위한 설정 정보(드라이버,데이터베이스, URL , USER , 비밀번호) 를 인자값에 주어야 한다.

        Environment environment = new Environment(
                "dev"
                ,new JdbcTransactionFactory()
                ,new PooledDataSource(DRIVER,URL,USER,PASSWORD));

        /*index 2. 만들어둔 환경 정보를 바탕으로 MyBatis 설정 구성*/
        Configuration configuration = new Configuration(environment);

        /*index 4. DataBase 와 접근을 할 수 있는 클래스 등록 */
        configuration.addMapper(Mapper.class);

        /*index 3. DateBase 와 접속 할 정보를 만들었다. 이제 Session 구성 */
        /*comment
        * SqlSession 을 만들기 위한 준비 단계
        * SqlSessionFactory : SqlSession 객체를 생성하기 위한 팩토리(공장) 역할의 인터페이스
        *
        * SqlSessionFactoryBuilder
        * -SqlSessionFactory 인터페이스 타입의 하의 구현 객체를 생성하기 위한 빌드 역할을 수행한다.
        *
        * build()
        * -환경 설정에 대한 정보를 담고 있는 Configuration 타입의 객체
        * -혹은 외부의 설정 파일과 연결 된 Stream 을 매개 변수로 전달하면
        * -SqlSessionFactory 인터페이스 타입의 객체를 반환*/


        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(configuration);

        /*comment
         *  openSession()
         * -SqlSession 인터페이스 타입의 객체를 반환하는 메소드
         * -boolean 타입을 인자로 전달 할 수 있다.
         * -false 를 전달하게 되면
         * -Connection 인터페이스 타입의 객체로 DML 수행후 autocommit
         * -설정을 꺼주게 된다.(권장)
         * */
        SqlSession sqlSession = factory.openSession(false);


        /*index 5 등록한 매퍼를 활용해서 내부에 작성한 기능을 수행 */
        Mapper mapper = sqlSession.getMapper(Mapper.class);

        Date date = mapper.selectSysDate();

        System.out.println("date = " + date);

        sqlSession.close();

    }
}
