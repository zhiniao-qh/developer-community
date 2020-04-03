package cn.treeshell.article.service;

import cn.treeshell.article.model.Comment;

import java.util.List;

/**
 * 文章评论 服务类
 *
 * @author panjing
 * @since 2020-04-03
 */
public interface CommentService {

    void add(Comment comment);

    List<Comment> findByArticleId(String articleId);

    void remove(String id);
}
