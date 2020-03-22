CREATE TABLE `pl` (
    `problemid` varchar(20) NOT NULL COMMENT '问题ID',
    `labelid` varchar(20) NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`problemid`,`labelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `problem` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `title` varchar(100) DEFAULT '' COMMENT '标题',
    `content` text COMMENT '内容',
    `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `updatetime` datetime DEFAULT NULL COMMENT '修改日期',
    `userid` varchar(20) DEFAULT '' COMMENT '用户ID',
    `nickname` varchar(100) DEFAULT '' COMMENT '昵称',
    `visits` bigint(20) DEFAULT 0 COMMENT '浏览量',
    `thumbup` bigint(20) DEFAULT 0 COMMENT '点赞数',
    `reply` bigint(20) DEFAULT 0 COMMENT '回复数',
    `solve` varchar(1) DEFAULT '0' COMMENT '是否解决',
    `replyname` varchar(100) DEFAULT '' COMMENT '回复人昵称',
    `replytime` datetime DEFAULT NULL COMMENT '回复日期',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='问题';

CREATE TABLE `reply` (
     `id` varchar(20) NOT NULL COMMENT '编号',
     `problemid` varchar(20) DEFAULT '' COMMENT '问题ID',
     `content` text COMMENT '回答内容',
     `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
     `updatetime` datetime DEFAULT NULL COMMENT '更新日期',
     `userid` varchar(20) DEFAULT '' COMMENT '回答人ID',
     `nickname` varchar(100) DEFAULT '' COMMENT '回答人昵称',
     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='回答';

CREATE TABLE `ul` (
    `uid` varchar(20) NOT NULL COMMENT '用户ID',
    `lid` varchar(20) NOT NULL COMMENT '标签ID',
    PRIMARY KEY (`uid`,`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;