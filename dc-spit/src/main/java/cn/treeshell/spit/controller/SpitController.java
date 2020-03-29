package cn.treeshell.spit.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.spit.model.Spit;
import cn.treeshell.spit.service.SpitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * 吐槽 前端控制器
 * @Author: panjing
 * @Date: 2020/3/28 23:24
 */
@CrossOrigin
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

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
    @GetMapping(value = "/{id}")
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
        spitService.add(spit);

        return new Result(true, StatusCode.OK, "保存成功");
    }

    /**
     * 修改
     * @param id
     * @param spit
     * @return
     */
    @PutMapping(value = "/{id}")
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
    @DeleteMapping(value = "/{id}")
    public Result remove(@PathVariable String id) {
        spitService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据父节点 ID 分页查询
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    @GetMapping(value = "/{parentid}/{page}/{size}")
    public Result findByParentid(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> spitPage = spitService.findByParentid(parentid, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(spitPage.getTotalElements(), spitPage.getContent()));
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @PutMapping(value = "/thumbup/{id}")
    public Result thumbup(@PathVariable String id) {
        // 判断当前用户是否已经点赞 TODO 测试
        String userid = "1";

        if (redisTemplate.opsForValue().get("thumbup_" + userid + "_" + id) != null) {
            return new Result(true, StatusCode.REPERROR, "不能重复点赞");
        }

        spitService.thumbup(id);
        redisTemplate.opsForValue().set("thumbup_" + userid + "_" + id, 1);

        return new Result(true, StatusCode.OK, "点赞成功");
    }
}
