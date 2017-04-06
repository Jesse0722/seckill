CREATE  DATABASE seckill;

use seckill;
CREATE TABLE seckill(
'seckill_id' bigint NOT NULL AUTO_INCREAMENT '商品库存id',
'name' varchar(120) NOT NULL COMMENT '商品名称',
'start_time' timestamp NOT NULL COMMENT '开始时间',
'end_time' timestamp NOT NULL COMMENT '结束时间',
'create_time' timestamp NOT NULL COMMENT '创建时间',
PRIMARY KEY (seckill_id),
key idx_start_time(start_time),
key idx_end_time(end_time),
key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREAMENT=1000 DEFAULT CHARSET=utf-8 COMMENT='秒杀库存表';

insert into
  seckill(name,number,start_time,end_time)
VALUES
  ('1000元秒杀iphone6',100,'2017-03-27 00:00:00','2017-03-30 00:00:00'),
  ('500元秒杀ipad2',200,'2017-03-27 00:00:00','2017-03-30 00:00:00'),
  ('300元秒杀iwatch',300,'2017-03-27 00:00:00','2017-03-30 00:00:00'),
  ('20元秒杀iphone5',400,'2017-03-27 00:00:00','2017-03-30 00:00:00');

CREATE TABLE succuss_killed(
  'seckill_id' bigint NOT NULL COMMENT '秒杀商品id',
  'user_phone' bigint NOT NULL COMMENT '用户手机号',
  'state' tinyint NOT NULL DEFAULT -1 COMMENT '状态标识:-1:无效 0:成功 1:已付款 2:已发货',
  'create_time' timestamp NOT NULL '创建时间',
  PRIMARY KEY(seckill_id,user_phone),/*联合主键*/
  key idx_create_time(create_time)
)ENGINE=InnoDB AUTO_INCREAMENT=1000 DEFAULT CHARSET=utf-8 COMMENT='秒杀库存表';

