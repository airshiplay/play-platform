package com.airlenet.web.logging.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import com.airlenet.web.logging.AccessLoggerInfo;

@AllArgsConstructor
@Getter
public class AccessLoggerBeforeEvent {

    private AccessLoggerInfo logger;
}
