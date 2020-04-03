package cn.treeshell.recruit.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.recruit.model.Enterprise;
import cn.treeshell.recruit.mapper.EnterpriseMapper;
import cn.treeshell.recruit.service.EnterpriseService;
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
 * 企业 服务实现类
 *
 * @author panjing
 * @since 2020-03-23
 */
@Service
@Transactional
public class EnterpriseServiceImpl extends ServiceImpl<EnterpriseMapper, Enterprise> implements EnterpriseService {

    /**
     * 查看热门企业
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:enterprises:hotList", expire = 3600)
    public List<Enterprise> hotList() {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.eq("is_hot", "1");

        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:enterprises:", expire = 3600)
    public List<Enterprise> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:enterprise:", key = "#id", expire = 3600)
    public Enterprise findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param enterprise
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Enterprise> findSearch(Enterprise enterprise, int page, int size) {

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(enterprise));
    }

    /**
     * 多条件查询
     * @param enterprise
     * @return
     */
    @Override
    public List<Enterprise> findSearch(Enterprise enterprise) {

        return this.baseMapper.selectList(createWrapper(enterprise));
    }

    /**
     * 新增
     * @param enterprise
     */
    @Override
    public void add(Enterprise enterprise) {
        this.baseMapper.insert(enterprise);
    }

    /**
     * 修改
     * @param enterprise
     */
    @Override
    @CacheUpdate(name = "dc-recruit:enterprise:", key = "#enterprise.id", value = "#enterprise")
    public void modify(Enterprise enterprise) {
        this.baseMapper.updateById(enterprise);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-recruit:enterprise:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param enterprise
     * @return
     */
    private QueryWrapper<Enterprise> createWrapper(Enterprise enterprise) {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(enterprise.getId()), "id", enterprise.getId());
        wrapper.like(StrUtil.isNotBlank(enterprise.getName()), "name", enterprise.getName());
        wrapper.like(StrUtil.isNotBlank(enterprise.getSummary()), "summary", enterprise.getSummary());
        wrapper.like(StrUtil.isNotBlank(enterprise.getAddress()), "address", enterprise.getAddress());
        wrapper.like(StrUtil.isNotBlank(enterprise.getLabels()), "labels", enterprise.getLabels());
        wrapper.like(StrUtil.isNotBlank(enterprise.getCoordinate()), "coordinate", enterprise.getCoordinate());
        wrapper.eq(StrUtil.isNotBlank(enterprise.getIsHot()), "is_hot", enterprise.getIsHot());

        return wrapper;
    }
}
