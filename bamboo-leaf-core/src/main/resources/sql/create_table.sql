CREATE TABLE `biz_leaf_relation`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `biz_code`  varchar(255) NOT NULL COMMENT '业务编码规则',
    `rule_code` char(32)     NOT NULL COMMENT '编码规则编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `KEY_BIZ` (`biz_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;


CREATE TABLE `bamboo_leaf_config`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `rule_name`     varchar(300)  NOT NULL COMMENT '编码规则名称',
    `rule_code`     char(32)      NOT NULL COMMENT '编码规则Code',
    `reset_rule`    tinyint(1) NOT NULL COMMENT '重置规则',
    `serial_length` int(11) NOT NULL COMMENT '流水号长度',
    `start_number`  bigint(20) NOT NULL COMMENT '初始化流水号',
    `incr_rule`     int(11) NOT NULL COMMENT '递增规则',
    `format`        varchar(1000) NOT NULL COMMENT '流水号格式',
    `remark`        varchar(300)  NOT NULL DEFAULT '' COMMENT '备注说明',
    `create_time`   datetime      NOT NULL COMMENT '创建时间',
    `update_time`   datetime      NOT NULL COMMENT '修改时间',
    `is_deleted`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

CREATE TABLE `leaf_alloc`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `biz_tag`     varchar(128) NOT NULL,
    `max_id`      bigint(20) NOT NULL DEFAULT '1',
    `step`        bigint(20) NOT NULL,
    `date_str`    varchar(255) DEFAULT NULL,
    `incr`        bigint(20) NOT NULL,
    `description` varchar(255) DEFAULT NULL,
    `update_time` datetime     NOT NULL ON UPDATE CURRENT_TIMESTAMP,
    `is_used`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否用完',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `key_biz_tag` (`biz_tag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;