package org.linlinjava.litemall.db.service;

import com.github.pagehelper.PageHelper;
import org.linlinjava.litemall.db.dao.LitemallAdMapper;
import org.linlinjava.litemall.db.dao.LitemallSupplyMapper;
import org.linlinjava.litemall.db.domain.LitemallAd;
import org.linlinjava.litemall.db.domain.LitemallAdExample;
import org.linlinjava.litemall.db.domain.LitemallSupply;
import org.linlinjava.litemall.db.domain.LitemallSupplyExample;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LitemallSupplyService {
    @Resource
    private LitemallSupplyMapper supplyMapper;

    public List<LitemallSupply> queryIndex() {
        LitemallSupplyExample example = new LitemallSupplyExample();
        example.or().andEnabledEqualTo(true);
        return supplyMapper.selectByExample(example);
    }

    public List<LitemallSupply> querySelective(String name, String content, Integer page, Integer limit, String sort,
                                               String order,String type) {
        LitemallSupplyExample example = new LitemallSupplyExample();
        LitemallSupplyExample.Criteria criteria = example.createCriteria();

        if (!StringUtils.isEmpty(name)) {
            criteria.andNameLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(content)) {
            criteria.andContentLike("%" + content + "%");
        }
        if (!StringUtils.isEmpty(type)) {
            criteria.andTypeEqualTo(type);
        }


        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            example.setOrderByClause(sort + " " + order);
        }

        PageHelper.startPage(page, limit);
        return supplyMapper.selectByExample(example);
    }

    public int updateById(LitemallSupply ad) {
        ad.setUpdateTime(LocalDateTime.now());
        return supplyMapper.updateByPrimaryKeySelective(ad);
    }

    public void deleteById(Integer id) {
        supplyMapper.deleteByPrimaryKey(id);
    }

    public void add(LitemallSupply ad) {
        ad.setAddTime(LocalDateTime.now());
        ad.setUpdateTime(LocalDateTime.now());
        supplyMapper.insertSelective(ad);
    }

    public LitemallSupply findById(Integer id) {
        return supplyMapper.selectByPrimaryKey(id);
    }
}
