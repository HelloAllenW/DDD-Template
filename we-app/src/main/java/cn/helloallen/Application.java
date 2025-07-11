package cn.helloallen;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// import org.springframework.context.annotation.Import;

/**
 * @SpringBootApplication
 * 这是Spring Boot的核心注解，它是一个组合注解，相当于同时使用了：
 * @SpringBootConfiguration: 标识为Spring Boot配置类
 * @EnableAutoConfiguration: 启用Spring Boot自动配置
 * @ComponentScan: 启用组件扫描，默认扫描当前包及其子包
 */
@SpringBootApplication

/**
 * @Configurable
 * 使类能够被Spring进行依赖注入
 * 通常用于在非Spring管理的对象中注入Spring Bean
 */
@Configurable
public class Application {

  // 这是Spring Boot应用的入口方法
  public static void main(String[] args) {
    // SpringApplication.run() 启动Spring Boot应用程序，传入当前类作为主配置类
    SpringApplication.run(Application.class);
  }

  /**
   * 条件性启用Dubbo
   * 仅在非local环境下启用
   */
  /**
   * @EnableDubbo
   * 启用Dubbo的自动配置
   * 自动配置Dubbo的注册中心、服务发现、负载均衡等
   * 扫描并注册Dubbo服务
   */
  @EnableDubbo
  /**
   * @ConditionalOnProperty
   * 这是Spring Boot的条件注解，用于条件性地启用配置：
   * name = "dubbo.enabled": 检查配置属性dubbo.enabled。
   * havingValue = "true": 当属性值为true时生效
   * matchIfMissing = true: 如果属性不存在，默认认为条件满足
   * 
   * 可以通过设置dubbo.enabled=false来禁用Dubbo
   */
  @ConditionalOnProperty(name = "dubbo.enabled", havingValue = "true", matchIfMissing = true)
  public static class DubboConfig {}

}
