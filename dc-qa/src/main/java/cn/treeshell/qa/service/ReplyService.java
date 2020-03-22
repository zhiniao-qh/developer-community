package cn.treeshell.qa.service;

import cn.treeshell.qa.model.Reply;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 回答 服务类
 *
 * @author panjing
 * @since 2020-03-21
 */
public interface ReplyService extends IService<Reply> {

    IPage<Reply> findSearch(Reply reply, int page, int size);

    List<Reply> findSearch(Reply reply);

    void remove(String id);

    void modify(Reply reply);

    void add(Reply reply);

    Reply findById(String id);

    List<Reply> findAll();
}
