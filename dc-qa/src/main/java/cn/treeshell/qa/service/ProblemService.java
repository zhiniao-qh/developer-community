package cn.treeshell.qa.service;

import cn.treeshell.qa.model.Problem;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 问题 服务类
 *
 * @author panjing
 * @since 2020-03-21
 */
public interface ProblemService extends IService<Problem> {

    IPage<Problem> findSearch(Problem problem, int page, int size);

    List<Problem> findSearch(Problem problem);

    IPage<Problem> hotlist(String labelid, int page, int size);

    IPage<Problem> waitlist(String labelid, int page, int size);

    IPage<Problem> newlist(String labelid, int page, int size);

    List<Problem> findAll();

    Problem findById(String id);

    void add(Problem problem);

    void modify(Problem problem);

    void remove(String id);
}
