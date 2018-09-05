package com.airlenet.auth.config;
//
//import com.airlenet.web.logging.AccessLoggerInfo;
//import com.airlenet.web.logging.events.AccessLoggerAfterEvent;
//import com.alibaba.fastjson.JSON;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.io.Serializable;
//import java.util.stream.Stream;
//
//@Component
//public class AccessLogConfig {
//    private Logger logger = LoggerFactory.getLogger("access-logger");
//
//    private static final Class excludes[] = {
//            ServletRequest.class,
//            ServletResponse.class,
//            InputStream.class,
//            OutputStream.class,
//            MultipartFile.class
//    };
//
//
//    @EventListener
//    public void onLogger(AccessLoggerAfterEvent event) {
//        AccessLoggerInfo info = event.getLogger();
//
//        if (logger.isInfoEnabled()) {
//            logger.info(JSON.toJSONString(info.toSimpleMap(obj -> {
//                if (Stream.of(excludes).anyMatch(aClass -> aClass.isInstance(obj))) return obj.getClass().getName();
//                return (Serializable) JSON.toJSON(obj);
//            })));
//        }
//    }
//}
