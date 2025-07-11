1. 什么叫配置类？配置类和其他类有什么区别？
**（1）什么是配置类？**
配置类就是专门用来告诉Spring如何创建和管理对象的类。

**（2）传统方式 vs 配置类方式**
- 传统方式（没有Spring时）：
```
// 传统方式：手动创建对象
public class UserService {
    public void doSomething() {
        // 手动创建缓存对象
        Cache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
        
        // 使用缓存
        cache.put("key", "value");
    }
}
```

- 配置类方式（使用Spring）：
```
@Configuration  // 这是配置类
public class GuavaConfig {
    
    @Bean  // 告诉Spring：请帮我创建这个对象
    public Cache<String, String> cache() {
        return CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
    }
}

@Service  // 这是业务类
public class UserService {
    
    @Autowired  // 告诉Spring：请把缓存对象给我
    private Cache<String, String> cache;
    
    public void doSomething() {
        // 直接使用，不需要手动创建
        cache.put("key", "value");
    }
}
```

**（3）配置类的作用**
- 集中管理对象创建：统一定义如何创建对象
- 解耦合：业务类不需要知道对象如何创建
- 便于修改：只需要在配置类中修改，不影响业务代码

**（4）服务启动时，配置类是怎么被自动加载的？**
Spring Boot启动流程  
第一步：启动类扫描
```
@SpringBootApplication  // 这个注解包含了@ComponentScan
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class);  // 启动Spring
    }
}
```

第二步：包扫描过程  
```
启动时Spring会扫描包路径：
cn.helloallen/           # 从Application所在包开始
├── Application.java     # 启动类
├── config/             # 配置包
│   └── GuavaConfig.java # 发现@Configuration注解
├── service/            # 业务包
│   └── UserService.java # 发现@Service注解
└── ...
```

第三步：自动加载过程
```
// Spring内部大致流程（简化版）
public class SpringBootApplication {
    public void start() {
        // 1. 扫描所有带@Configuration的类
        List<Class> configClasses = scanForConfigurationClasses();
        
        // 2. 加载配置类
        for (Class configClass : configClasses) {
            loadConfiguration(configClass);  // 加载GuavaConfig
        }
        
        // 3. 执行@Bean方法，创建对象
        for (Method method : getBeansMethod(configClass)) {
            Object bean = method.invoke();  // 调用cache()方法
            container.put("cache", bean);   // 存入容器
        }
    }
}
```

2. Spring容器的Bean指的是什么？
Bean就是Spring管理的对象，就像一个**对象仓库**。
（1）形象的比喻
```
// Spring容器就像一个超大的HashMap
Map<String, Object> springContainer = new HashMap<>();

// 启动时，Spring会把所有Bean放入这个容器
springContainer.put("cache", new Cache<String, String>());
springContainer.put("userService", new UserService());
springContainer.put("redisTemplate", new RedisTemplate());
```

（2）具体例子解释
- 创建Bean的过程：
```
@Configuration
public class GuavaConfig {
    
    @Bean(name = "cache")  // 告诉Spring：创建一个名为"cache"的Bean
    public Cache<String, String> cache() {
        return CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
    }
}
```

- Spring内部操作（简化版）：
```
// Spring启动时的内部操作
public class SpringContainer {
    private Map<String, Object> beans = new HashMap<>();
    
    public void loadBeans() {
        // 1. 找到GuavaConfig类
        GuavaConfig config = new GuavaConfig();
        
        // 2. 调用cache()方法
        Cache<String, String> cacheObject = config.cache();
        
        // 3. 存入容器，名称为"cache"
        beans.put("cache", cacheObject);
    }
    
    public Object getBean(String name) {
        return beans.get(name);  // 根据名称获取Bean
    }
}
```

- 使用Bean的过程：
```
@Service
public class UserService {
    
    @Autowired  // 告诉Spring：从容器中找到cache对象给我
    private Cache<String, String> cache;
    
    // Spring会自动执行类似这样的操作：
    // this.cache = (Cache<String, String>) springContainer.getBean("cache");
}
```

（3）为什么要用这种方式？
- 不用Spring（传统方式）：
```
public class UserService {
    private Cache<String, String> cache;
    
    public UserService() {
        // 每个地方都要自己创建
        this.cache = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
    }
}

public class OrderService {
    private Cache<String, String> cache;
    
    public OrderService() {
        // 又要创建一遍，重复代码
        this.cache = CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
    }
}
```

- 使用Spring（现代方式）：
```
// 配置类：统一创建
@Configuration
public class GuavaConfig {
    @Bean
    public Cache<String, String> cache() {
        return CacheBuilder.newBuilder()
            .expireAfterWrite(3, TimeUnit.SECONDS)
            .build();
    }
}

// 业务类：直接使用
@Service
public class UserService {
    @Autowired
    private Cache<String, String> cache;  // 自动注入，共享同一个对象
}

@Service
public class OrderService {
    @Autowired
    private Cache<String, String> cache;  // 自动注入，共享同一个对象
}
```

（4）总结
- 配置类：专门告诉Spring如何创建对象的类
- 自动加载：Spring启动时自动扫描@Configuration类并执行@Bean方法
- Bean容器：Spring内部的对象仓库，存储所有创建好的对象
- 依赖注入：Spring自动从容器中取出对象，注入到需要的地方
这就是Spring的控制反转（IoC）和依赖注入（DI）的核心概念：
- - 控制反转：对象的创建权交给Spring
- - 依赖注入：Spring自动把对象注入到需要的地方

