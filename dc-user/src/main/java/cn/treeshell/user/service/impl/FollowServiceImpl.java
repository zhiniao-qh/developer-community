package cn.treeshell.user.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.treeshell.user.mapper.FollowMapper;
import cn.treeshell.user.model.Follow;
import cn.treeshell.user.model.User;
import cn.treeshell.user.service.FollowService;
import cn.treeshell.user.service.UserService;
import com.alicp.jetcache.anno.Cached;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 关注 服务实现类
 *
 * @author panjing
 * @since 2020-04-10
 */
@Service
@Transactional
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {

    @Autowired
    private UserService userService;

    /**
     * 查询我的粉丝
     * @param userId
     * @return
     */
    @Override
    @Cached(name = "dc-user:follow:follows:", expire = 3600)
    public List<User> findByFollowId(String userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(userId), "follow_user", userId);

        List<Follow> followList = this.baseMapper.selectList(wrapper);
        if (CollUtil.isEmpty(followList)) return null;

        List<String> userIdList = followList.stream().map(follow -> follow.getUserId()).collect(Collectors.toList());

        return userService.findByUserIdList(userIdList);
    }

    /**
     * 查询我的关注
     * @param userId
     * @return
     */
    @Override
    @Cached(name = "dc-user:follow:fans:", expire = 3600)
    public List<User> findByUserId(String userId) {
        QueryWrapper<Follow> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(userId), "user_id", userId);

        List<Follow> followList = this.baseMapper.selectList(wrapper);
        if (CollUtil.isEmpty(followList)) return null;

        List<String> followIdList = followList.stream().map(follow -> follow.getFollowUser()).collect(Collectors.toList());

        return userService.findByUserIdList(followIdList);
    }

    /**
     * 删除某用户关注
     * @param follow
     */
    @Override
    public void remove(Follow follow) {
        UpdateWrapper<Follow> wrapper = new UpdateWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(follow.getUserId()), "user_id", follow.getUserId());
        wrapper.eq(StrUtil.isNotBlank(follow.getFollowUser()), "follow_user", follow.getFollowUser());

        this.baseMapper.delete(wrapper);
    }

    /**
     * 关注某用户
     * @param follow
     */
    @Override
    public void add(Follow follow) {
        this.baseMapper.insert(follow);
    }
}
