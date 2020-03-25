package cn.treeshell.recruit.service.impl;

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
    @Cached(name = "dc-recruit:enterprises:hotlist", expire = 3600)
    public List<Enterprise> hotlist() {

        return this.getBaseMapper().selectByIshot("1");
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:enterprises:", expire = 3600)
    public List<Enterprise> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:enterprise:", key = "#id", expire = 3600)
    public Enterprise findById(String id) {

        return this.getBaseMapper().selectById(id);
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

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(enterprise));
    }

    /**
     * 多条件查询
     * @param enterprise
     * @return
     */
    @Override
    public List<Enterprise> findSearch(Enterprise enterprise) {

        return this.getBaseMapper().selectList(createWrapper(enterprise));
    }

    /**
     * 新增
     * @param enterprise
     */
    @Override
    public void add(Enterprise enterprise) {
        this.getBaseMapper().insert(enterprise);
    }

    /**
     * 修改
     * @param enterprise
     */
    @Override
    @CacheUpdate(name = "dc-recruit:enterprise:", key = "#enterprise.id", value = "#enterprise")
    public void modify(Enterprise enterprise) {
        this.getBaseMapper().updateById(enterprise);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-recruit:enterprise:", key = "#id")
    public void remove(String id) {
        this.getBaseMapper().deleteById(id);
    }

    /**
     * 动态条件构造
     * @param enterprise
     * @return
     */
    private QueryWrapper<Enterprise> createWrapper(Enterprise enterprise) {
        QueryWrapper<Enterprise> wrapper = new QueryWrapper<>();
        wrapper.like(enterprise.getId() != null, "id", enterprise.getId());
        wrapper.like(enterprise.getName() != null, "name", enterprise.getName());
        wrapper.like(enterprise.getSummary() != null, "summary", enterprise.getSummary());
        wrapper.like(enterprise.getAddress() != null, "address", enterprise.getAddress());
        wrapper.like(enterprise.getLabels() != null, "labels", enterprise.getLabels());
        wrapper.like(enterprise.getCoordinate() != null, "coordinate", enterprise.getCoordinate());
        wrapper.eq(enterprise.getId() != null, "ishot", enterprise.getIshot());

        return wrapper;
    }
}
