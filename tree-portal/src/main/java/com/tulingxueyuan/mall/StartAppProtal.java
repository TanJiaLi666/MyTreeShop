package com.tulingxueyuan.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 * @Author TanJiaLi
 * @Slogan tjl
 */
@SpringBootApplication
@EnableTransactionManagement//开启事务管理
public class StartAppProtal {

    public static void main(String[] args) {
        SpringApplication.run(StartAppProtal.class, args);
     }

}
