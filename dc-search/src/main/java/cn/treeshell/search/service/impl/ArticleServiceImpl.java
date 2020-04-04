package cn.treeshell.search.service.impl;

import cn.treeshell.search.mapper.ArticleMapper;
import cn.treeshell.search.model.Article;
import cn.treeshell.search.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 文章 服务实现类
 *
 * @author  panjing
 * @since  2020-04-03
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 根据关键字搜索并分页
     * @param key
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return articleMapper.findByTitleOrContentLike(key, key, pageable);
    }
}
