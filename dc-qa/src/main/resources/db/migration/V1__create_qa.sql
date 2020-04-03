CREATE TABLE `problem_label` (
    `problem_id` varchar(20) NOT NULL COMMENT '问题ID',
    `label_id` varchar(20) NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`problem_id`,`label_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `problem` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `title` varchar(100) DEFAULT '' COMMENT '标题',
    `content` text COMMENT '内容',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT NULL COMMENT '修改日期',
    `user_id` varchar(20) DEFAULT '' COMMENT '用户ID',
    `nickname` varchar(100) DEFAULT '' COMMENT '昵称',
    `visits` bigint(20) DEFAULT 0 COMMENT '浏览量',
    `thumb_up` bigint(20) DEFAULT 0 COMMENT '点赞数',
    `reply` bigint(20) DEFAULT 0 COMMENT '回复数',
    `solve` varchar(1) DEFAULT '0' COMMENT '是否解决',
    `reply_name` varchar(100) DEFAULT '' COMMENT '回复人昵称',
    `reply_time` datetime DEFAULT NULL COMMENT '回复日期',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT='问题';

CREATE TABLE `reply` (
     `id` varchar(20) NOT NULL COMMENT '编号',
     `problem_id` varchar(20) DEFAULT '' COMMENT '问题ID',
     `content` text COMMENT '回答内容',
     `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
     `update_time` datetime DEFAULT NULL COMMENT '更新日期',
     `user_id` varchar(20) DEFAULT '' COMMENT '回答人ID',
     `nickname` varchar(100) DEFAULT '' COMMENT '回答人昵称',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答';

CREATE TABLE `user_label` (
    `user_id` varchar(20) NOT NULL COMMENT '用户ID',
    `label_id` varchar(20) NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`user_id`,`label_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;