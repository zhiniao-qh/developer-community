package cn.treeshell.article.mapper;

import cn.treeshell.article.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 文章评论 Mapper 接口
 *
 * @author panjing
 * @since 2020-4-3
 */
public interface CommentMapper extends MongoRepository<Comment, String> {

    List<Comment> findByArticleId(String articleId);
}
