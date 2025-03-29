# bamboo-leaf

基于美团Leaf改造的自定义规则叫号器

> GET 请求 http://localhost:1001/bamboo/leaf/get/{key} 方法即可获取业务号段

## 表结构设计

#### 编码规则配置表

```sql
CREATE TABLE `leaf_config`
(
    `id`            bigint(20)    NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `rule_name`     varchar(300)  NOT NULL COMMENT '编码规则名称',
    `rule_code`     char(32)      NOT NULL COMMENT '编码规则Code',
    `reset_rule`    tinyint(1)    NOT NULL COMMENT '重置规则',
    `serial_length` int(11)       NOT NULL COMMENT '流水号长度',
    `start_number`  bigint(20)    NOT NULL COMMENT '初始化流水号',
    `incr_rule`     int(11)       NOT NULL COMMENT '递增规则',
    `format`        varchar(1000) NOT NULL COMMENT '流水号格式',
    `remark`        varchar(300)  NOT NULL DEFAULT '' COMMENT '备注说明',
    `create_time`   datetime      NOT NULL COMMENT '创建时间',
    `update_time`   datetime      NOT NULL COMMENT '修改时间',
    `is_deleted`    tinyint(1)    NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
```

#### 编码执行配置

```sql
CREATE TABLE `leaf_alloc`
(
    `id`          bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `biz_tag`     varchar(128) NOT NULL,
    `max_id`      bigint(20)   NOT NULL DEFAULT '1',
    `step`        bigint(20)   NOT NULL,
    `date_str`    varchar(255) NOT NULL DEFAULT '',
    `incr`        bigint(20)   NOT NULL,
    `description` varchar(255) NOT NULL DEFAULT '',
    `update_time` datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `is_used`     tinyint(1)   NOT NULL DEFAULT '0' COMMENT '是否用完',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `key_biz_tag` (`biz_tag`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
```

#### 业务关联配置

```sql
CREATE TABLE `leaf_biz_relation`
(
    `id`        bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `biz_code`  varchar(255) NOT NULL COMMENT '业务编码规则',
    `rule_code` char(32)     NOT NULL COMMENT '编码规则编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `KEY_BIZ` (`biz_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;
```
