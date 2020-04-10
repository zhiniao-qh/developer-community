package cn.treeshell.qa.controller;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.qa.model.Reply;
import cn.treeshell.qa.service.ReplyService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 回答 前端控制器
 *
 * @author panjing
 * @since 2020-03-21
 */
@CrossOrigin
@RestController
@RequestMapping("/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll(){

        return new Result(true, StatusCode.OK, "查询成功", replyService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id){

        return new Result(true, StatusCode.OK, "查询成功", replyService.findById(id));
    }

    /**
     * 分页 + 多条件查询
     * @param reply 查询条件
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Reply reply, @PathVariable int page, @PathVariable int size){
        IPage<Reply> replyPage = replyService.findSearch(reply, page, size);

        return new Result(true,StatusCode.OK,"查询成功",  new PageResult<>(replyPage.getTotal(), replyPage.getRecords()));
    }

    /**
     * 多条件查询
     * @param reply
     * @return
     */
    @PostMapping("/search")
    public Result findSearch(@RequestBody Reply reply) {

        return new Result(true,StatusCode.OK,"查询成功",  replyService.findSearch(reply));
    }

    /**
     * 增加
     * @param reply
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Reply reply) {
        String token = (String) request.getAttribute("claims_user");
        if (StrUtil.isEmpty(token)) {

            return new Result(false, StatusCode.ACCESSERROR, "权限不足");
        }

        replyService.add(reply);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param reply
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Reply reply, @PathVariable String id) {
        reply.setId(id);
        replyService.modify(reply);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        replyService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
