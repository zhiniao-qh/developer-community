package cn.treeshell.search.mapper;

import cn.treeshell.search.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 文章搜索 Mapper 接口
 *
 * @author panjing
 * @since 2020-04-03
 */
public interface ArticleMapper extends ElasticsearchRepository<Article, String> {

    Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}
