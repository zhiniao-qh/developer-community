package cn.treeshell.recruit.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.recruit.model.Recruit;
import cn.treeshell.recruit.mapper.RecruitMapper;
import cn.treeshell.recruit.service.RecruitService;
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
 * 职位 服务实现类
 *
 * @author panjing
 * @since 2020-03-23
 */
@Service
@Transactional
public class RecruitServiceImpl extends ServiceImpl<RecruitMapper, Recruit> implements RecruitService {

    /**
     * 推荐职位
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:recruits:recommend", expire = 3600)
    public List<Recruit> recommend() {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("state", "2");
        wrapper.orderByAsc("create_time");
        wrapper.last("limit 10");

        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 最新职位
     * @return
     */
    @Override
    public List<Recruit> newList() {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.eq("state", "1");
        wrapper.orderByDesc("create_time");
        wrapper.last("limit 10");

        return this.baseMapper.selectList(wrapper);
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:recruits:", expire = 3600)
    public List<Recruit> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-recruit:recruit:", key = "#id", expire = 3600)
    public Recruit findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param recruit
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Recruit> findSearch(Recruit recruit, int page, int size) {

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(recruit));
    }

    /**
     * 多条件查询
     * @param recruit
     * @return
     */
    @Override
    public List<Recruit> findSearch(Recruit recruit) {

        return this.baseMapper.selectList(createWrapper(recruit));
    }

    /**
     * 新增
     * @param recruit
     */
    @Override
    public void add(Recruit recruit) {
        this.baseMapper.insert(recruit);
    }

    /**
     * 修改
     * @param recruit
     */
    @Override
    @CacheUpdate(name = "dc-recruit:recruit:", key = "#recruit.id", value ="#recruit")
    public void modify(Recruit recruit) {
        this.baseMapper.updateById(recruit);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-recruit:recruit:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param recruit
     * @return
     */
    private QueryWrapper<Recruit> createWrapper(Recruit recruit) {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(recruit.getId()), "id", recruit.getId());
        wrapper.like(StrUtil.isNotBlank(recruit.getJobName()), "job_name", recruit.getJobName());
        wrapper.like(StrUtil.isNotBlank(recruit.getSalary()), "salary", recruit.getSalary());
        wrapper.like(StrUtil.isNotBlank(recruit.getConditions()), "condition", recruit.getConditions());
        wrapper.like(StrUtil.isNotBlank(recruit.getEducation()), "education", recruit.getEducation() );
        wrapper.like(StrUtil.isNotBlank(recruit.getAddress()), "address", recruit.getAddress());
        wrapper.like(StrUtil.isNotBlank(recruit.getEnterpriseId()), "enterprise_id", recruit.getEnterpriseId());
        wrapper.like(StrUtil.isNotBlank(recruit.getLabel()), "label", recruit.getLabel());
        wrapper.like(StrUtil.isNotBlank(recruit.getContent1()), "content1", recruit.getContent1());
        wrapper.like(StrUtil.isNotBlank(recruit.getContent2()), "content2", recruit.getContent2());
        wrapper.eq(StrUtil.isNotBlank(recruit.getType()), "type", recruit.getType());
        wrapper.eq(StrUtil.isNotBlank(recruit.getState()), "state", recruit.getState());

        return wrapper;
    }
}
