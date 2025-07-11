package cn.helloallen.config;

// Redisson是一个Redis的Java客户端库
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
// 条件注解，根据配置决定是否启用
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
// 启用配置属性类
import org.springframework.boot.context.properties.EnableConfigurationProperties;
// Spring上下文，用于获取应用上下文
import org.springframework.context.ConfigurableApplicationContext;
// Spring注解，将方法返回的对象注册为Spring容器中的Bean
import org.springframework.context.annotation.Bean;
// Spring注解，标识这是一个配置类
import org.springframework.context.annotation.Configuration;

/**
 * Redis 客户端，使用 Redisson
 * <a href="https://github.com/redisson/redisson">Redisson</a>
 *
 * @author wanghailun helloallen.cn
 */
// 配置类注解，标识这是一个配置类
@Configuration
// 启用配置属性类，让Spring可以读取配置文件。与RedisClientConfigProperties类关联
@EnableConfigurationProperties(RedisClientConfigProperties.class)
// 条件注解，根据配置决定是否启用
@ConditionalOnProperty(name = "redis.enabled", havingValue = "true", matchIfMissing = false)
public class RedisClientConfig {

  @Bean("redissonClient")
  // Spring会自动注入这两个参数，properties包含了从配置文件读取的所有Redis配置
  public RedissonClient redissonClient(ConfigurableApplicationContext applicationContext,
      RedisClientConfigProperties properties) {
    Config config = new Config();

    config.useSingleServer() // 使用单机模式连接Redis
        .setAddress("redis://" + properties.getHost() + ":" + properties.getPort())
        // 连接池配置
        .setConnectionPoolSize(properties.getPoolSize())
        .setConnectionMinimumIdleSize(properties.getMinIdleSize())
        // 连接超时配置
        .setIdleConnectionTimeout(properties.getIdleTimeout())
        .setConnectTimeout(properties.getConnectTimeout())
        // 重试配置
        .setRetryAttempts(properties.getRetryAttempts())
        .setRetryInterval(properties.getRetryInterval())
        // 心跳配置
        .setPingConnectionInterval(properties.getPingInterval())
        // 连接保持配置
        .setKeepAlive(properties.isKeepAlive());

    return Redisson.create(config);
  }

}

/**
 * 在项目中使用Redis
 */
/**
 * 1. 注入RedissonClient
  @Service
  public class UserService {
      
      @Autowired
      @Qualifier("redissonClient")
      private RedissonClient redissonClient;
      
      // 或者
      @Resource(name = "redissonClient")
      private RedissonClient redissonClient;
  }
 */
/**
 * 2. 使用Redis操作
  @Service
  public class UserService {
      
      @Resource(name = "redissonClient")
      private RedissonClient redissonClient;
      
      public void saveUser(String userId, String userData) {
          // 获取Map结构
          RMap<String, String> map = redissonClient.getMap("users");
          map.put(userId, userData);
          
          // 设置过期时间
          map.expire(30, TimeUnit.MINUTES);
      }
      
      public String getUser(String userId) {
          RMap<String, String> map = redissonClient.getMap("users");
          return map.get(userId);
      }
      
      public void useDistributedLock(String lockKey) {
          RLock lock = redissonClient.getLock(lockKey);
          try {
              // 尝试加锁，最多等待10秒，锁定后30秒自动解锁
              if (lock.tryLock(10, 30, TimeUnit.SECONDS)) {
                  // 执行业务逻辑
                  System.out.println("获得锁，执行业务");
              }
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          } finally {
              if (lock.isHeldByCurrentThread()) {
                  lock.unlock();
              }
          }
      }
  }
 */
/**
 * 与GuavaConfig对比：
 * RedisClientConfig更加灵活，支持分布式锁、集群模式等
 * 适用于高并发、分布式系统
 * 
 * GuavaConfig适用于单机应用，性能更优
 * 适用于短期数据缓存
 * 
 */