package cn.treeshell.article.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 文章评论（MongoDB）
 *
 * @author panjing
 * @since 2020-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Comment implements Serializable {

    /**
     * ID
     */
    @Id
    private String _id;

    /**
     * 文章ID
     */
    private String articleId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人ID
     */
    private String userId;

    /**
     * 评论ID，为0表示文章的顶级评论
     */
    private String parentId;

    /**
     * 评论日期
     */
    private Date publishDate;
}
