package org.mydal.servicea.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.mydal.common.po.ServiceADemo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface ServiceADemoMapper {

    @Select({"select    id ,  \n" +
            "           text  \n" +
            "from netflix_demo_db.service_a_demo   \n"})
    @Results(id = "queryOne",value= {
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "text", column = "text", javaType = String.class),
    })
    List<ServiceADemo> queryAll();

}
