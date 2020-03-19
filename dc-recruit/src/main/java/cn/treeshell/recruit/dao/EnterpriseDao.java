package cn.treeshell.recruit.dao;

import cn.treeshell.recruit.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/18 22:45
 */
public interface EnterpriseDao extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise>{

    /**h
     * 查询热门企业
     * @return
     */
    List<Enterprise> findByIshot(String isot);
}
