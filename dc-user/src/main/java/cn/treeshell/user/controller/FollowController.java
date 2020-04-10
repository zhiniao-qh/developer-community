package cn.treeshell.user.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.common.util.JwtUtil;
import cn.treeshell.user.model.Follow;
import cn.treeshell.user.service.FollowService;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 关注 前端控制器
 *
 * @author panjing
 * @since 2020-04-10
 */
@CrossOrigin
@RestController
@RequestMapping("/user/follow")
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private JwtUtil jwtUtil;

    private String userId;

    /**
     * 检测用户是否登录
     */
    @ModelAttribute
    public void check() {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            throw new RuntimeException("请先登录");
        }

        Claims claims = jwtUtil.parseJWT(token);
        userId = claims.getId();
    }

    /**
     * 关注某用户
     * @param followId
     * @return
     */
    @PostMapping("/{followId}")
    public Result add(@PathVariable String followId) {
        Follow follow = createFollow(followId);
        followService.add(follow);

        return new Result(true, StatusCode.OK, "关注成功");
    }

    /**
     * 删除某用户关注
     * @param followId
     * @return
     */
    @DeleteMapping("/{followId}")
    public Result remove(@PathVariable String followId) {
        Follow follow = createFollow(followId);
        followService.remove(follow);

        return new Result(true, StatusCode.OK, "取消关注成功");
    }

    /**
     * 查询我的关注
     * @return
     */
    @GetMapping("/myFollow")
    public Result findByUserId() {

        return new Result(true, StatusCode.OK, "查询成功", followService.findByUserId(userId));
    }

    /**
     * 查询我的粉丝
     * @return
     */
    @GetMapping("/myFans")
    public Result findByFollowId() {

        return new Result(true, StatusCode.OK, "查询成功", followService.findByFollowId(userId));
    }

    /**
     * 构建 Follow 对象
     * @param followId
     * @return
     */
    private Follow createFollow(String followId) {
        Follow follow = new Follow();
        follow.setUserId(userId);
        follow.setFollowUser(followId);

        return follow;
    }
}
