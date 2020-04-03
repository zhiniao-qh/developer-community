package cn.treeshell.qa.mapper;

import cn.treeshell.qa.model.Problem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;

/**
 * 问题 Mapper 接口
 *
 * @author panjing
 * @since 2020-03-21
 */
public interface ProblemMapper extends BaseMapper<Problem> {

    IPage<Problem> newList(@Param("labelId") String labelId, IPage<Problem> problemIPage);

    IPage<Problem> hotList(@Param("labelId") String labelId, IPage<Problem> problemIPage);

    IPage<Problem> waitList(@Param("labelId") String labelId, IPage<Problem> problemIPage);
}
