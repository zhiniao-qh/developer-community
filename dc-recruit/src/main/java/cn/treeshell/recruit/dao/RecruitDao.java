package cn.treeshell.recruit.dao;

import cn.treeshell.recruit.model.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/18 22:45
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit>{

    /**
     * 推荐职位
     * @param state
     * @return
     */
    List<Recruit> findTop6ByStateOrderByCreatetime(String state);

    /**
     * 最新职位
     * @param state
     * @return
     */
    List<Recruit> findTop6ByStateNotOrderByCreatetimeDesc(String state);
}
