package com.bigdatalighter.ioclearning;

import com.bigdatalighter.ioclearning.anotation.MyAutowired;
import com.bigdatalighter.ioclearning.anotation.MyComponent;
import com.bigdatalighter.ioclearning.util.IocUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description:
 *
 * @author: Leo Zhang(johnson5211.work@gmail.com)
 **/
public class MyApplicationContext {
    public static final String DEFAULT_PROPERTIES_FILENAME = "application.properties";
    public static final String KEY_IOC_BEAN_SCAN = "ioc.bean.scan";

    private Set<String> classNameSet = new HashSet<String>();
    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    public MyApplicationContext() {
        try{
            init();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init() throws Exception {
        String beanScanPath = getBeanScanPath(KEY_IOC_BEAN_SCAN);
        loadBeanClass(beanScanPath);
        registerBean();
        dependenceInjection();
    }

    public Object getBean(String name) throws Exception{
        return beanMap.get(name);
    }

    /**
     * 加载包所有的类（仅加载类名，不是真实的类加载）
     * @param packageName
     */
    public void loadBeanClass(String packageName) {
        //com.bigdatalighter.ioclearning transfer to com/bigdatalighter/ioclearning
        String filePath = packageName.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(filePath);
        File rootFile = new File(url.getFile());
        if (rootFile != null && rootFile.isDirectory()) {
            for(File file : rootFile.listFiles()) {
                if (file.isDirectory()) {
                    loadBeanClass(packageName + "." + file.getName());
                } else {
                    int indexOf = file.getName().indexOf(".class");
                    if (indexOf > 0) {
                        classNameSet.add(packageName + "." + file.getName().substring(0, indexOf));
                    }
                }
            }
        }
    }


    private String getBeanScanPath(String key) {
        Properties prop = IocUtil.getPropertyByName(DEFAULT_PROPERTIES_FILENAME);
        Object value = prop.get(key);
        return value == null ? null : value.toString();
    }

    /**
     * 加载类，根据类注解，确定是否为组件类，是的话创建实例
     * @throws Exception
     */
    private void registerBean() throws Exception {
        if (classNameSet != null && classNameSet.size() > 0) {
            for (String className : classNameSet){
                Class<?> clazz = Class.forName(className);
                MyComponent component = clazz.getAnnotation(MyComponent.class);
                if (component != null) {
                    String beanName = component.values().length() > 0 ? component.values() : IocUtil.toLowerCaseIndex(clazz.getSimpleName());
                    beanMap.put(beanName, clazz.newInstance());
                }
            }
        }
    }

    /**
     * 对注册的组件进行依赖注入
     * @throws Exception
     */
    private void dependenceInjection() throws Exception {
        if (beanMap.size() > 0) {
            for (Object object : beanMap.values()) {
                doInjection(object);
            }
        }
    }

    /**
     * 依赖注入
     * @param o
     * @throws Exception
     */
    private void doInjection(Object o) throws Exception {
        Field[] fields = o.getClass().getDeclaredFields();
        if (fields.length > 0) {
            for (Field field : fields) {
                MyAutowired autowired = field.getAnnotation(MyAutowired.class);
                if (autowired != null) {
                    String beanName = autowired.value().length() > 0 ? autowired.value() : IocUtil.toLowerCaseIndex(field.getType().getSimpleName());
                    Object bean = beanMap.get(beanName);
                    if (bean == null) {
                        bean = field.getType().newInstance();
                        beanMap.put(beanName, bean);
                    }
                    field.setAccessible(true);
                    field.set(o, bean);
                    doInjection(bean);
                }
            }
        }
    }

}
