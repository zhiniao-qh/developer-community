package cn.treeshell.user.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.common.util.JwtUtil;
import cn.treeshell.user.model.User;
import cn.treeshell.user.service.UserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户 前端控制器
 *
 * @author panjing
 * @since 2020-04-05
 */
@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private HttpServletRequest request;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        user = userService.login(user);

        if (user == null) {

            return new Result(false, StatusCode.LOGINERROR, "登陆失败");
        }

        String token = jwtUtil.createJWT(user.getId(), user.getMobile(), "user");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("role", "user");

        return new Result(true, StatusCode.OK, "登陆成功", map);
    }

    /**
     * 发送短信验证码
     * @param mobile
     * @return
     */
    @PostMapping("/send_sms/{mobile}")
    public Result sendSms(@PathVariable String mobile) {
        userService.sendSms(mobile);

        return new Result(true, StatusCode.OK, "发送成功");
    }

    /**
     * 注册
     * @param checkCode
     * @return
     */
    @PostMapping("/register/{checkCode}")
    public Result register(@PathVariable String checkCode, @RequestBody User user) {
        // 1.读取缓存中的验证码
        String checkCodeRedis = (String) redisTemplate.opsForValue().get("checkCode_" + user.getMobile());

        // 2.判断验证码是否合法
        if (StrUtil.isEmpty(checkCodeRedis)) {

            return new Result(false, StatusCode.ERROR, "请先获取手机验证码");
        }

        if (!checkCodeRedis.equals(checkCode)) {

            return new Result(false, StatusCode.ERROR, "请输入正确的验证码");
        }

        // 3.验证码正确 -> 注册
        userService.register(user);

        return new Result(true, StatusCode.OK, "注册成功");
    }

    /**
     * 删除，必须有admin角色才能删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        String token = (String) request.getAttribute("claims_admin");
        if (StrUtil.isEmpty(token)) {

            throw new RuntimeException("权限不足");
        }

        userService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 新增
     * @param user
     * @return
     */
    @PostMapping
    public Result add(@RequestBody User user) {
        userService.add(user);

        return new Result(true, StatusCode.OK, "新增成功");
    }

    /**
     * 修改
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody User user, @PathVariable String id) {
        user.setId(id);
        userService.modify(user);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", userService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }

    /**
     * 查询登录用户信息
     * @return
     */
    @GetMapping("/info")
    public Result info() {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            throw new RuntimeException("请先登录");
        }

        Claims claims = jwtUtil.parseJWT(token);
        String id = claims.getId();

        return new Result(true, StatusCode.OK, "查询成功", userService.findById(id));
    }

    /**
     * 修改当前登录用户信息
     * @param user
     * @return
     */
    @PutMapping("/saveInfo")
    public Result saveInfo(@RequestBody User user) {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            throw new RuntimeException("请先登录");
        }

        Claims claims = jwtUtil.parseJWT(token);
        String id = claims.getId();
        user.setId(id);

        userService.modify(user);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 分页 + 多条件查询
     * @param user
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody User user, @PathVariable int page, @PathVariable int size) {
        IPage<User> userIPage = userService.findSearch(user, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(userIPage.getTotal(), userIPage.getRecords()));
    }
}
