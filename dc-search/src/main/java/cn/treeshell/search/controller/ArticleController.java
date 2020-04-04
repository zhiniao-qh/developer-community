package cn.treeshell.search.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.search.model.Article;
import cn.treeshell.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * 文章 前端控制器
 *
 * @author  panjing
 * @since  2020-04-03
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 根据关键字搜索并分页
     * @param key
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{key}/{page}/{size}")
    public Result findByKey(@PathVariable String key, @PathVariable int page, @PathVariable int size) {
        Page<Article> articlePage = articleService.findByKey(key, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(articlePage.getTotalElements(), articlePage.getContent()));
    }

}
