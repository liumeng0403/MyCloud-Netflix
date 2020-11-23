package org.mydal.serviceb.log;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.mydal.serviceb.log.common.JsonUtil;
import org.mydal.serviceb.log.es.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@Component
public class LogDemo {

    @Autowired
    private Logger logger;

    public void logToEs(LogMessage logMessage) {
        try {

            if (StringUtils.isBlank(logMessage.getClientIP())) {
                logMessage.setClientIP(null);
            } else if (StringUtils.containsIgnoreCase(logMessage.getClientIP(), "localhost")) {
                logMessage.setClientIP("127.0.0.1");
            }
            if (StringUtils.isBlank(logMessage.getServerIP())) {
                logMessage.setServerIP(null);
            } else if (StringUtils.containsIgnoreCase(logMessage.getServerIP(), "localhost")) {
                logMessage.setServerIP("127.0.0.1");
            }
            logger.log(logMessage);

        } catch (Exception e) {
            log.info("错误描述--" + null == e.getMessage() ? "" : e.getMessage());
            log.info("ELK-log-json--" + JsonUtil.getJackson().toJson(logMessage));
        }
    }

}
