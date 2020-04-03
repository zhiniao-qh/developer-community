package cn.treeshell.qa.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.qa.mapper.ReplyMapper;
import cn.treeshell.qa.model.Reply;
import cn.treeshell.qa.service.ReplyService;
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
 * 回答 服务实现类
 *
 * @author panjing
 * @since 2020-03-21
 */
@Service
@Transactional
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, Reply> implements ReplyService {

    /**
     * 条件查询 + 分页
     * @param reply
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Reply> findSearch(Reply reply, int page, int size) {

        return this.baseMapper.selectPage(new Page(page, size), createWrapper(reply));
    }

    /**
     * 条件查询
     * @param reply
     * @return
     */
    @Override
    public List<Reply> findSearch(Reply reply) {

        return this.baseMapper.selectList(createWrapper(reply));
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-qa:reply:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 修改
     * @param reply
     */
    @Override
    @CacheUpdate(name = "dc-qa:reply:", key = "#reply.id", value = "#reply")
    public void modify(Reply reply) {
        this.baseMapper.updateById(reply);
    }

    /**
     * 新增
     * @param reply
     */
    @Override
    public void add(Reply reply) {
        this.baseMapper.insert(reply);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-qa:reply:", key = "#id", expire = 3600)
    public Reply findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    @Cached(name = "dc-qa:replies:", expire = 3600)
    public List<Reply> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 动态条件构造
     * @param reply
     * @return
     */
    private QueryWrapper<Reply> createWrapper(Reply reply) {
        QueryWrapper<Reply> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(reply.getId()), "id", reply.getId());
        wrapper.like(StrUtil.isNotBlank(reply.getProblemId()), "problem_id", reply.getProblemId());
        wrapper.like(StrUtil.isNotBlank(reply.getContent()), "content", reply.getContent());
        wrapper.like(StrUtil.isNotBlank(reply.getUserId()), "user_id", reply.getUserId());
        wrapper.like(StrUtil.isNotBlank(reply.getNickname()), "nickname", reply.getNickname());

        return wrapper;
    }
}
