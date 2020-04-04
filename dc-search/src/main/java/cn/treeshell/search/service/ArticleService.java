package cn.treeshell.search.service;

import cn.treeshell.search.model.Article;
import org.springframework.data.domain.Page;

/**
 * 文章 服务接口类
 * @author  panjing
 * @since  2020-04-03
 */
public interface ArticleService {

    Page<Article> findByKey(String key, int page, int size);
}
