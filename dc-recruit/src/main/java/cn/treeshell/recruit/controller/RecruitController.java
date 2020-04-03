package cn.treeshell.recruit.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.recruit.model.Recruit;
import cn.treeshell.recruit.service.RecruitService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 职位 前端控制器
 *
 * @author panjing
 * @since 2020-03-23
 */
@CrossOrigin
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /**
     * 推荐职位
     * @return
     */
    @GetMapping("/search/recommend")
    public Result recommend() {

        return new Result(true, StatusCode.OK, "查询成功", recruitService.recommend());
    }

    /**
     * 最新职位
     * @return
     */
    @GetMapping("/search/newlist")
    public Result newList() {

        return new Result(true, StatusCode.OK, "查询成功", recruitService.newList());
    }

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", recruitService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", recruitService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param recruit 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Recruit recruit, @PathVariable int page, @PathVariable int size) {
        IPage<Recruit> recruitIPage = recruitService.findSearch(recruit, page, size);

        return  new Result(true, StatusCode.OK, "查询成功", new PageResult<>(recruitIPage.getTotal(), recruitIPage.getRecords()));
    }

    /**
     * 根据条件查询
     * @param recruit
     * @return
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Recruit recruit) {

        return new Result(true, StatusCode.OK,"查询成功", recruitService.findSearch(recruit));
    }

    /**
     * 增加
     * @param recruit
     */
    @PostMapping
    public Result add(@RequestBody Recruit recruit) {
        recruitService.add(recruit);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param recruit
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Recruit recruit, @PathVariable String id) {
        recruit.setId(id);
        recruitService.modify(recruit);

        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        recruitService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
