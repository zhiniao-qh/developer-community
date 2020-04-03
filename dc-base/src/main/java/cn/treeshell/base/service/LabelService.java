package cn.treeshell.base.service;

import cn.treeshell.base.model.Label;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 标签 服务类
 *
 * @author panjing
 * @since 2020-03-22
 */
public interface LabelService extends IService<Label> {

    IPage<Label> findSearch(Label label, int page, int size);

    List<Label> findSearch(Label label);

    List<Label> findAll();

    Label findById(String id);

    void add(Label label);

    void modify(Label label);

    void remove(String id);

    List<Label> findTopList(String recommend);

    List<Label> findByState(String state);
}
