package cn.treeshell.gathering.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.gathering.model.Gathering;
import cn.treeshell.gathering.service.GatheringService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 活动 前端控制器
 *
 * @author panjing
 * @since 2020-03-23
 */
@CrossOrigin
@RestController
@RequestMapping("/gathering")
public class GatheringController {

    @Autowired
    private GatheringService gatheringService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", gatheringService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", gatheringService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param gathering 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Gathering gathering, @PathVariable int page, @PathVariable int size) {
        IPage<Gathering> gatheringIPage = gatheringService.findSearch(gathering, page, size);

        return  new Result(true, StatusCode.OK, "查询成功", new PageResult<>(gatheringIPage.getTotal(), gatheringIPage.getRecords()));
    }

    /**
     * 根据条件查询
     * @param gathering
     * @return
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Gathering gathering) {

        return new Result(true, StatusCode.OK, "查询成功", gatheringService.findSearch(gathering));
    }

    /**
     * 新增
     * @param gathering
     */
    @PostMapping
    public Result add(@RequestBody Gathering gathering) {
        gatheringService.add(gathering);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param gathering
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Gathering gathering, @PathVariable String id) {
        gathering.setId(id);
        gatheringService.modify(gathering);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable String id) {
        gatheringService.delete(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
