package cn.treeshell.article.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.article.model.Channel;
import cn.treeshell.article.mapper.ChannelMapper;
import cn.treeshell.article.service.ChannelService;
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
    @Cached(name = "dc-article:channels:", expire = 3600)
    public List<Channel> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    @Cached(name = "dc-article:channel:", key = "#id", expire = 3600)
    public Channel findById(String id) {

        return this.baseMapper.selectById(id);
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

        return this.baseMapper.selectPage(new Page<>(page, size), createWrapper(channel));
    }

    /**
     * 多条件查询
     * @param channel
     * @return
     */
    @Override
    public List<Channel> findSearch(Channel channel) {

        return this.baseMapper.selectList(createWrapper(channel));
    }

    /**
     * 新增
     * @param channel
     */
    @Override
    public void add(Channel channel) {
        this.baseMapper.insert(channel);
    }

    /**
     * 修改
     * @param channel
     */
    @Override
    @CacheUpdate(name = "dc-article:channel:", key = "#channel.id", value = "#channel")
    public void modify(Channel channel) {
        this.baseMapper.updateById(channel);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @CacheInvalidate(name = "dc-article:channel:", key = "#id")
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 动态条件构造
     * @param channel
     * @return
     */
    private QueryWrapper<Channel> createWrapper(Channel channel) {
        QueryWrapper<Channel> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(channel.getId()), "id", channel.getId());
        wrapper.like(StrUtil.isNotBlank(channel.getName()), "name", channel.getName());
        wrapper.eq(StrUtil.isNotBlank(channel.getState()), "state", channel.getState());

        return wrapper;
    }
}
