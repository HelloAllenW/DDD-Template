package cn.helloallen.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wanghailun helloallen.cn
 * @description Redis 连接配置 <a href=
 *              "https://github.com/redisson/redisson/tree/master/redisson-spring-boot-starter">redisson-spring-boot-starter</a>
 * @create 2023-09-03 16:51
 */
/**
 * @Data (Lombok注解)
 * @Data 可以自动生成以下代码：
  public class RedisClientConfigProperties {
      // 自动生成所有getter方法
      public String getHost() { return host; }
      public int getPort() { return port; }
      // ... 其他getter
      
      // 自动生成所有setter方法
      public void setHost(String host) { this.host = host; }
      public void setPort(int port) { this.port = port; }
      // ... 其他setter
      
      // 自动生成toString()、equals()、hashCode()方法
  }
 */
@Data
// prefix: 配置前缀，Spring会自动映射配置文件中的属性
// ignoreInvalidFields: 忽略无效字段，如果配置文件中没有对应的属性，不会抛出异常
@ConfigurationProperties(prefix = "redis.sdk.config", ignoreInvalidFields = true)
public class RedisClientConfigProperties {

  /** host:ip */
  private String host;
  /** 端口 */
  private int port;
  /** 账密 */
  private String password;
  /** 设置连接池的大小，默认为64 */
  private int poolSize = 64;
  /** 设置连接池的最小空闲连接数，默认为10 */
  private int minIdleSize = 10;
  /** 设置连接的最大空闲时间（单位：毫秒），超过该时间的空闲连接将被关闭，默认为10000 */
  private int idleTimeout = 10000;
  /** 设置连接超时时间（单位：毫秒），默认为10000 */
  private int connectTimeout = 10000;
  /** 设置连接重试次数，默认为3 */
  private int retryAttempts = 3;
  /** 设置连接重试的间隔时间（单位：毫秒），默认为1000 */
  private int retryInterval = 1000;
  /** 设置定期检查连接是否可用的时间间隔（单位：毫秒），默认为0，表示不进行定期检查 */
  private int pingInterval = 0;
  /** 设置是否保持长连接，默认为true */
  // HTTP短连接: 请求完成后立即关闭连接。TCP长连接: 保持连接开启，可以处理多个请求
  private boolean keepAlive = true;

}


/**
 * 连接池工作原理：
 */
/**
 * 1. 连接池大小 (poolSize = 64)
 * // 连接池就像一个停车场
  Redis连接池 [最大64个连接]
  ├── 连接1 [使用中]
  ├── 连接2 [空闲]
  ├── 连接3 [空闲]
  ├── ...
  └── 连接64 [空闲]
 */
/**
 * 2. 最小空闲连接数 (minIdleSize = 10)
 * // 保证至少有10个连接随时待命
  连接池状态监控:
  - 总连接数: 64
  - 使用中连接: 30
  - 空闲连接: 34
  - 最小空闲要求: 10 ✓ (满足要求)

  // 如果空闲连接少于10个，会自动创建新连接
  连接池状态监控:
  - 总连接数: 58
  - 使用中连接: 50
  - 空闲连接: 8
  - 最小空闲要求: 10 ✗ (不满足，需要创建2个新连接)
 */
/**
 * 为什么需要连接池？
 * // 没有连接池的情况（性能差）
  public void saveUser(String userId, String userData) {
      // 每次都创建新连接，很慢
      Connection conn = new Connection("redis://localhost:6379");
      conn.set(userId, userData);
      conn.close();  // 关闭连接，资源浪费
  }

  // 使用连接池的情况（性能好）
  public void saveUser(String userId, String userData) {
      // 从连接池获取连接，很快
      Connection conn = connectionPool.getConnection();
      conn.set(userId, userData);
      connectionPool.returnConnection(conn);  // 归还连接，可重复使用
  }
 */

/**
 * 配置参数调优建议：
 * 1. 连接池大小调优
  // 根据业务量调整
  低并发应用: poolSize = 10-20
  中等并发应用: poolSize = 50-100
  高并发应用: poolSize = 100-200
  超高并发应用: poolSize = 200+

  2. 超时时间调优
  // 根据网络环境调整
  内网环境: connectTimeout = 3000-5000ms
  外网环境: connectTimeout = 10000-15000ms
  跨地域: connectTimeout = 15000-30000ms

  3. 重试策略调优
  // 根据可靠性要求调整
  一般应用: retryAttempts = 2-3, retryInterval = 1000ms
  关键应用: retryAttempts = 5, retryInterval = 500ms
 */