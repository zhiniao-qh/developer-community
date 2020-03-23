package cn.treeshell.recruit.service.impl;

import cn.treeshell.recruit.model.Recruit;
import cn.treeshell.recruit.mapper.RecruitMapper;
import cn.treeshell.recruit.service.RecruitService;
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
    public List<Recruit> recommend() {

        return this.getBaseMapper().selectByState("2");
    }

    /**
     * 最新职位
     * @return
     */
    @Override
    public List<Recruit> newlist() {

        return this.getBaseMapper().selectByStateAndCreatetime("0");
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<Recruit> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public Recruit findById(String id) {

        return this.getBaseMapper().selectById(id);
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

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(recruit));
    }

    /**
     * 多条件查询
     * @param recruit
     * @return
     */
    @Override
    public List<Recruit> findSearch(Recruit recruit) {

        return this.getBaseMapper().selectList(createWrapper(recruit));
    }

    /**
     * 新增
     * @param recruit
     */
    @Override
    public void add(Recruit recruit) {
        this.getBaseMapper().insert(recruit);
    }

    /**
     * 修改
     * @param recruit
     */
    @Override
    public void modify(Recruit recruit) {
        this.getBaseMapper().updateById(recruit);
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
     * @param recruit
     * @return
     */
    private QueryWrapper<Recruit> createWrapper(Recruit recruit) {
        QueryWrapper<Recruit> wrapper = new QueryWrapper<>();
        wrapper.like(recruit.getId() != null, "id", recruit.getId());
        wrapper.like(recruit.getJobname() != null, "jobname", recruit.getJobname());
        wrapper.like(recruit.getSalary() != null, "salary", recruit.getSalary());
        wrapper.like(recruit.getCondition() != null, "condition", recruit.getCondition());
        wrapper.like(recruit.getEducation() != null, "education", recruit.getEducation() );
        wrapper.like(recruit.getAddress() != null, "address", recruit.getAddress());
        wrapper.like(recruit.getEid() != null, "eid", recruit.getEid());
        wrapper.like(recruit.getLabel() != null, "label", recruit.getLabel());
        wrapper.like(recruit.getContent1() != null, "content1", recruit.getContent1());
        wrapper.like(recruit.getContent2() != null, "content2", recruit.getContent2());
        wrapper.eq(recruit.getType() != null, "type", recruit.getType());
        wrapper.eq(recruit.getState() != null, "state", recruit.getState());

        return wrapper;
    }
}
