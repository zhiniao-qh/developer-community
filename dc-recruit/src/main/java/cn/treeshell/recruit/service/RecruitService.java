package cn.treeshell.recruit.service;

import cn.treeshell.recruit.model.Recruit;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 职位 服务类
 *
 * @author panjing
 * @since 2020-03-23
 */
public interface RecruitService extends IService<Recruit> {

    List<Recruit> recommend();

    List<Recruit> newList();

    List<Recruit> findAll();

    Recruit findById(String id);

    IPage<Recruit> findSearch(Recruit recruit, int page, int size);

    List<Recruit> findSearch(Recruit recruit);

    void add(Recruit recruit);

    void modify(Recruit recruit);

    void remove(String id);
}
