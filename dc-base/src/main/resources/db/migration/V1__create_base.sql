CREATE TABLE `city` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `name` varchar(20) DEFAULT '' COMMENT '城市名称',
    `ishot` varchar(1) DEFAULT '0' COMMENT '是否热门',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='城市';

CREATE TABLE `label` (
    `id` varchar(20) NOT NULL COMMENT '标签ID',
    `labelname` varchar(100) DEFAULT '' COMMENT '标签名称',
    `state` varchar(1) DEFAULT '0' COMMENT '状态',
    `count` bigint(20) DEFAULT 0 COMMENT '使用数量',
    `recommend` varchar(1) DEFAULT '0' COMMENT '是否推荐',
    `fans` bigint(20) DEFAULT 0 COMMENT '粉丝数',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签';

CREATE TABLE `ul` (
    `userid` varchar(20) NOT NULL,
    `labelid` varchar(20) NOT NULL,
    PRIMARY KEY (`userid`,`labelid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
