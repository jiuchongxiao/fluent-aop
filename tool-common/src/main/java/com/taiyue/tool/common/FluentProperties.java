package com.taiyue.tool.common;

/**
 * Created by admin on 2018/5/28.
 */
import org.fluentd.logger.FluentLogger;

import java.io.*;
import java.util.Properties;

/**
 * 读取Properties文件的例子
 * File: FluentProperties.java
 * User: leizhimin
 * Date: 2008-2-15 18:38:40
 */
public final class FluentProperties {
    private static String projectame;
    private static String fluentUrl;
    private static Integer fluentPort;

    private static FluentLogger log;

    static {
        Properties prop = new Properties();
//        相对路径 spring boot
//        InputStream in = Object.class.getResourceAsStream("/application.properties");
        try {
//            绝对路径
//            FileInputStream in = new FileInputStream("D:\\work\\x\\spring4-mvc-example\\application.properties");

            //spring boot 将application.properties放到 resources下
            //spring mvc 将application.properties放到 WEB-INFO下classes文件夹下

//            String path = FluentProperties.class.getClassLoader().getResource("").getPath()+"/application.properties";
//            FileInputStream in = new FileInputStream(path);


            InputStream inputStream = FluentProperties.class.getResourceAsStream("/application.properties");

//            String projectPath = System.getProperty("user.dir");
//            File file = new File(projectPath+"/conf/application.properties");
//            FileInputStream in = new FileInputStream(file);

            prop.load(inputStream);

            FluentProperties.class.getClassLoader().getResourceAsStream("application.properties");

            projectame = prop.getProperty("project.name").trim();
            fluentUrl = prop.getProperty("fluent.url").trim();
            fluentPort = Integer.parseInt(prop.getProperty("fluent.port").trim());

            log = FluentLogger.getLogger(FluentProperties.getProjectame(), FluentProperties.getFluentUrl(), FluentProperties.getFluentPort());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FluentLogger getLog() {
        return log;
    }

    public static void setLog(FluentLogger log) {
        FluentProperties.log = log;
    }

    /**
     * 私有构造方法，不需要创建对象
     */
    private FluentProperties() {
    }

    public static String getProjectame() {
        return projectame;
    }

    public static void setProjectame(String projectame) {
        FluentProperties.projectame = projectame;
    }

    public static String getFluentUrl() {
        return fluentUrl;
    }

    public static void setFluentUrl(String fluentUrl) {
        FluentProperties.fluentUrl = fluentUrl;
    }

    public static Integer getFluentPort() {
        return fluentPort;
    }

    public static void setFluentPort(Integer fluentPort) {
        FluentProperties.fluentPort = fluentPort;
    }
//    public static void main(String args[]){
//        System.out.println(getParam1());
//        System.out.println(getParam2());
//    }
}
