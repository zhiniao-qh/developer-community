package cn.treeshell.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.treeshell.user.model.User;
import cn.treeshell.user.mapper.UserMapper;
import cn.treeshell.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户 服务实现类
 *
 * @author panjing
 * @since 2020-04-05
 */
@Service
@Transactional
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 发送短信验证码
     * @param mobile
     */
    @Override
    public void sendSms(String mobile) {
        // 1.生成 6 位随机数字
        String checkCode = RandomUtil.randomNumbers(6);

        // 2.向缓存中存放一份
        redisTemplate.opsForValue().set("checkCode_" + mobile, checkCode, 30, TimeUnit.MINUTES);

        // 3.给用户发送一份
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("checkCode", checkCode);

        rabbitTemplate.convertAndSend("sms", map);

        System.out.println("验证码为：" + checkCode);
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void register(User user) {
        user.setUpdateDate(LocalDateTime.now());
        user.setLastLoginDate(LocalDateTime.now());
        // 密码加密
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        this.baseMapper.insert(user);
    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile", user.getMobile());

        User userDB = this.baseMapper.selectOne(wrapper);

        if (userDB != null && bCryptPasswordEncoder.matches(user.getPassword(), userDB.getPassword())) {
            userDB.setPassword(StrUtil.EMPTY);

            return userDB;
        }

        return null;
    }

    /**
     * 删除，必须有 admin 角色才能删除
     * @param id
     */
    @Override
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<User> findSearch(User user, int page, int size) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(user.getMobile()), "mobile", user.getMobile());
        wrapper.like(StrUtil.isNotBlank(user.getEmail()), "email", user.getEmail());
        wrapper.like(StrUtil.isNotBlank(user.getNickname()), "nickname", user.getNickname());
        wrapper.eq(StrUtil.isNotBlank(user.getSex()), "sex", user.getSex());

        return this.baseMapper.selectPage(new Page<>(page, size), wrapper);
    }

    /**
     * 修改
     * @param user
     */
    @Override
    public void modify(User user) {
        this.baseMapper.updateById(user);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public User findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<User> findAll() {

        return this.baseMapper.selectList(null);
    }

    /**
     * 新增
     * @param user
     */
    @Override
    public void add(User user) {
        this.baseMapper.insert(user);
    }

    /**
     * 根据 userId 集合获取 User 对象
     * @param userIdList
     * @return
     */
    @Override
    public List<User> findByUserIdList(List<String> userIdList) {

        return this.baseMapper.selectBatchIds(userIdList);
    }
}
