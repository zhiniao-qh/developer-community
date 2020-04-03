CREATE TABLE `enterprise` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `name` varchar(100) DEFAULT '' COMMENT '企业名称',
    `summary` varchar(1000) DEFAULT '' COMMENT '企业简介',
    `address` varchar(100) DEFAULT '' COMMENT '企业地址',
    `labels` varchar(100) DEFAULT '' COMMENT '标签列表',
    `coordinate` varchar(100) DEFAULT '' COMMENT '坐标',
    `is_hot` varchar(1) DEFAULT '0' COMMENT '是否热门',
    `logo` varchar(100) DEFAULT '' COMMENT 'LOGO',
    `job_count` int DEFAULT 0 COMMENT '职位数',
    `url` varchar(100) DEFAULT '' COMMENT 'URL',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT='企业';

CREATE TABLE `recruit` (
    `id` varchar(20) NOT NULL COMMENT 'ID',
    `job_name` varchar(100) DEFAULT '' COMMENT '职位名称',
    `salary` varchar(100) DEFAULT '' COMMENT '薪资范围',
    `conditions` varchar(100) DEFAULT '' COMMENT '经验要求',
    `education` varchar(100) DEFAULT '' COMMENT '学历要求',
    `type` varchar(1) DEFAULT '0' COMMENT '任职方式',
    `address` varchar(100) DEFAULT '' COMMENT '办公地址',
    `enterprise_id` varchar(20) DEFAULT '' COMMENT '企业ID',
    `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `state` varchar(1) DEFAULT '0' COMMENT '状态',
    `url` varchar(100) DEFAULT '' COMMENT '网址',
    `label` varchar(100) DEFAULT '' COMMENT '标签',
    `content1` varchar(100) DEFAULT '' COMMENT '职位描述',
    `content2` varchar(100) DEFAULT '' COMMENT '职位要求',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT='职位';
