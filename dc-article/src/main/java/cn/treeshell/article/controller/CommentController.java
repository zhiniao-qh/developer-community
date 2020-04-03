package cn.treeshell.article.controller;

import cn.treeshell.article.model.Comment;
import cn.treeshell.article.service.CommentService;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章评论 前端控制器
 *
 * @author panjing
 * @since 2020-04-03
 */
@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 文章评论提交
     * @param comment
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Comment comment) {
        commentService.add(comment);

        return new Result(true, StatusCode.OK, "提交成功");
    }

    /**
     * 根据文章 ID 查询评论列表
     * @param articleId
     * @return
     */
    @GetMapping("/article/{articleId}")
    public Result findByArticleId(@PathVariable String articleId) {

        return new Result(true, StatusCode.OK, "查询成功", commentService.findByArticleId(articleId));
    }

    /**
     * 根据评论 ID 删除评论
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        commentService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
