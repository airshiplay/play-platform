package com.airlenet.play.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
/**
 * Created by lig on 17/6/9.
 */
public class SystemEnvVariables {

    private static final Logger LOG = LoggerFactory.getLogger(SystemEnvVariables.class);


    public String getAppRoot() {
        String appRoot = null;
        appRoot = System.getProperty("catalina.base");
        if(appRoot != null) {
            appRoot = getCanonicalPath(appRoot);
        }
        return appRoot;
    }

    /**
     * Gets the canonical path<br/>
     *
     * @param inPath input path
     * @return the canonical path.
     * @since
     */
    private String getCanonicalPath(final String inPath) {
        String path = null;
        try {
            if(inPath != null) {
                final File file = new File(inPath);
                path = file.getCanonicalPath();
            }
        } catch(final IOException e) {
            LOG.error("file.getCanonicalPath() IOException:", e);
        }
        return path;
    }
}
