CREATE TABLE `article` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `columnid` varchar(20) DEFAULT '' COMMENT '专栏ID',
    `userid` varchar(20) DEFAULT '' COMMENT '用户ID',
    `title` varchar(100) DEFAULT '' COMMENT '标题',
    `content` text COMMENT '文章正文',
    `image` varchar(100) DEFAULT '' COMMENT '文章封面',
    `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '发表日期',
    `updatetime` datetime DEFAULT NULL COMMENT '修改日期',
    `ispublic` varchar(1) DEFAULT '0' COMMENT '是否公开',
    `istop` varchar(1) DEFAULT '0' COMMENT '是否置顶',
    `visits` int(20) DEFAULT 0 COMMENT '浏览量',
    `thumbup` int(20) DEFAULT 0 COMMENT '点赞数',
    `comment` int(20) DEFAULT 0 COMMENT '评论数',
    `state` varchar(1) DEFAULT '0' COMMENT '审核状态',
    `channelid` varchar(20) DEFAULT '' COMMENT '所属频道',
    `url` varchar(100) DEFAULT '' COMMENT 'URL',
    `type` varchar(1) DEFAULT '0' COMMENT '类型',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文章';

CREATE TABLE `channel` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `name` varchar(100) DEFAULT '' COMMENT '频道名称',
    `state` varchar(1) DEFAULT '0' COMMENT '状态',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='频道';

CREATE TABLE `column` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `name` varchar(100) DEFAULT '' COMMENT '专栏名称',
    `summary` varchar(1000) DEFAULT '' COMMENT '专栏简介',
    `userid` varchar(20) DEFAULT '' COMMENT '用户ID',
    `createtime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '申请日期',
    `checktime` datetime DEFAULT NULL COMMENT '审核日期',
    `state` varchar(1) DEFAULT '0' COMMENT '状态',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专栏';