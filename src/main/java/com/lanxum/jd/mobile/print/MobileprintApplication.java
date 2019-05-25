package com.lanxum.jd.mobile.print;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author jiang
 */
@SpringBootApplication
@EnableScheduling
@MapperScan(value = "com/lanxum/jd/mobile/print/modules/**/mapper")
public class MobileprintApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobileprintApplication.class, args);
    }

}
