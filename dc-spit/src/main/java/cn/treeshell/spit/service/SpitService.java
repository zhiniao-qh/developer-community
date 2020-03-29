package cn.treeshell.spit.service;

import cn.treeshell.spit.model.Spit;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/28 23:13
 */
public interface SpitService {

    List<Spit> findAll();

    Spit findById(String id);

    void add(Spit spit);

    void modify(Spit spit);

    void remove(String id);

    Page<Spit> findByParentid(String parentid, int page, int size);

    void thumbup(String id);
}
