package cn.treeshell.base.controller;

import cn.treeshell.base.model.Label;
import cn.treeshell.base.service.LabelService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/16 22:45
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", labelService.findAll());
    }

    @GetMapping(value = "/{labelId}")
    public Result findById(@PathVariable String labelId) {

        return new Result(true, StatusCode.OK, "查询成功", labelService.findById(labelId));
    }

    @PostMapping
    public Result save(@RequestBody Label label) {
        labelService.save(label);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    @PutMapping(value = "/{labelId}")
    public Result update(@PathVariable String labelId, @RequestBody Label label) {
        label.setId(labelId);
        labelService.update(label);

        return new Result(true, StatusCode.OK, "更新成功");
    }

    @DeleteMapping(value = "/{labelId}")
    public Result delete(@PathVariable String labelId) {
        labelService.deleteById(labelId);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 注意：如果是 JDK1.7 环境，不要将 JSON 转为 map，有安全隐患。
     * @param label
     * @return
     */
    @PostMapping(value = "/search")
    public Result findSearch(@RequestBody Label label) {
        List<Label> list = labelService.findSearch(label);

        return new Result(true, StatusCode.OK, "查询成功", list);
    }

    @PostMapping(value = "/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label, @PathVariable int page, @PathVariable int size) {
        Page<Label> labelPage = labelService.pageQuery(label, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Label>(labelPage.getTotalElements(), labelPage.getContent()));
    }

}
