package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallInfoMapper;
import org.linlinjava.litemall.db.dao.LitemallIntroduceMapper;
import org.linlinjava.litemall.db.domain.LitemallInfo;
import org.linlinjava.litemall.db.domain.LitemallInfoExample;
import org.linlinjava.litemall.db.domain.LitemallIntroduce;
import org.linlinjava.litemall.db.domain.LitemallInfo.Column;
import org.linlinjava.litemall.db.domain.LitemallIntroduceExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LitemallInfoService {
    Column[] columns = new Column[]{Column.id, Column.suerid, Column.type, Column.picUrl,Column.brief,
            Column.detail, Column.home,Column.isHot, Column.read, Column.enabled, Column.updateTime};
    @Resource
    private LitemallInfoMapper infoMapper;

    /**
     * 获取热卖商品
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallInfo> queryByHot(int offset, int limit) {
        LitemallInfoExample example = new LitemallInfoExample();
        example.or().andIsHotEqualTo(true);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return infoMapper.selectByExampleSelective(example, columns);
    }

    /**
     * 获取新品上市
     *
     * @param offset
     * @param limit
     * @return
     */
    public List<LitemallInfo> queryByNew(int offset, int limit) {
        LitemallInfoExample example = new LitemallInfoExample();
        example.or().andEnabledEqualTo(true);
        example.setOrderByClause("add_time desc");
        PageHelper.startPage(offset, limit);
        return infoMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallInfo> querySelective(Integer goodsId,boolean enabled,  boolean isHot, Integer offset,
                                                  Integer limit,
                                                  String sort,
                                                  String order) {
        LitemallInfoExample example = new LitemallInfoExample();
        LitemallInfoExample.Criteria criteria1 = example.or();
        if (goodsId != null) {
            criteria1.andIdEqualTo(goodsId);
        }
        if (!StringUtils.isEmpty(isHot)) {
            criteria1.andIsHotEqualTo(isHot);
        }
        if (!StringUtils.isEmpty(enabled)) {
            criteria1.andEnabledEqualTo(enabled);
        }

        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(offset, limit);

        return infoMapper.selectByExampleSelective(example, columns);
    }

    public List<LitemallInfo> querySelective(Integer page, Integer size, String sort, String order) {
        LitemallInfoExample example = new LitemallInfoExample();
        LitemallInfoExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, size);
        return infoMapper.selectByExampleSelective(example);
    }

    /**
     * 获取某个商品信息,包含完整信息
     *
     * @param id
     * @return
     */
    public LitemallInfo findById(Integer id) {
        LitemallInfoExample example = new LitemallInfoExample();
        example.or().andIdEqualTo(id);
        return infoMapper.selectByPrimaryKey(id);
    }

    /**
     * 获取某个商品信息，仅展示相关内容
     *
     * @param id
     * @return
     */
    public LitemallInfo findByIdVO(Integer id) {
        LitemallInfoExample example = new LitemallInfoExample();
        example.or().andIdEqualTo(id);
        return infoMapper.selectOneByExampleSelective(example, columns);
    }

    public int updateById(LitemallInfo goods) {
        goods.setUpdateTime(LocalDateTime.now());
        return infoMapper.updateByPrimaryKeySelective(goods);
    }

    public void deleteById(Integer id) {
        infoMapper.deleteByPrimaryKey(id);
    }

    public int add(LitemallInfo goods) {
        goods.setAddTime(LocalDateTime.now());
        goods.setUpdateTime(LocalDateTime.now());
        return infoMapper.insertSelective(goods);
    }

    /**
     * 获取所有物品总数，包括在售的和下架的，但是不包括已删除的商品
     *
     * @return
     */
    public int count() {
        LitemallInfoExample example = new LitemallInfoExample();
        return (int) infoMapper.countByExample(example);
    }

    public List<LitemallInfo> queryByIds(Integer[] ids) {
        LitemallInfoExample example = new LitemallInfoExample();
        example.or().andIdIn(Arrays.asList(ids));
        return infoMapper.selectByExampleSelective(example, columns);
    }
}
