CREATE TABLE `label` (
    `id` varchar(20) NOT NULL COMMENT '标签ID',
    `label_name` varchar(100) DEFAULT '' COMMENT '标签名称',
    `state` varchar(1) DEFAULT '0' COMMENT '状态',
    `count` bigint(20) DEFAULT 0 COMMENT '使用数量',
    `recommend` varchar(1) DEFAULT '0' COMMENT '是否推荐',
    `fans` bigint(20) DEFAULT 0 COMMENT '粉丝数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT='标签';

CREATE TABLE `user_label` (
    `user_id` varchar(20) NOT NULL,
    `label_id` varchar(20) NOT NULL,
    PRIMARY KEY (`user_id`,`label_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
