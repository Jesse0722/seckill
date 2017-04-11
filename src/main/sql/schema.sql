CREATE  DATABASE seckill;

use seckill;
CREATE TABLE seckill(
  `seckill_id` BIGINT NOT NUll AUTO_INCREMENT COMMENT '商品库存ID',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `number` int NOT NULL COMMENT '库存数量',
  `start_time` TIMESTAMP  NOT NULL COMMENT '秒杀开始时间',
  `end_time`   TIMESTAMP   NOT NULL COMMENT '秒杀结束时间',
  `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (seckill_id),
  key idx_start_time(start_time),
  key idx_end_time(end_time),
  key idx_create_time(create_time)
)ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8 COMMENT='秒杀库存表';

-- 初始化数据
INSERT into seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone6',100,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('800元秒杀ipad',200,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('6600元秒杀mac book pro',300,'2016-01-01 00:00:00','2016-01-02 00:00:00'),
  ('7000元秒杀iMac',400,'2016-01-01 00:00:00','2016-01-02 00:00:00');

-- 秒杀成功明细表
-- 用户登录认证相关信息(简化为手机号)
CREATE TABLE success_killed(
  `seckill_id` BIGINT NOT NULL COMMENT '秒杀商品ID',
  `user_phone` BIGINT NOT NULL COMMENT '用户手机号',
  `state` TINYINT NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  `create_time` TIMESTAMP NOT NULL COMMENT '创建时间',
  PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
  KEY idx_create_time(create_time)
)ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='秒杀成功明细表';

/*增加用户表，用于验证*/
-- CREATE TABLE user(
--   'user_id' int NOT NULL AUTO_INCREAMENT COMMENT '用户id' ,
--   'user_name' varchar(20) NOT NULL COMMENT '用户名',
--   'password' varchar(20) NOT NULL COMMENT '密码',
--   'user_phone' int(11) NOT NULL COMMENT '用户手机号',
--   'email' VARCHAR(50) NOT NULL DEFAULT  COMMENT '邮箱',
--   'create_time' timestamp NOT NULL '创建时间',
--   PRIMARY KEY(user_id),/*联合主键*/
--   key idx_create_time(create_time)
-- )ENGINE=InnoDB AUTO_INCREAMENT=10000 DEFAULT CHARSET=utf-8 COMMENT='用户表';

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT ,
  `user_name` varchar(20) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8;

insert into
  user(user_name,email,password)
VALUES
  ('user',"xieweiba@gmail.com","123456");

CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`role_id`),
  KEY `idx_userrole` (`user_id`),
  CONSTRAINT `idx_userrole` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
)ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;

insert into role (role_name,user_id) VALUES ('user',10000);
insert into role (role_name,user_id) VALUES ('user',10001);
insert into role (role_name,user_id) VALUES ('user',10002);
create table persistent_login
 (
     user_name  VARCHAR(20) not null,
     series     VARCHAR(64) not null ,
     token     VARCHAR(64) not null,
     last_used timestamp not null,
     PRIMARY KEY (`series`)
   )ENGINE=InnoDB DEFAULT CHARSET=utf8;