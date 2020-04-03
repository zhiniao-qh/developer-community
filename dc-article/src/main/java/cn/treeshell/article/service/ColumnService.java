package cn.treeshell.article.service;

import cn.treeshell.article.model.Column;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 专栏 服务类
 *
 * @author panjing
 * @since 2020-03-22
 */
public interface ColumnService extends IService<Column> {

    List<Column> findAll();

    Column findById(String id);

    IPage<Column> findSearch(Column column, int page, int size);

    List<Column> findSearch(Column column);

    void add(Column column);

    void modify(Column column);

    void remove(String id);

    void check(String id);

    List<Column> findByUserId(String userId);
}
