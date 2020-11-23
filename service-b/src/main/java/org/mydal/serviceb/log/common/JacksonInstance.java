package org.mydal.serviceb.log.common;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringEscapeUtils;

import java.nio.charset.Charset;

public class JacksonInstance {

    private ObjectMapper objectMapper;

    public JacksonInstance(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String toJson(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException("jackson to json failed!", e);
        }
    }

    public <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException("jackson from json 1 failed!", e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("jackson from json 2 failed!", e);
        }
    }

    public <T> T fromJson(byte[] json, Class<T> clazz) {
        /**
         * 注意:
         * 此段代码 仅为 特殊情况的演示, 生产不能这样写的 !!!
         * */
        try {
            String str0 = StringEscapeUtils.unescapeJava(new String(json, Charset.forName("utf-8")));
            String str1 = "";
            if(str0.startsWith("\"")){
                str1 = str0.substring(1,str0.length());
            }else {
                str1 = str0;
            }
            String str2 = "";
            if(str1.endsWith("\"")){
                str2 = str1.substring(0,str0.length()-1);
            }else {
                str2 = str1;
            }
            return objectMapper.readValue(str2, clazz);
        } catch (Exception e) {
            throw new RuntimeException("jackson from json 3 failed!", e);
        }
    }

}
