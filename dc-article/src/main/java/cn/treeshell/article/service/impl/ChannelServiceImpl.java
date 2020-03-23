package cn.treeshell.article.service.impl;

import cn.treeshell.article.model.Channel;
import cn.treeshell.article.mapper.ChannelMapper;
import cn.treeshell.article.service.ChannelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 频道 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

    /**
     * 查询全部数据
     * @return
     */
    @Override
    public List<Channel> findAll() {

        return this.getBaseMapper().selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public Channel findById(String id) {

        return this.getBaseMapper().selectById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param channel
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Channel> findSearch(Channel channel, int page, int size) {

        return this.getBaseMapper().selectPage(new Page<>(page, size), createWrapper(channel));
    }

    /**
     * 多条件查询
     * @param channel
     * @return
     */
    @Override
    public List<Channel> findSearch(Channel channel) {

        return this.getBaseMapper().selectList(createWrapper(channel));
    }

    /**
     * 新增
     * @param channel
     */
    @Override
    public void add(Channel channel) {
        this.getBaseMapper().insert(channel);
    }

    /**
     * 修改
     * @param channel
     */
    @Override
    public void modify(Channel channel) {
        this.getBaseMapper().updateById(channel);
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
     * @param channel
     * @return
     */
    private QueryWrapper<Channel> createWrapper(Channel channel) {
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.like(channel.getId() != null, "id", channel.getId());
        wrapper.like(channel.getName() != null, "name", channel.getName());
        wrapper.eq(channel.getState() != null, "state", channel.getState());

        return wrapper;
    }
}
