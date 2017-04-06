package org.seckill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by jesse on 2017/3/30.
 */
@SpringBootApplication
@ImportResource("classpath:spring/*.xml")
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class,args);
    }
}
