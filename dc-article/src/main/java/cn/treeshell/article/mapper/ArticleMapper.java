package cn.treeshell.article.mapper;

import cn.treeshell.article.model.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * 文章 Mapper 接口
 *
 * @author panjing
 * @since 2020-03-22
 */
public interface ArticleMapper extends BaseMapper<Article> {

    void updateState(@Param("id") String id);

    void addThumbup(@Param("id") String id);
}
