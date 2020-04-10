package cn.treeshell.user.service;

import cn.treeshell.user.model.Admin;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 管理员 服务类
 *
 * @author panjing
 * @since 2020-04-05
 */
public interface AdminService extends IService<Admin> {

    void add(Admin admin);

    Admin login(Admin admin);

    void remove(String id);

    IPage<Admin> findSearch(Admin admin, int page, int size);

    void modify(Admin id);

    Admin findById(String id);

    List<Admin> findAll();
}
