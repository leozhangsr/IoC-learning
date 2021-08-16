package com.bigdatalighter.ioclearning.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description:
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class IocUtil {

    /**
     * 加载配置文件
     * @param filename
     * @return
     */
    public static Properties getPropertyByName(String filename) {
        InputStream is = null;
        Properties prop = null;
        try {
            is = IocUtil.class.getClassLoader().getResourceAsStream(filename);
            prop = new Properties();
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return prop;
    }

    /**
     * 根据类名转化为对象变量名，按java变量命名规范，默认变更名为类名，首字母小写，
     * 如IUserDao -> iUserDao
     * @param name
     * @return
     */
    public static String toLowerCaseIndex(String name) {
        if (name != null && name.length() >0) {
           StringBuilder sb =  new StringBuilder();
           sb.append(name.substring(0,1).toLowerCase())
                   .append(name.substring(1, name.length()));
           return sb.toString();
        }
        return null;
    }

}
