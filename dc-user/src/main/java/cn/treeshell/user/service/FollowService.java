package cn.treeshell.user.service;

import cn.treeshell.user.model.Follow;
import cn.treeshell.user.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 关注 服务类
 *
 * @author panjing
 * @since 2020-04-10
 */
public interface FollowService extends IService<Follow> {

    List<User> findByFollowId(String userId);

    List<User> findByUserId(String userId);

    void remove(Follow follow);

    void add(Follow follow);
}
