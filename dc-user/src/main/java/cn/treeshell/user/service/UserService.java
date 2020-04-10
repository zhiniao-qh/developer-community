package cn.treeshell.user.service;

import cn.treeshell.user.model.User;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 用户 服务类
 *
 * @author panjing
 * @since 2020-04-05
 */
public interface UserService extends IService<User> {

    void sendSms(String mobile);

    void register(User user);

    User login(User user);

    void remove(String id);

    IPage<User> findSearch(User user, int page, int size);

    void modify(User user);

    User findById(String id);

    List<User> findAll();

    void add(User user);

    List<User> findByUserIdList(List<String> userIdList);
}
