package cn.treeshell.recruit.service;

import cn.treeshell.recruit.model.Enterprise;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 企业 服务类
 *
 * @author panjing
 * @since 2020-03-23
 */
public interface EnterpriseService extends IService<Enterprise> {

    List<Enterprise> hotList();

    List<Enterprise> findAll();

    Enterprise findById(String id);

    IPage<Enterprise> findSearch(Enterprise enterprise, int page, int size);

    List<Enterprise> findSearch(Enterprise enterprise);

    void add(Enterprise enterprise);

    void modify(Enterprise enterprise);

    void remove(String id);
}
