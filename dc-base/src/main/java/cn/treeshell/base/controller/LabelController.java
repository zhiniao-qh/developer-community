package cn.treeshell.base.controller;

import cn.treeshell.base.model.Label;
import cn.treeshell.base.service.LabelService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 标签 前端控制器
 *
 *  @author panjing
 * @since 2020-03-22
 */
@CrossOrigin
@RestController
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(id));
    }

    /**
     * 多条件查询
     * 注意：如果是 JDK1.7 环境，不要将 JSON 转为 map，有安全隐患。
     * @param label
     * @return
     */
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);

        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    /**
     * 分页 + 多条件查询
     * @param label
     * @param page
     * @param size
     * @return
     */
    @PostMapping(value = "/search/{page}/{size}")
    public Result findSearch(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        IPage<Label> labelPage = labelService.findSearch(label, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(labelPage.getTotal(), labelPage.getRecords()));
    }

    /**
     * 新增
     * @param label
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Label label) {
        labelService.add(label);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 修改
     * @param id
     * @param label
     * @return
     */
    @PutMapping(value = "/{id}")
    public Result modify(@PathVariable String id, @RequestBody Label label) {
        label.setId(id);
        labelService.modify(label);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    public Result remove(@PathVariable String id) {
        labelService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
