package cn.treeshell.article.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.article.model.Article;
import cn.treeshell.article.service.ArticleService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private HttpServletRequest request;

    /**
     * 更新文章状态
     * @param id
     * @return
     */
    @PutMapping("/examine/{id}")
    public Result modifyState(@PathVariable String id) {
        articleService.modifyState(id);

        return new Result(true, StatusCode.OK, "审核成功");
    }

    /**
     * 增加点赞数
     * @param id
     * @return
     */
    @PutMapping("/thumb_up/{id}")
    public Result thumbUp(@PathVariable String id) {
        articleService.thumbUp(id);
        // TODO: 关于重复点赞的逻辑

        return new Result(true, StatusCode.OK, "点赞成功");
    }

    /**
     * 查询头条文章
     * @return
     */
    @GetMapping("/top")
    public Result top() {

        return new Result(true, StatusCode.OK, "查询成功", articleService.top());
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
    @GetMapping("/{id}")
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
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Article article, @PathVariable int page, @PathVariable int size) {
        IPage<Article> articleIPage = articleService.findSearch(article, page, size);

        return  new Result(true, StatusCode.OK, "查询成功",  new PageResult<Article>(articleIPage.getTotal(), articleIPage.getRecords()) );
    }

    /**
     * 根据条件查询
     * @param article
     * @return
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Article article) {

        return new Result(true, StatusCode.OK, "查询成功", articleService.findSearch(article));
    }

    /**
     * 增加
     * @param article
     */
    @PostMapping
    public Result add(@RequestBody Article article) {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }

        articleService.add(article);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param article
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Article article, @PathVariable String id) {
        article.setId(id);
        articleService.modify(article);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        articleService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

}
