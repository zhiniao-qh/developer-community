package cn.treeshell.spit.mapper;

import cn.treeshell.spit.model.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: panjing
 * @Date: 2020/3/28 23:12
 */
public interface SpitMapper extends MongoRepository<Spit, String> {

    Page<Spit> findByParentid(String parentid, Pageable pageable);
}
