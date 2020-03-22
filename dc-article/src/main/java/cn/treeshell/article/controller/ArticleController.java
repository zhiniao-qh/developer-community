package cn.treeshell.article.controller;

import cn.treeshell.article.model.Article;
import cn.treeshell.article.service.ArticleService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 文章 前端控制器
 *
 * @author panjing
 * @since 2020-03-22
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 更新文章状态
     * @param articleId
     * @return
     */
    @PutMapping(value = "/examine/{articleId}")
    public Result modifyState(@PathVariable String articleId) {
        articleService.modifyState(articleId);

        return new Result(true, StatusCode.OK, "审核成功");
    }

    /**
     * 增加点赞数
     * @param articleId
     * @return
     */
    @PutMapping(value = "/thumbup/{articleId}")
    public Result addThumbup(@PathVariable String articleId) {
        articleService.addThumbup(articleId);

        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", articleService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping(value="/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", articleService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param article 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping(value="/search/{page}/{size}")
    public Result findSearch(@RequestBody Article article, @PathVariable int page, @PathVariable int size) {
        IPage<Article> articleIPage = articleService.findSearch(article, page, size);

        return  new Result(true, StatusCode.OK, "查询成功",  new PageResult<Article>(articleIPage.getTotal(), articleIPage.getRecords()) );
    }

    /**
     * 根据条件查询
     * @param article
     * @return
     */
    @PostMapping(value="/search")
    public Result findSearch( @RequestBody Article article) {

        return new Result(true, StatusCode.OK, "查询成功", articleService.findSearch(article));
    }

    /**
     * 增加
     * @param article
     */
    @PostMapping
    public Result add(@RequestBody Article article) {
        articleService.add(article);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param article
     */
    @PutMapping(value="/{id}")
    public Result modify(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.modify(article);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping(value="/{id}")
    public Result remove(@PathVariable String id) {
        articleService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

}
