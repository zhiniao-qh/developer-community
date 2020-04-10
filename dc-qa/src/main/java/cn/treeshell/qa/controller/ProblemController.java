package cn.treeshell.qa.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.qa.model.Problem;
import cn.treeshell.qa.service.ProblemService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 问题 前端控制器
 *
 * @author panjing
 * @since 2020-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 最新问题
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/newlist/{labelId}/{page}/{size}")
    public Result newList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        IPage<Problem> problemIPage = problemService.newList(labelId, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(problemIPage.getTotal(), problemIPage.getRecords()));
    }

    /**
     * 最热问题
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/hotlist/{labelId}/{page}/{size}")
    public Result hotList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        IPage<Problem> problemIPage = problemService.hotList(labelId, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(problemIPage.getTotal(), problemIPage.getRecords()));
    }

    /**
     * 最久未回复问题
     * @param labelId
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/waitlist/{labelId}/{page}/{size}")
    public Result waitList(@PathVariable String labelId, @PathVariable int page, @PathVariable int size) {
        IPage<Problem> problemIPage = problemService.waitList(labelId, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(problemIPage.getTotal(), problemIPage.getRecords()));
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK,"查询成功", problemService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", problemService.findById(id));
    }

    /**
     * 分页 + 多条件查询
     * @param problem
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Problem problem, @PathVariable int page, @PathVariable int size) {
        IPage<Problem> problemIPage = problemService.findSearch(problem, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(problemIPage.getTotal(), problemIPage.getRecords()));
    }

    /**
     * 多条件查询
     * @param problem
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Problem problem) {

        return new Result(true, StatusCode.OK, "查询成功", problemService.findSearch(problem));
    }

    /**
     * 新增
     * @param problem
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Problem problem) {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }

        problemService.add(problem);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param problem
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Problem problem, @PathVariable String id) {
        problem.setId(id);
        problemService.modify(problem);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        problemService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
