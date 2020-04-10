package cn.treeshell.qa.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.qa.mapper.ProblemMapper;
import cn.treeshell.qa.model.Problem;
import cn.treeshell.qa.service.ProblemService;
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
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @Override
    @Cached(name = "dc-qa:problems:hotList:", key = "#labelId", expire = 1200)
    public IPage<Problem> hotList(String labelId, int page, int size) {
        
        return this.baseMapper.hotList(labelId, new Page<>(page, size));
    }

    /**
     * 最久未回复问题
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @Override
    @Cached(name = "dc-qa:problems:waitList:", key = "#labelId", expire = 3600)
    public IPage<Problem> waitList(String labelId, int page, int size) {

        return this.baseMapper.waitList(labelId, new Page<>(page, size));
    }

    /**
     * 最新问题
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Problem> newList(String labelId, int page, int size) {

        return this.baseMapper.newList(labelId, new Page<>(page, size));
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    @Cached(name = "dc-qa:problems:", expire = 3600)
    public List<Problem> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-qa:problem:", key = "#id", expire = 3600)
    public Problem findById(String id) {

        return this.baseMapper.selectById(id);
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
    @CacheUpdate(name = "dc-qa:problem:", key = "#problem.id", value = "#problem")
    public void modify(Problem problem) {
        this.baseMapper.updateById(problem);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-qa:problem:", key = "#id")
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
        wrapper.like(StrUtil.isNotBlank(problem.getId()), "id", problem.getId());
        wrapper.like(StrUtil.isNotBlank(problem.getTitle()), "title", problem.getTitle());
        wrapper.like(StrUtil.isNotBlank(problem.getContent()), "content", problem.getContent());
        wrapper.like(StrUtil.isNotBlank(problem.getUserId()), "user_id", problem.getUserId());
        wrapper.like(StrUtil.isNotBlank(problem.getNickname()), "nickname", problem.getNickname());
        wrapper.like(StrUtil.isNotBlank(problem.getSolve()), "solve", problem.getSolve());
        wrapper.like(StrUtil.isNotBlank(problem.getReplyName()), "reply_name", problem.getReplyName());

        return wrapper;
    }
}
