package cn.treeshell.recruit.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 职位
 *
 * @author panjing
 * @since 2020-03-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Recruit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 职位名称
     */
    private String jobName;

    /**
     * 薪资范围
     */
    private String salary;

    /**
     * 经验要求
     */
    private String conditions;

    /**
     * 学历要求
     */
    private String education;

    /**
     * 任职方式
     */
    private String type;

    /**
     * 办公地址
     */
    private String address;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 创建日期
     */
    private LocalDateTime createTime;

    /**
     * 状态
     */
    private String state;

    /**
     * 网址
     */
    private String url;

    /**
     * 标签
     */
    private String label;

    /**
     * 职位描述
     */
    private String content1;

    /**
     * 职位要求
     */
    private String content2;
}
