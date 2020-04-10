CREATE TABLE `admin` (
     `id` varchar(20) NOT NULL COMMENT 'ID',
     `login_name` varchar(100) NOT NULL COMMENT '登陆名称',
     `password` varchar(100) NOT NULL COMMENT '密码',
     `state` varchar(1) DEFAULT '0' COMMENT '状态',
     PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '管理员';

CREATE TABLE `follow` (
    `user_id` varchar(20) NOT NULL COMMENT '用户ID',
    `follow_user` varchar(20) NOT NULL COMMENT '被关注用户ID',
  PRIMARY KEY (`user_id`,`follow_user`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `password` varchar(100) DEFAULT '' COMMENT '密码',
    `nickname` varchar(100) DEFAULT '' COMMENT '昵称',
    `sex` varchar(2) DEFAULT NULL COMMENT '性别',
    `birthday` datetime DEFAULT NULL COMMENT '出生年月日',
    `avatar` varchar(100) DEFAULT '' COMMENT '头像',
    `mobile` varchar(20) DEFAULT NULL COMMENT '手机号',
    `email` varchar(100) DEFAULT '' COMMENT 'E-Mail',
    `register_date` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
    `update_date` datetime DEFAULT NULL COMMENT '修改日期',
    `last_login_date` datetime DEFAULT NULL COMMENT '最后登陆日期',
    `interest` varchar(100) DEFAULT '' COMMENT '兴趣',
    `personality` varchar(100) DEFAULT '' COMMENT '个性',
    `fans_count` int(20) DEFAULT 0 COMMENT '粉丝数',
    `follow_count` int(20) DEFAULT 0 COMMENT '关注数',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT = '用户';