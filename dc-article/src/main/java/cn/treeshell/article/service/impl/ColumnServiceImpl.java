package cn.treeshell.article.service.impl;

import cn.treeshell.article.model.Column;
import cn.treeshell.article.mapper.ColumnMapper;
import cn.treeshell.article.service.ColumnService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 专栏 服务实现类
 *
 * @author panjing
 * @since 2020-03-22
 */
@Service
@Transactional
public class ColumnServiceImpl extends ServiceImpl<ColumnMapper, Column> implements ColumnService {

}
