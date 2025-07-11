-- 创建本地开发数据库
CREATE DATABASE IF NOT EXISTS we_local CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE we_local;

-- 创建用户订单表（简化版，不分库分表）
CREATE TABLE IF NOT EXISTS user_order (
    id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL COMMENT '用户ID',
    order_no VARCHAR(32) NOT NULL COMMENT '订单号',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_user_order_user_id (user_id),
    INDEX idx_user_order_order_no (order_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户订单表';

-- 插入测试数据
INSERT INTO user_order (user_id, order_no) VALUES 
(1001, 'ORDER_001'),
(1002, 'ORDER_002'),
(1003, 'ORDER_003'); 