package cn.treeshell.article.service;

import cn.treeshell.article.model.Article;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 文章 服务类
 *
 * @author panjing
 * @since 2020-03-22
 */
public interface ArticleService extends IService<Article> {

    void modifyState(String articleId);

    void addThumbup(String articleId);

    List<Article> findAll();

    Article findById(String id);

    IPage<Article> findSearch(Article article, int page, int size);

    List<Article> findSearch(Article article);

    void add(Article article);

    void modify(Article article);

    void remove(String id);
}
