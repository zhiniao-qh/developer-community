package cn.treeshell.base.service;

import cn.treeshell.base.dao.LabelDao;
import cn.treeshell.base.model.Label;
import cn.treeshell.common.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.criteria.Predicate;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: panjing
 * @Date: 2020/3/16 22:55
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {

        /*
         * new Specification<Label>()
         * root 根对象，也就是把条件封装到哪个对象中。
         * criteriaQuery 封装的是查询关键字。
         * criteriaBuilder 用来封装条件对象，如果直接返回null，表示不需要任何条件。
         */
        return labelDao.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
            }

            if (label.getState() != null && !"".equals(label.getState())) {
                predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), label.getState()));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            // list -> array
            predicateList.toArray(predicates);

            return criteriaBuilder.and(predicates);
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return labelDao.findAll((root, criteriaQuery, criteriaBuilder)->{
            List<Predicate> predicateList = new ArrayList<>();

            if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%"));
            }

            if (label.getState() != null && !"".equals(label.getState())) {
                predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), label.getState()));
            }


            Predicate[] predicates = new Predicate[predicateList.size()];
            predicateList.toArray(predicates);

            return criteriaBuilder.and(predicates);
        }, pageable);
    }
}
