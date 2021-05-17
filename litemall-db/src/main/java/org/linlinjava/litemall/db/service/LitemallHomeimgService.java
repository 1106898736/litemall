package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallHomeimgMapper;
import org.linlinjava.litemall.db.dao.LitemallIntroduceMapper;
import org.linlinjava.litemall.db.domain.LitemallHomeimg;
import org.linlinjava.litemall.db.domain.LitemallHomeimgExample;
import org.linlinjava.litemall.db.domain.LitemallIntroduce;
import org.linlinjava.litemall.db.domain.LitemallIntroduce.Column;
import org.linlinjava.litemall.db.domain.LitemallIntroduceExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallHomeimgService {
    Column[] columns = new Column[]{Column.id, Column.gallery};
    @Resource
    private LitemallIntroduceMapper introduceMapper;

    @Resource
    private LitemallHomeimgMapper homeimgMapper;

    /**
     * 获取热卖商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallIntroduce> queryByHot(int offset, int limit) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return introduceMapper.selectByExampleSelective(example, columns);
    }

    /**
     * 获取新品上市
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallIntroduce> queryByNew(int offset, int limit) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return introduceMapper.selectByExampleSelective(example, columns);
    }

    /**
     * 获取分类下的商品
     *
     * @param catList
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallIntroduce> queryByCategory(List<Integer> catList, int offset, int limit) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.setOrderByClause("add_time  desc");
        PageHelper.startPage(offset, limit);
        return introduceMapper.selectByExampleSelective(example, columns);
    }


    /**
     * 获取分类下的商品
     *
     * @param catId
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallIntroduce> queryByCategory(Integer catId, int offset, int limit) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);

        return introduceMapper.selectByExampleSelective(example, columns);
    }


    public List<LitemallHomeimg> querySelective() {
        LitemallHomeimgExample example = new LitemallHomeimgExample();
        return homeimgMapper.selectByExample(example);
    }

    public List<LitemallIntroduce> querySelective(Integer page, Integer size, String sort, String order) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        LitemallIntroduceExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return introduceMapper.selectByExampleSelective(example);
    }

    /**
     * 获取某个商品信息,包含完整信息
     *
     * @param id
     * @return
     */
    public LitemallIntroduce findById(Integer id) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.or().andIdEqualTo(id);
        return introduceMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public LitemallIntroduce findByIdVO(Integer id) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.or().andIdEqualTo(id);
        return introduceMapper.selectOneByExampleSelective(example, columns);
    }


    /**
     * 获取所有在售物品总数
     *
     * @return
     */
    public Integer queryOnSale() {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        return (int) introduceMapper.countByExample(example);
    }

    public int updateById(LitemallIntroduce goods) {
        goods.setUpdateTime(LocalDateTime.now());
        return introduceMapper.updateByPrimaryKeySelective(goods);
    }

    public void deleteById(Integer id) {
        introduceMapper.deleteByPrimaryKey(id);
    }

    public void add(LitemallIntroduce goods) {
        goods.setAddTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        introduceMapper.insertSelective(goods);
    }

    /**
     * 获取所有物品总数，包括在售的和下架的，但是不包括已删除的商品
     *
     * @return
     */
    public int count() {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        return (int) introduceMapper.countByExample(example);
    }

    public List<Integer> getCatIds(Integer brandId, String keywords) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        LitemallIntroduceExample.Criteria criteria1 = example.or();
        LitemallIntroduceExample.Criteria criteria2 = example.or();

        if (!StringUtils.isEmpty(keywords)) { ;
            criteria2.andNameLike("%" + keywords + "%");
        }

        List<LitemallIntroduce> goodsList = introduceMapper.selectByExampleSelective(example, Column.id);
        List<Integer> cats = new ArrayList<Integer>();
        for (LitemallIntroduce goods : goodsList) {
            cats.add(goods.getId());
        }
        return cats;
    }

    public boolean checkExistByName(String name) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.or().andNameEqualTo(name);
        return introduceMapper.countByExample(example) != 0;
    }

    public List<LitemallIntroduce> queryByIds(Integer[] ids) {
        LitemallIntroduceExample example = new LitemallIntroduceExample();
        example.or().andIdIn(Arrays.asList(ids));
        return introduceMapper.selectByExampleSelective(example, columns);
    }
}
