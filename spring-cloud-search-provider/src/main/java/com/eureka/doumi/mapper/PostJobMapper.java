package com.eureka.doumi.mapper;

import com.doumi.pojo.PostJobList;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostJobMapper {

    @Select("select * from postjoblist")
    List<PostJobList> getAllJobList();
}
