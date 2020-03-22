package cn.treeshell.qa.service.impl;

import cn.treeshell.qa.mapper.ProblemMapper;
import cn.treeshell.qa.model.Problem;
import cn.treeshell.qa.service.ProblemService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 问题 服务实现类
 *
 * @author panjing
 * @since 2020-03-21
 */
@Service
@Transactional
public class ProblemServiceImpl extends ServiceImpl<ProblemMapper, Problem> implements ProblemService {

    /**
     * 分页 + 多条件查询
     * @param problem
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Problem> findSearch(Problem problem, int page, int size) {

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(problem));
    }

    /**
     * 多条件查询
     * @param problem
     * @return
     */
    @Override
    public List<Problem> findSearch(Problem problem) {

        return this.baseMapper.selectList(createWrapper(problem));
    }

    /**
     * 最热问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Problem> hotlist(String labelid, int page, int size) {

        return this.baseMapper.hotlist(labelid, new Page<>(page, size));
    }

    /**
     * 最久未回复问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Problem> waitlist(String labelid, int page, int size) {

        return this.baseMapper.waitlist(labelid, new Page<>(page, size));
    }

    /**
     * 最新问题
     * @param labelid
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Problem> newlist(String labelid, int page, int size) {

        return this.baseMapper.newlist(labelid, new Page<>(page, size));
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<Problem> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public Problem findById(String id) {

        return this.getBaseMapper().selectById(id);
    }


    /**
     * 新增
     * @param problem
     */
    @Override
    public void add(Problem problem) {
        this.baseMapper.insert(problem);
    }

    /**
     * 修改
     * @param problem
     */
    @Override
    public void modify(Problem problem) {
        this.baseMapper.updateById(problem);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param problem
     * @return
     */
    private QueryWrapper<Problem> createWrapper(Problem problem) {
        QueryWrapper<Problem> wrapper = new QueryWrapper<>();
        wrapper.like(problem.getId() != null, "id", problem.getId());
        wrapper.like(problem.getTitle() != null, "title", problem.getTitle());
        wrapper.like(problem.getContent() != null, "content", problem.getContent());
        wrapper.like(problem.getUserid() != null, "userid", problem.getUserid());
        wrapper.like(problem.getNickname() != null, "nickname", problem.getNickname());
        wrapper.like(problem.getSolve() != null, "solve", problem.getSolve());
        wrapper.like(problem.getReplyname() != null, "replyname", problem.getReplyname());

        return wrapper;
    }
}
