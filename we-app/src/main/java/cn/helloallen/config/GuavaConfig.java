package cn.helloallen.config;

// Guava提供的缓存接口
import com.google.common.cache.Cache;
// Guava的缓存构建器，用于创建缓存实例
import com.google.common.cache.CacheBuilder;
// Spring注解，将方法返回的对象注册为Spring容器中的Bean
import org.springframework.context.annotation.Bean;
// Spring注解，标识这是一个配置类
import org.springframework.context.annotation.Configuration;

//  Java时间单位枚举
import java.util.concurrent.TimeUnit;

/**
 * @Configuration注解作用：
 * 告诉Spring这是一个配置类（专门用来告诉Spring如何创建和管理对象的类）
 * Spring会扫描这个类中的@Bean方法
 * 相当于传统XML配置文件的Java版本
 */
@Configuration
public class GuavaConfig {

  /**
   * @Bean(name = "cache")
   * 将方法返回的对象注册为Spring Bean
   * Bean的名称为"cache"
   * 其他类可以通过@Autowired或@Resource(name = "cache")注入使用
   */
  @Bean(name = "cache")
  /**
   * 缓存类型：Cache<String, String>
   * 泛型参数: <Key类型, Value类型>。这是一个本地内存缓存，数据存储在JVM内存中
   * @return
   */
  public Cache<String, String> cache() {
    return CacheBuilder.newBuilder()
        .expireAfterWrite(3, TimeUnit.SECONDS) // 数据写入缓存后，3秒后自动过期删除
        .build();
  }

}

/**
 * 该缓存配置特点：
1. 适用场景
- 短期数据缓存: 3秒过期时间很短
- 热点数据: 频繁访问的数据
- 临时存储: 不需要持久化的数据
2. 性能特点
- 本地缓存: 访问速度极快，无网络开销
- 内存存储: 数据存储在JVM堆内存中
- 自动过期: 防止内存泄漏
3. 局限性
- 单机缓存: 只在当前JVM实例中有效
- 重启丢失: 应用重启后缓存数据丢失
- 内存占用: 占用JVM内存空间
 */

// 如何在项目中使用这个缓存：
/**
 * 1. 注入缓存Bean
  @Service
  public class UserService {
      
    @Autowired
    @Qualifier("cache")
    private Cache<String, String> cache;
    
    // 或者使用@Resource
    @Resource(name = "cache")
    private Cache<String, String> cache;
  }
 */

/**
 * 2. 使用缓存
  @Service
  public class UserService {
      
    @Resource(name = "cache")
    private Cache<String, String> cache;
    
    public void cacheUser(String userId, String userData) {
      // 存储数据到缓存
      cache.put(userId, userData);
    }
    
    public String getUser(String userId) {
      // 从缓存获取数据
      return cache.getIfPresent(userId);
    }
    
    public String getUserWithCallback(String userId) {
      // 缓存不存在时，执行回调函数获取数据
      return cache.get(userId, () -> {
        // 从数据库查询用户数据
        return "用户数据从数据库查询";
      });
    }
  }
 */