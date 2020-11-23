package org.mydal.serviceb.log.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonUtil {

    /*
     * 自定义 jackson 统一配置  ( Cause: FastJson 反序列化 order 对象总是报错 ),   mydal , 2020-01-15
     */

    @Bean("jsonMapper")
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);  //  转换为格式化的json
        mapper.setSerializationInclusion(JsonInclude.Include.NON_ABSENT);  //
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);  // 如果是空对象的时候,不抛异常
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);  // 如果json中有新增的字段并且是实体类类中不存在的，不报错
        return mapper;
    }

    public static JacksonInstance getJackson() {
        return new JacksonInstance(ContextUtil.getContext().getBean("jsonMapper",ObjectMapper.class));
    }

}
