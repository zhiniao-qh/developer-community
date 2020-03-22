package cn.treeshell.base.service.impl;

import cn.treeshell.base.model.Label;
import cn.treeshell.base.mapper.LabelMapper;
import cn.treeshell.base.service.LabelService;
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
     * 分页 + 多条件查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Label> findSearch(Label label, int page, int size) {

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(label));
    }

    /**
     * 多条件查询
     * @param label
     * @return
     */
    @Override
    public List<Label> findSearch(Label label) {

        return this.getBaseMapper().selectList(createWrapper(label));
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<Label> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public Label findById(String id) {

        return this.getBaseMapper().selectById(id);
    }

    /**
     * 新增
     * @param label
     */
    @Override
    public void add(Label label) {
        this.getBaseMapper().insert(label);
    }

    /**
     * 修改
     * @param label
     */
    @Override
    public void modify(Label label) {
        this.getBaseMapper().updateById(label);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void remove(String id) {
        this.getBaseMapper().deleteById(id);
    }

    /**
     * 动态条件构造
     * @param label
     * @return
     */
    private QueryWrapper<Label> createWrapper(Label label) {
        QueryWrapper<Label> wrapper = new QueryWrapper<>();
        wrapper.like(label.getLabelname() != null, "labelname", label.getLabelname());
        wrapper.like(label.getState() != null, "state", label.getState());

        return wrapper;
    }
}
