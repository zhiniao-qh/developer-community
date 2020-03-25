package cn.treeshell.article.service.impl;

import cn.treeshell.article.model.Column;
import cn.treeshell.article.mapper.ColumnMapper;
import cn.treeshell.article.service.ColumnService;
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
 * 专栏 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements ColumnService {

    /**
     * 查询全部数据
     * @return
     */
    @Override
    @Cached(name = "dc-article:columns:", expire = 3600)
    public List<Column> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-article:column:", key = "#id", expire = 3600)
    public Column findById(String id) {

        return this.getBaseMapper().selectById(id);
    }

    /**
     * 分页 + 分页多条件查询
     * @param column
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Column> findSearch(Column column, int page, int size) {

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(column));
    }

    /**
     * 多条件查询
     * @param column
     * @return
     */
    @Override
    public List<Column> findSearch(Column column) {

        return this.getBaseMapper().selectList(createWrapper(column));
    }

    /**
     * 新增
     * @param column
     */
    @Override
    public void add(Column column) {
        this.getBaseMapper().insert(column);
    }

    /**
     * 修改
     * @param column
     */
    @Override
    @CacheUpdate(name = "dc-article:column:", key = "#column.id", value = "#column")
    public void modify(Column column) {
        this.getBaseMapper().updateById(column);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-article:column:", key = "#id")
    public void remove(String id) {
        this.getBaseMapper().deleteById(id);
    }

    /**
     * 动态条件构造
     * @param column
     * @return
     */
    private QueryWrapper<Column> createWrapper(Column column) {
        QueryWrapper<Column> wrapper = new QueryWrapper<>();
        wrapper.like(column.getId() != null, "id", column.getId());
        wrapper.like(column.getName() != null, "name", column.getName());
        wrapper.like(column.getSummary() != null, "summary", column.getSummary());
        wrapper.like(column.getUserid() != null, "userid", column.getUserid());
        wrapper.eq(column.getState() != null, "state", column.getState());

        return wrapper;
    }
}
