package cn.treeshell.article.service;

import cn.treeshell.article.model.Channel;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 频道 服务类
 *
 * @author panjing
 * @since 2020-03-22
 */
public interface ChannelService extends IService<Channel> {

    List<Channel> findAll();

    Channel findById(String id);

    IPage<Channel> findSearch(Channel channel, int page, int size);

    List<Channel> findSearch(Channel channel);

    void add(Channel channel);

    void modify(Channel channel);

    void remove(String id);
}
