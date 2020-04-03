package cn.treeshell.article.controller;

import cn.treeshell.article.model.Column;
import cn.treeshell.article.service.ColumnService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 专栏 前端控制器
 *
 * @author panjing
 * @since 2020-03-22
 */
@CrossOrigin
@RestController
@RequestMapping("/column")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    /**
     * 专栏审核
     * @param id
     * @return
     */
    @PutMapping("/examine/{id}")
    public Result check(@PathVariable String id) {
        columnService.check(id);

        return new Result(true, StatusCode.OK, "审核成功");
    }

    /**
     * 根据用户 ID 查询专栏列表
     * @param userId
     * @return
     */
    @GetMapping("/user/{userId}")
    public Result findByUserId(@PathVariable String userId) {

        return new Result(true, StatusCode.OK, "查询成功", columnService.findByUserId(userId));
    }
    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", columnService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", columnService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param column 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Column column, @PathVariable int page, @PathVariable int size) {
        IPage<Column> columnIPage = columnService.findSearch(column, page, size);

        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<>(columnIPage.getTotal(), columnIPage.getRecords()));
    }

    /**
     * 根据条件查询
     * @param column
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Column column) {

        return new Result(true, StatusCode.OK, "查询成功", columnService.findSearch(column));
    }

    /**
     * 新增
     * @param column
     */
    @PostMapping
    public Result add(@RequestBody Column column) {
        columnService.add(column);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param column
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Column column, @PathVariable String id) {
        column.setId(id);
        columnService.modify(column);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        columnService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}

