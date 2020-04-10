package cn.treeshell.user.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.user.model.Admin;
import cn.treeshell.user.mapper.AdminMapper;
import cn.treeshell.user.service.AdminService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 管理员 服务实现类
 *
 * @author panjing
 * @since 2020-04-05
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 新增
     * @param admin
     */
    @Override
    public void add(Admin admin) {
        // 密码加密
        admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));

        this.baseMapper.insert(admin);
    }

    /**
     * 登录
     * @param admin
     * @return
     */
    @Override
    public Admin login(Admin admin) {
        // 由于使用了 BCryptPasswordEncoder，因此需要先查询出密码，再做匹配操作
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", admin.getLoginName());

        Admin adminDB = this.baseMapper.selectOne(wrapper);

        if (adminDB != null && bCryptPasswordEncoder.matches(admin.getPassword(), adminDB.getPassword())) {
            adminDB.setPassword(StrUtil.EMPTY);

            return adminDB;
        }

        return null;
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void remove(String id) {
        this.baseMapper.deleteById(id);
    }

    /**
     * 分页 + 多条件查询
     * @param admin
     * @param page
     * @param size
     * @return
     */
    @Override
    public IPage<Admin> findSearch(Admin admin, int page, int size) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq(StrUtil.isNotBlank(admin.getState()), "state", admin.getState());

        return this.baseMapper.selectPage(new Page<>(page, size), wrapper);
    }

    /**
     * 修改
     * @param admin
     */
    @Override
    public void modify(Admin admin) {
        this.baseMapper.updateById(admin);
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    @Override
    public Admin findById(String id) {

        return this.baseMapper.selectById(id);
    }

    /**
     * 查询全部
     * @return
     */
    @Override
    public List<Admin> findAll() {

        return this.baseMapper.selectList(null);
    }
}
