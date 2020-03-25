package cn.treeshell.gathering.service.impl;

import cn.treeshell.gathering.mapper.GatheringMapper;
import cn.treeshell.gathering.model.Gathering;
import cn.treeshell.gathering.service.GatheringService;
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
 * 活动 服务实现类
 *
 * @author panjing
 * @since 2020-03-23
 */
@Service
@Transactional
public class GatheringServiceImpl extends ServiceImpl<GatheringMapper, Gathering> implements GatheringService {

    /**
     * 查询全部数据
     * @return
     */
    @Override
    @Cached(name = "dc-gathering:gatherings:", expire = 3600)
    public List<Gathering> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-gathering:gathering:", key = "#id", expire = 3600)
    public Gathering findById(String id) {

        return this.getBaseMapper().selectById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param gathering
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Gathering> findSearch(Gathering gathering, int page, int size) {

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(gathering));
    }

    /**
     * 多条件查询
     * @param gathering
     * @return
     */
    @Override
    public List<Gathering> findSearch(Gathering gathering) {

        return this.getBaseMapper().selectList(createWrapper(gathering));
    }

    /**
     * 新增
     * @param gathering
     */
    @Override
    public void add(Gathering gathering) {
        this.getBaseMapper().insert(gathering);
    }

    /**
     * 修改
     * @param gathering
     */
    @Override
    @CacheUpdate(name = "dc-gathering:gathering:", key = "#gathering.id", value = "#gathering")
    public void modify(Gathering gathering) {
        this.getBaseMapper().updateById(gathering);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-gathering:gathering:", key = "#id")
    public void delete(String id) {
        this.getBaseMapper().deleteById(id);
    }

    /**
     * 动态条件构造
     * @param gathering
     * @return
     */
    private QueryWrapper<Gathering> createWrapper(Gathering gathering) {
        QueryWrapper<Gathering> wrapper = new QueryWrapper<>();
        wrapper.like(gathering.getId() != null, "id", gathering.getId());
        wrapper.like(gathering.getName() != null, "name", gathering.getName());
        wrapper.like(gathering.getSummary() != null, "summary", gathering.getSummary());
        wrapper.like(gathering.getSponsor() != null, "sponsor", gathering.getSponsor());
        wrapper.like(gathering.getAddress() != null, "address", gathering.getAddress());
        wrapper.like(gathering.getCity() != null, "city", gathering.getCity());
        wrapper.eq(gathering.getState() != null, "state", gathering.getState());

        return wrapper;
    }
}
