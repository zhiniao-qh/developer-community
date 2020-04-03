package cn.treeshell.article.service.impl;

import cn.treeshell.article.mapper.CommentMapper;
import cn.treeshell.article.model.Comment;
import cn.treeshell.article.service.CommentService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 文章评论 服务实现类
 *
 * @author panjing
 * @since 2020-04-03
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 文章评论新增
     * @param comment
     */
    @Override
    public void add(Comment comment) {
        IdWorker idWorker = new IdWorker();
        comment.set_id(idWorker.getIdStr());
        comment.setPublishDate(new Date());

        commentMapper.save(comment);
    }

    /**
     * 根据文章 ID 查询评论列表
     * @param articleId
     * @return
     */
    @Override
    public List<Comment> findByArticleId(String articleId) {

        return commentMapper.findByArticleId(articleId);
    }

    /**
     * 根据评论 ID 删除评论
     * @param id
     */
    @Override
    public void remove(String id) {
        commentMapper.deleteById(id);
    }
}
