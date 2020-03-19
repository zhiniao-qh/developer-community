package cn.treeshell.recruit.controller;

import java.util.List;
import java.util.Map;

import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import cn.treeshell.recruit.model.Enterprise;
import cn.treeshell.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: panjing
 * @Date: 2020/3/18 22:45
 */
@RestController
@CrossOrigin
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;


	/**
	 * 查看热门企业
	 * @return
	 */
	@GetMapping("/search/hotlist")
	public Result hotList() {
		List<Enterprise> enterpriseList = enterpriseService.hotlist("1");

		return new Result(true, StatusCode.OK, "查询成功", enterpriseList);
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@GetMapping
	public Result findAll(){

		return new Result(true, StatusCode.OK,"查询成功",enterpriseService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@GetMapping(value="/{id}")
	public Result findById(@PathVariable String id){

		return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@PostMapping(value="/search/{page}/{size}")
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Enterprise> pageList = enterpriseService.findSearch(searchMap, page, size);

		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Enterprise>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @PostMapping(value="/search")
    public Result findSearch( @RequestBody Map searchMap){

    	return new Result(true,StatusCode.OK,"查询成功",enterpriseService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param enterprise
	 */
	@PostMapping
	public Result add(@RequestBody Enterprise enterprise  ){
		enterpriseService.add(enterprise);

		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param enterprise
	 */
	@PutMapping(value="/{id}")
	public Result update(@RequestBody Enterprise enterprise, @PathVariable String id ){
		enterprise.setId(id);
		enterpriseService.update(enterprise);

		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@DeleteMapping(value="/{id}")
	public Result delete(@PathVariable String id ){
		enterpriseService.deleteById(id);

		return new Result(true,StatusCode.OK,"删除成功");
	}
}
