package org.mydal.serviceb.log;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LogMessage implements Serializable {

    private static final long serialVersionUID = 649279812344444247L;

    /**
     * 日志编号
     */
    private Long id;
    /**
     * ikey，表示一次请求的所有日志相同的Key值
     */
    private String ikey;
    /**
     * 请求者的用户名
     */
    private String username;
    /**
     * 日志时间
     */
    private Date logTime;
    /**
     * 请求者的IP地址
     */
    private String clientIP;
    /**
     * 服务器的IP地址
     */
    private String serverIP;
    /**
     * 日志所在的模块信息
     */
    private String module;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 日志类型
     */
    private String logType;
    /**
     * 供检索所用的关键词(主要用于统计数据)
     */
    private String keyword;
    /**
     * 日志信息
     */
    private String content;
    /**
     * 是输入输出日志，还是过程日志(true: 输入输出日志，反之)
     */
    private Boolean isHandle;
}

