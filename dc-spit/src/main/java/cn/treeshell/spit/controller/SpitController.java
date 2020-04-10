package cn.treeshell.spit.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.spit.model.Spit;
import cn.treeshell.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 吐槽 前端控制器
 *
 * @author panjing
 * @since 2020-3-28
 */
@CrossOrigin
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(id));
    }

    /**
     * 新增
     * @param spit
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Spit spit) {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }

        spitService.add(spit);

        return new Result(true, StatusCode.OK, "保存成功");
    }

    /**
     * 修改
     * @param id
     * @param spit
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@PathVariable String id, @RequestBody Spit spit) {
        spit.set_id(id);
        spitService.modify(spit);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        spitService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据父节点 ID 分页查询
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentId(parentId, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @PutMapping("/thumbup/{id}")
    public Result thumbUp(@PathVariable String id) {
        // 判断当前用户是否已经点赞 TODO 测试
        String userId = "1";

        String key = "thumbUp_" + userId + "_" + id;
        if (redisTemplate.opsForValue().get(key) != null) {
            return new Result(true, StatusCode.REPERROR, "不能重复点赞");
        }

        spitService.thumbUp(id);
        redisTemplate.opsForValue().set(key, 1);

        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
