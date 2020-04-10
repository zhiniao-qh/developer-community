package cn.treeshell.user.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 关注
 *
 * @author panjing
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Follow implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 被关注用户ID
     */
    private String followUser;
}
