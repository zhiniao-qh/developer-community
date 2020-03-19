package cn.treeshell.base.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: panjing
 * @Date: 2020/3/16 22:49
 */
@Data
@Entity
@Table(name = "label")
public class Label implements Serializable {

    @Id
    private String id;
    // 标签名称
    private String labelname;
    // 状态
    private String state;
    // 使用数量
    private Long count;
    // 关注数
    private Long fans;
    // 是否推荐
    private String recommend;
}
