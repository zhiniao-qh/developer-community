package cn.treeshell.recruit.mapper;

import cn.treeshell.recruit.model.Recruit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 职位 Mapper 接口
 *
 * @author panjing
 * @since 2020-03-23
 */
public interface RecruitMapper extends BaseMapper<Recruit> {

    List<Recruit> selectByState(@Param("state") String state);

    List<Recruit> selectByStateAndCreatetime(@Param("state")String state);
}
