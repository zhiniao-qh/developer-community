package cn.treeshell.base.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 标签
 *
 * @author panjing
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    private String id;

    /**
     * 标签名称
     */
    private String labelName;

    /**
     * 状态
     */
    private String state;

    /**
     * 使用数量
     */
    private Long count;

    /**
     * 是否推荐
     */
    private String recommend;

    /**
     * 粉丝数
     */
    private Long fans;
}
