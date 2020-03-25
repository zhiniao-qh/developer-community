package cn.treeshell.article.service.impl;

import cn.treeshell.article.model.Article;
import cn.treeshell.article.mapper.ArticleMapper;
import cn.treeshell.article.service.ArticleService;
import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    /**
     * 更新文章状态
     * @param id
     */
    @Override
    public void modifyState(String id) {
        this.baseMapper.updateState(id);
    }

    /**
     * 增加点赞数
     * @param id
     */
    @Override
    public void addThumbup(String id) {
        this.baseMapper.addThumbup(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    @Cached(name = "dc-article:articles:", expire = 3600)
    public List<Article> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-article:article:", key = "#id", expire = 3600)
    public Article findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param article
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Article> findSearch(Article article, int page, int size) {

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(article));
    }

    /**
     * 多条件查询
     * @param article
     * @return
     */
    @Override
    public List<Article> findSearch(Article article) {

        return this.baseMapper.selectList(createWrapper(article));
    }

    /**
     * 新增
     * @param article
     */
    @Override
    public void add(Article article) {
        this.baseMapper.insert(article);
    }

    /**
     * 修改
     * @param article
     */
    @Override
    @CacheUpdate(name = "dc-article:article:", key = "#article.id", value = "#article")
    public void modify(Article article) {
        this.baseMapper.updateById(article);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-article:article:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param article
     * @return
     */
    private QueryWrapper<Article> createWrapper(Article article) {
        QueryWrapper<Article> wrapper = new QueryWrapper<>();
        wrapper.like(article.getId() != null, "id", article.getId());
        wrapper.like(article.getColumnid() != null, "columnid", article.getColumnid());
        wrapper.like(article.getChannelid() != null, "channelid", article.getChannelid());
        wrapper.like(article.getTitle() != null, "title", article.getTitle());
        wrapper.eq(article.getIspublic() != null, "ispublic", article.getIspublic());
        wrapper.eq(article.getIstop() != null, "istop", article.getIstop());
        wrapper.eq(article.getState() != null, "state", article.getState());
        wrapper.eq(article.getType() != null, "type", article.getType());

        return wrapper;
    }
}
