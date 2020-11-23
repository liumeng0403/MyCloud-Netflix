package org.mydal.serviceb.log.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ContextUtil implements ApplicationContextAware {

    /*
     * mydal : 方便获取 上下文 IOC 容器 ， 2019-08-29
     */

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        if(context==null){
            context=applicationContext;
        }
    }

    public static ApplicationContext getContext(){
        return context;
    }

}
