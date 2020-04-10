package cn.treeshell.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 管理员
 *
 * @author panjing
 * @since 2020-04-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    private String id;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 状态
     */
    private String state;
}
