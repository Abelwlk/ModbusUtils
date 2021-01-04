package com.cczu.modbus.common;

import java.util.ResourceBundle;

/**
 * @author weiliangkang
 * @date 2020/12/3 14:08
 */
public class PropertiesUtil {
    private static final ResourceBundle BUNDLE;

    static {
        BUNDLE = ResourceBundle.getBundle("modbus");
    }

    public static String getValue(String key) {
        return BUNDLE.getString(key);
    }
}
