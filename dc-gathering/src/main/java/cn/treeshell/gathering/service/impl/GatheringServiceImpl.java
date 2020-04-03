package cn.treeshell.gathering.service.impl;

import cn.hutool.core.util.StrUtil;
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

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-gathering:gathering:", key = "#id", expire = 3600)
    public Gathering findById(String id) {

        return this.baseMapper.selectById(id);
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

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(gathering));
    }

    /**
     * 多条件查询
     * @param gathering
     * @return
     */
    @Override
    public List<Gathering> findSearch(Gathering gathering) {

        return this.baseMapper.selectList(createWrapper(gathering));
    }

    /**
     * 新增
     * @param gathering
     */
    @Override
    public void add(Gathering gathering) {
        this.baseMapper.insert(gathering);
    }

    /**
     * 修改
     * @param gathering
     */
    @Override
    @CacheUpdate(name = "dc-gathering:gathering:", key = "#gathering.id", value = "#gathering")
    public void modify(Gathering gathering) {
        this.baseMapper.updateById(gathering);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-gathering:gathering:", key = "#id")
    public void delete(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param gathering
     * @return
     */
    private QueryWrapper<Gathering> createWrapper(Gathering gathering) {
        QueryWrapper<Gathering> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(gathering.getId()), "id", gathering.getId());
        wrapper.like(StrUtil.isNotBlank(gathering.getName()), "name", gathering.getName());
        wrapper.like(StrUtil.isNotBlank(gathering.getSummary()), "summary", gathering.getSummary());
        wrapper.like(StrUtil.isNotBlank(gathering.getSponsor()), "sponsor", gathering.getSponsor());
        wrapper.like(StrUtil.isNotBlank(gathering.getAddress()), "address", gathering.getAddress());
        wrapper.like(StrUtil.isNotBlank(gathering.getCity()), "city", gathering.getCity());
        wrapper.eq(StrUtil.isNotBlank(gathering.getState()), "state", gathering.getState());

        return wrapper;
    }
}
