package cn.helloallen.config;

// Lombok提供的日志注解
import lombok.extern.slf4j.Slf4j;
// 条件注解，只有当容器中不存在指定Bean时才创建
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 启用异步方法支持
import org.springframework.scheduling.annotation.EnableAsync;
// ThreadPoolExecutor: Java并发包中的线程池实现
import java.util.concurrent.*;

// 自动生成日志对象
// private static final Logger log = LoggerFactory.getLogger(ThreadPoolConfig.class);
@Slf4j
// 启用异步方法支持，保证可以在其他类中使用@Async注解
@EnableAsync
// 标识为配置类
@Configuration
// 启用ThreadPoolConfigProperties配置属性类
@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
public class ThreadPoolConfig {

  // @Bean: 将返回的对象注册为Spring Bean
  // 线程池通常全局唯一，不需要特殊命名。当@Bean注解没有指定name参数时，Spring会使用方法名作为Bean的名称
  @Bean
  // @ConditionalOnMissingBean: 只有当容器中不存在ThreadPoolExecutor时才创建
  @ConditionalOnMissingBean(ThreadPoolExecutor.class)
  public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties properties)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException {
    // 实例化策略
    RejectedExecutionHandler handler;
    switch (properties.getPolicy()) {
      case "AbortPolicy":
        handler = new ThreadPoolExecutor.AbortPolicy();
        break;
      case "DiscardPolicy":
        handler = new ThreadPoolExecutor.DiscardPolicy();
        break;
      case "DiscardOldestPolicy":
        handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        break;
      case "CallerRunsPolicy":
        handler = new ThreadPoolExecutor.CallerRunsPolicy();
        break;
      default:
        handler = new ThreadPoolExecutor.AbortPolicy();
        break;
    }
    // 创建线程池
    return new ThreadPoolExecutor(properties.getCorePoolSize(),
        properties.getMaxPoolSize(),
        properties.getKeepAliveTime(),
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(properties.getBlockQueueSize()),
        Executors.defaultThreadFactory(),
        handler);
  }

}

/**
 * 线程池工作原理图解：
 */
/**
 * 线程池执行流程：
                     新任务提交
                          ↓
                   核心线程池是否已满？
                    ↓ 否          是 ↓
               分配核心线程执行      队列是否已满？
                              ↓ 否          是 ↓
                         任务进入队列      线程池是否已满？
                                      ↓ 否          是 ↓
                                 创建非核心线程      执行拒绝策略
                                      ↓
                                   执行任务
 */
/**
 * 1. 核心线程数 (corePoolSize = 20)
 * // 线程池启动后会保持的最小线程数
  线程池状态：
  核心线程：[线程1] [线程2] [线程3] ... [线程20]
  状态：    运行中   空闲    运行中      空闲

  // 即使没有任务，这20个线程也会一直存在
 */
/**
 * 2. 最大线程数 (maxPoolSize = 200)
  // 线程池能创建的最大线程数
  当任务很多时：
  核心线程：[线程1-20]   全部运行中
  队列：    [任务1-5000] 队列满了
  非核心线程：[线程21-200] 动态创建处理任务

  // 总共最多200个线程在工作
 */
/**
 * 3. 空闲线程存活时间 (keepAliveTime = 10秒)
  // 非核心线程的存活时间
  非核心线程生命周期：
  0秒     5秒     10秒    15秒
  |-------|-------|-------|
  创建    空闲    空闲    销毁
          ↑               ↑
          任务完成        超时销毁

  // 核心线程不会被销毁（除非设置allowCoreThreadTimeOut=true）
 */
/**
 * 4. 阻塞队列大小 (blockQueueSize = 5000)
  // 任务队列的最大容量
  队列状态：
  [任务1] [任务2] [任务3] ... [任务5000]
    ↑                            ↑
  队首                         队尾

  // 当队列满时，新任务会触发创建非核心线程或拒绝策略
 */

 /**
  * 实际使用示例
  */
/**
 * 1. 异步方法使用
  @Service
  public class UserService {
      
      @Async  // 使用配置的线程池执行
      public CompletableFuture<String> asyncProcessUser(String userId) {
          log.info("异步处理用户: {}, 线程: {}", userId, Thread.currentThread().getName());
          
          // 模拟耗时操作
          try {
              Thread.sleep(2000);
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
          }
          
          return CompletableFuture.completedFuture("处理完成: " + userId);
      }
  }
 */
/**
 * 2. 手动使用线程池
  @Service
  public class TaskService {
      
      @Autowired
      private ThreadPoolExecutor threadPoolExecutor;
      
      public void submitTask(Runnable task) {
          try {
              threadPoolExecutor.submit(task);
              log.info("任务提交成功，活跃线程数: {}", threadPoolExecutor.getActiveCount());
          } catch (RejectedExecutionException e) {
              log.error("任务被拒绝: {}", e.getMessage());
              // 可以实现降级处理
          }
      }
      
      public void monitorThreadPool() {
          log.info("线程池状态:");
          log.info("核心线程数: {}", threadPoolExecutor.getCorePoolSize());
          log.info("最大线程数: {}", threadPoolExecutor.getMaximumPoolSize());
          log.info("当前线程数: {}", threadPoolExecutor.getPoolSize());
          log.info("活跃线程数: {}", threadPoolExecutor.getActiveCount());
          log.info("队列大小: {}", threadPoolExecutor.getQueue().size());
      }
  }
 */