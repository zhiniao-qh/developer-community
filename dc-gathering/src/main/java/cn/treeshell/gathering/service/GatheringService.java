package cn.treeshell.gathering.service;

import cn.treeshell.gathering.model.Gathering;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 活动 服务类
 *
 * @author panjing
 * @since 2020-03-23
 */
public interface GatheringService extends IService<Gathering> {

    List<Gathering> findAll();

    Gathering findById(String id);

    IPage<Gathering> findSearch(Gathering gathering, int page, int size);

    List<Gathering> findSearch(Gathering gathering);

    void add(Gathering gathering);

    void modify(Gathering gathering);

    void delete(String id);
}
