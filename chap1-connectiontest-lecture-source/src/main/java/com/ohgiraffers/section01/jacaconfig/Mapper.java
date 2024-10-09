package com.ohgiraffers.section01.jacaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {
    // DUAL : 가상의 테이블

    @Select("SELECT CURRENT_DATE() FROM DUAL")
    Date selectSysDate(); // 가상의테이블임.(연달아 작성하면 해당 메소드 명에 바로 위의

}
