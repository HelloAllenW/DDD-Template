# DDD-Template

## 项目简介

本项目为基于领域驱动设计（DDD）的微服务架构模板，集成了常用的中间件（如 Nacos、Redis、RocketMQ、XXL-Job 等），适用于企业级应用的快速开发和架构实践。项目采用多模块结构，涵盖 API、应用、领域、基础设施、触发器、类型定义等典型 DDD 分层。

## 目录结构

```
.
├── we-api/            # API 层，定义接口与数据模型
├── we-app/            # 应用层，应用服务、配置、启动入口
├── we-domain/         # 领域层，核心业务逻辑与领域模型
├── we-infrastructure/ # 基础设施层，持久化、第三方服务等
├── we-trigger/        # 触发层，HTTP/RPC 接口、定时任务、监听器等
├── we-types/          # 通用类型、异常、响应模型等
├── docs/              # 运维、部署、SQL 脚本等文档
├── scaffold-std/      # 脚手架模板
├── pom.xml            # Maven 父工程配置
└── README.md          # 项目说明文档
```
依赖关系符合DDD原则的：外层依赖内层。同时领域层不依赖基础设施层，保持了领域模型的纯净性。

## 启动方式

1. 启动 Nacos（配置中心）
2. 启动 Redis（缓存服务）
3. 启动 Dubbo（RPC 服务框架）
4. 启动 RocketMQ（消息队列）
5. 启动 XXL-Job（分布式任务调度）

> 各中间件的 Docker Compose 配置、初始化脚本等可在 `docs/dev-ops/environment/` 下找到。

### 启动应用

以 we-app 为例：

```bash
cd we-app
# 构建
mvn clean package
# 启动
java -jar target/we-app-*.jar
```

## 依赖环境

- JDK 8+
- Maven 3.6+
- Docker（推荐用于本地中间件环境）
- Nacos
- Redis
- RocketMQ
- XXL-Job
- Dubbo

## 主要模块说明

- **we-api**：对外暴露的接口定义及数据传输对象（DTO/VO）。
- **we-app**：应用服务层，负责业务编排、配置、启动等。
- **we-domain**：领域模型与核心业务逻辑，聚合根、实体、值对象、领域服务等。
- **we-infrastructure**：数据持久化、第三方服务接入、缓存等基础设施实现。
- **we-trigger**：HTTP/RPC 接口、消息监听、定时任务等触发器。
- **we-types**：通用类型、异常定义、统一响应模型等。

## 文档与运维

- `docs/dev-ops/`：包含 Docker Compose、启动/停止脚本、SQL 脚本等。
- 各中间件的配置、初始化脚本详见对应子目录。

## 贡献与开发

1. Fork 本仓库并创建分支
2. 提交代码并发起 Pull Request
3. 代码需通过单元测试和代码规范检查