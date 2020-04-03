package cn.treeshell.spit.mapper;

import cn.treeshell.spit.model.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 吐槽 Mapper 接口
 *
 * @author panjing
 * @since 2020-3-28
 */
public interface SpitMapper extends MongoRepository<Spit, String> {

    Page<Spit> findByParentId(String parentId, Pageable pageable);
}
