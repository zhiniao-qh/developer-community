package cn.treeshell.spit.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 吐槽
 *
 * @author panjing
 * @since 2020-3-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Spit implements Serializable {

    /**
     * ID
     */
    @Id
    private String _id;

    /**
     * 吐槽内容
     */
    private String content;

    /**
     * 发布人吐槽日期
     */
    private Date publishTime;

    /**
     * 发布人ID
     */
    private String userId;

    /**
     * 发布人昵称
     */
    private String nickname;

    /**
     * 浏览量
     */
    private Integer visits;

    /**
     * 点赞数
     */
    private Integer thumbUp;

    /**
     * 分享数
     */
    private Integer share;

    /**
     * 回复数
     */
    private Integer comment;

    /**
     * 状态，是否可见
     */
    private String state;

    /**
     * 上级ID
     */
    private String parentId;
}
