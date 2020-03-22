CREATE TABLE `gathering` (
    `id` varchar(20) NOT NULL COMMENT '编号',
    `name` varchar(100) DEFAULT '' COMMENT '活动名称',
    `summary` text COMMENT '大会简介',
    `detail` text COMMENT '详细说明',
    `sponsor` varchar(100) DEFAULT '' COMMENT '主办方',
    `image` varchar(100) DEFAULT '' COMMENT '活动图片',
    `starttime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间',
    `endtime` datetime DEFAULT NULL COMMENT '截止时间',
    `address` varchar(100) DEFAULT '' COMMENT '举办地点',
    `enrolltime` datetime DEFAULT NULL COMMENT '报名截止',
    `state` varchar(1) DEFAULT '0' COMMENT '是否可见',
    `city` varchar(20) DEFAULT '' COMMENT '城市',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动';

CREATE TABLE `usergath` (
    `userid` varchar(20) NOT NULL COMMENT '用户ID',
    `gathid` varchar(20) NOT NULL COMMENT '活动ID',
    `exetime` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '点击时间',
    PRIMARY KEY (`userid`,`gathid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户关注活动';
