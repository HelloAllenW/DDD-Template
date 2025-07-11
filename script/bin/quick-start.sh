#!/bin/bash

echo "========================================"
echo "快速启动 DDD 模板项目"
echo "========================================"
echo ""

echo "当前使用本地MySQL环境配置..."
echo "请确保："
echo "1. MySQL服务已启动"
echo "2. 已执行数据库初始化脚本"
echo "   mysql -u root -p < we-app/src/main/resources/create_local_db.sql"
echo "3. 已修改 application-local.yml 中的数据库密码"
echo ""

echo "启动应用..."
cd we-app
mvn spring-boot:run

echo ""
echo "启动完成！"
echo "访问地址：http://localhost:8091"
echo "" 