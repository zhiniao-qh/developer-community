package cn.treeshell.spit.service;

import cn.treeshell.spit.model.Spit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 吐槽 服务类
 *
 * @author panjing
 * @since 2020-3-28
 */
public interface SpitService {

    List<Spit> findAll();

    Spit findById(String id);

    void add(Spit spit);

    void modify(Spit spit);

    void remove(String id);

    Page<Spit> findByParentId(String parentId, int page, int size);

    void thumbUp(String id);
}
