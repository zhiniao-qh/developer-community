package cn.treeshell.spit.service.impl;

import cn.treeshell.spit.mapper.SpitMapper;
import cn.treeshell.spit.model.Spit;
import cn.treeshell.spit.service.SpitService;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/28 23:13
 */
@Service
@Transactional
public class SpitServiceImpl implements SpitService {

    @Autowired
    private SpitMapper spitMapper;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 查询全部
     * @return
     */
    public List<Spit> findAll() {

        return spitMapper.findAll();
    }

    /**
     * 根据 ID 查询
     * @param id
     * @return
     */
    public Spit findById(String id) {

        return spitMapper.findById(id).get();
    }

    /**
     * 新增
     * @param spit
     */
    public void add(Spit spit) {
        spit.set_id(idWorker.getIdStr());
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");

        // 如果当前添加的吐槽有父节点，那么父节点吐槽数 + 1
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);

            mongoTemplate.updateFirst(query, update, "spit");
        }

        spitMapper.save(spit);
    }

    /**
     * 修改
     * @param spit
     */
    public void modify(Spit spit) {
        spitMapper.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void remove(String id) {
        spitMapper.deleteById(id);
    }

    /**
     * 按照 parentid 分页查询
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return spitMapper.findByParentid(parentid, pageable);
    }

    /**
     * 点赞：使用原生 mongoTemplate 优化性能，减少交互次数
     *      如果使用 spitMapper，需要交互两次，分别是查询 + 更新
     * @param id
     */
    @Override
    public void thumbup(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update = new Update();
        update.inc("thumbup", 1);

        mongoTemplate.updateFirst(query, update, "spit");
    }
}
