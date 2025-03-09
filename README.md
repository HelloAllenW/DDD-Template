# 我们爱运动 - server

## 技术相关细节

### 1. dubbo与zookeeper的使用
- （1）背景：集中式转变为分布式开发后，应用之间的关系变得异常复杂，这时通过Dubbo进行服务路由等功能实现对分布式服务的治理。
- （2）dubbo架构：
  - dubbo包括：Provider(暴露服务的服务提供方)、Consumer(调用远程服务的服务消费方)、Register(服务注册与发现的注册中心，比如zookeeper)、Monitor(统计服务的调用次数和调用时间的监控中心)、Container(服务运行容器)
  - dubbo其实很像生产者-消费者模型。只是在这种模型上，加上了注册中心和监控中心，用于管理提供方提供的url，以及管理整个过程。
  - 整个发布-订阅的过程非常简单：启动容器，加载，运行服务提供者；服务提供者在启动时，在注册中心发布注册自己提供的服务；服务消费者在启动时，在注册中心订阅自己所需的服务。
- （3）自己实现一个dubbo：[Dubbo 一篇文章就够了：从入门到实战](https://segmentfault.com/a/1190000019896723)
- （4）本地使用Dubbo admin（是用于管理Dubbo服务的基于Web的管理工具，Dubbo Admin通过连接Zookeeper来获取注册的服务信息）
  - 启动zk服务端：zkServer.cmd
  - 启动客户端:zkCli.cmd
  - 启动dubbo admin：D:\dubbo-admin-develop\dubbo-admin-server\target\dubbo-admin-server-0.7.0-SNAPSHOT.jar
  - 参考：[Dubbo admin 在Windows下的安装和服务发现](https://www.cnblogs.com/lippon/p/14182905.html)