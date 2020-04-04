package cn.treeshell.search.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章
 *
 * @author  panjing
 * @since  2020-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Document(indexName = "developer-community", type = "article")
public class Article implements Serializable {

    /**
     * Id
     */
    @Id
    private String id;

    /**
     * 标题
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;

    /**
     * 文章正文
     */
    @Field(analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;

    /**
     * 审核状态
     */
    private String state;
}
