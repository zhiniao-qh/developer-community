package cn.treeshell.user.controller;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.common.util.JwtUtil;
import cn.treeshell.user.model.Admin;
import cn.treeshell.user.service.AdminService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理员 前端控制器
 *
 * @author panjing
 * @since 2020-04-05
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        admin = adminService.login(admin);
        if (admin == null) {

            return new Result(false, StatusCode.LOGINERROR, "登陆失败");
        }

        // JWT 令牌生成
        String token = jwtUtil.createJWT(admin.getId(), admin.getLoginName(), "admin");
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("role", "admin");

        return new Result(true, StatusCode.OK, "登录成功", map);
    }

    /**
     * 新增
     * @param admin
     * @return
     */
    @PostMapping
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);

        return new Result(true, StatusCode.OK, "添加成功");
    }

    /**
     * 查询全部
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK,"查询成功", adminService.findAll());
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", adminService.findById(id));
    }

    /**
     * 修改
     * @param admin
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.modify(admin);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        adminService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 分页 + 多条件查询
     * @param admin
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Admin admin, @PathVariable int page, @PathVariable int size) {
        IPage<Admin> adminIPage = adminService.findSearch(admin, page, size);

        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(adminIPage.getTotal(), adminIPage.getRecords()));
    }
}
