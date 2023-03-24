/**
 * this class is used to load the config text file into the program for other classes to get
 */

package com.company;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        if (props == null) return null;
        return (String) props.get(key);
    }
}
