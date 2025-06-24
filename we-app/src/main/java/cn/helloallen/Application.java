package cn.helloallen;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
@EnableDubbo
@MapperScan("cn.helloallen.domain.dao")
public class Application {

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
