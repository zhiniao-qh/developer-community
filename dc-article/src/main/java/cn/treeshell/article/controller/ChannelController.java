package cn.treeshell.article.controller;

import cn.treeshell.article.model.Channel;
import cn.treeshell.article.service.ChannelService;
import cn.treeshell.common.model.PageResult;
import cn.treeshell.common.model.Result;
import cn.treeshell.common.model.StatusCode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 频道 前端控制器
 *
 * @author panjing
 * @since 2020-03-22
 */
@CrossOrigin
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    /**
     * 查询全部数据
     * @return
     */
    @GetMapping
    public Result findAll() {

        return new Result(true, StatusCode.OK, "查询成功", channelService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable String id) {

        return new Result(true, StatusCode.OK, "查询成功", channelService.findById(id));
    }


    /**
     * 分页 + 多条件查询
     * @param channel 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @PostMapping("/search/{page}/{size}")
    public Result findSearch(@RequestBody Channel channel, @PathVariable int page, @PathVariable int size) {
        IPage<Channel> channelIPage = channelService.findSearch(channel, page, size);

        return  new Result(true, StatusCode.OK, "查询成功", new PageResult<>(channelIPage.getTotal(), channelIPage.getRecords()));
    }

    /**
     * 根据条件查询
     * @param channel
     * @return
     */
    @PostMapping("/search")
    public Result findSearch( @RequestBody Channel channel) {

        return new Result(true, StatusCode.OK, "查询成功", channelService.findSearch(channel));
    }

    /**
     * 新增
     * @param channel
     */
    @PostMapping
    public Result add(@RequestBody Channel channel) {
        channelService.add(channel);

        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     * @param channel
     */
    @PutMapping("/{id}")
    public Result modify(@RequestBody Channel channel, @PathVariable String id) {
        channel.setId(id);
        channelService.modify(channel);

        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        channelService.remove(id);

        return new Result(true, StatusCode.OK, "删除成功");
    }
}
