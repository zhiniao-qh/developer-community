package cn.treeshell.base.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.base.model.Label;
import cn.treeshell.base.mapper.LabelMapper;
import cn.treeshell.base.service.LabelService;
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
 * 标签 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    /**
     * 推荐标签列表
     * @return
     */
    @Override
    @Cached(name = "dc-base:users:recommend:", expire = 3600)
    public List<Label> findTopList(String recommend) {
        QueryWrapper<Label> wrapper =  new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(recommend), "recommend", recommend);


        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 有效标签列表
     * @return
     */
    @Override
    @Cached(name = "dc-base:users:state:", expire = 3600)
    public List<Label> findByState(String state) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(state), "state", state);

        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 分页 + 多条件查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Label> findSearch(Label label, int page, int size) {

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(label));
    }

    /**
     * 多条件查询
     * @param label
     * @return
     */
    @Override
    public List<Label> findSearch(Label label) {

        return this.baseMapper.selectList(createWrapper(label));
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    @Cached(name = "dc-base:users:", expire = 3600)
    public List<Label> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     *
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-base:user:", key = "#id", expire = 3600)
    public Label findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 新增
     * @param label
     */
    @Override
    public void add(Label label) {
        this.baseMapper.insert(label);
    }

    /**
     * 修改
     * @param label
     */
    @Override
    @CacheUpdate(name = "dc-base:user:", key = "#label.id", value = "#label")
    public void modify(Label label) {
        this.baseMapper.updateById(label);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-base:user:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param label
     * @return
     */
    private QueryWrapper<Label> createWrapper(Label label) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(label.getLabelName()), "label_name", label.getLabelName());
        wrapper.like(StrUtil.isNotBlank(label.getState()), "state", label.getState());

        return wrapper;
    }
}
