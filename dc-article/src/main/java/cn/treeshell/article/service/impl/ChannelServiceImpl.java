package cn.treeshell.article.service.impl;

import cn.treeshell.article.model.Channel;
import cn.treeshell.article.mapper.ChannelMapper;
import cn.treeshell.article.service.ChannelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 频道 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class ChannelServiceImpl extends ServiceImpl<ChannelMapper, Channel> implements ChannelService {

}
