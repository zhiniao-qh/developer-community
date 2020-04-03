package cn.treeshell.recruit.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.recruit.model.Enterprise;
import cn.treeshell.recruit.service.EnterpriseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 企业 前端控制器
 *
 * @author panjing
 * @since 2020-03-23
 */
@CrossOrigin
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;

    /**
     * 查看热门企业
     * @return
     */
    @GetMapping("/search/hotlist")
    public Result hotList() {

        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.hotList());
    }

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param enterprise 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Enterprise enterprise , @PathVariable int page, @PathVariable int size) {
        IPage<Enterprise> enterpriseIPage = enterpriseService.findSearch(enterprise, page, size);

        return  new Result(true, StatusCode.OK, "查询成功", new PageResult<>(enterpriseIPage.getTotal(), enterpriseIPage.getRecords()) );
    }

    /**
     * 根据条件查询
     * @param enterprise
     * @return
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Enterprise enterprise) {

        return new Result(true, StatusCode.OK, "查询成功", enterpriseService.findSearch(enterprise));
    }

    /**
     * 新增
     * @param enterprise
     */
    @PostMapping
    public Result add(@RequestBody Enterprise enterprise) {
        enterpriseService.add(enterprise);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param enterprise
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Enterprise enterprise, @PathVariable String id) {
        enterprise.setId(id);
        enterpriseService.modify(enterprise);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        enterpriseService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
