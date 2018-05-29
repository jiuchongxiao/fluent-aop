package com.taiyue.tool.common.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
//import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by ljb on 2018/5/28.
 */
@SpringBootApplication
@Import({ControllerProcessAop.class, DaoProcessAop.class, ServiceProcessAop.class})
//事务执行顺序设置
//@EnableTransactionManagement(order = AopOrder.TRANSACTION)
public class App {

    public static void main(String[] args) {
        //System.out.println(Ignite.class);
        SpringApplication.run(App.class, args);
    }

}
