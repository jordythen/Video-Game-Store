package com.revature.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ResourceUtil {
	public static Logger log = Logger.getLogger(ResourceUtil.class);

	
    private static InputStream getResource(String resource) {
        return ResourceUtil.class.getResourceAsStream("/" + resource);
    }

    public static Properties getProperties(String resource) {
        Properties prop = new Properties();
        InputStream stream = getResource(resource);
        if (stream != null) {
            try {
                prop.load(stream);
            } catch (IOException ex) {
                log.error("Could not open " + resource + " properties file!");
                ex.printStackTrace();
            }
        }
        return prop;

    }
}
