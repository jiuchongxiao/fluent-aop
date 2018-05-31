package com.taiyue.tool.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MagicExistsCondition implements Condition {
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        ResourceLoader resourceLoader = context.getResourceLoader();
//        Resource resource = resourceLoader.getResource("application.properties");
        try {
//            String path = FluentProperties.class.getClassLoader().getResource("").getPath()+"/application.properties";

//            FileInputStream in = new FileInputStream(path);

            InputStream inputStream = FluentProperties.class.getResourceAsStream("/application.properties");

//            String projectPath = System.getProperty("user.dir");
//            File file = new File(projectPath+"/conf/application.properties");
//            FileInputStream in = new FileInputStream(file);

            Properties properties = new Properties();
            properties.load(inputStream);

//            properties.load(resource.getInputStream());

            return Boolean.parseBoolean(properties.getProperty("fluent.aopStatus"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


}





