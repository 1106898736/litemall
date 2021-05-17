package org.linlinjava.litemall.admin.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.linlinjava.litemall.admin.dto.GoodsAllinone;
import org.linlinjava.litemall.admin.vo.CatVo;
import org.linlinjava.litemall.core.qcode.QCodeService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.db.domain.*;
import org.linlinjava.litemall.db.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.linlinjava.litemall.admin.util.AdminResponseCode.GOODS_NAME_EXIST;

@Service
public class AdminInfoService {
    private final Log logger = LogFactory.getLog(AdminInfoService.class);

    @Autowired
    private LitemallInfoService infoService;
    @Autowired
    private LitemallGoodsSpecificationService specificationService;
    @Autowired
    private LitemallGoodsAttributeService attributeService;
    @Autowired
    private LitemallGoodsProductService productService;
    @Autowired
    private LitemallCategoryService categoryService;
    @Autowired
    private LitemallBrandService brandService;
    @Autowired
    private LitemallCartService cartService;
    @Autowired
    private QCodeService qCodeService;

    public Object list( Integer page, Integer limit, String sort, String order) {
        List<LitemallInfo> infosList = infoService.querySelective(page, limit, sort, order);
        return ResponseUtil.okList(infosList);
    }


    @Transactional
    public int update(LitemallInfo litemallInfo) {
        return infoService.updateById(litemallInfo);
    }

    @Transactional
    public Object delete(LitemallInfo info) {
        Integer id = info.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        infoService.deleteById(id);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object create(LitemallInfo info) {
        int res = infoService.add(info);
        if (res == 0) {
            return  ResponseUtil.fail(555,"添加失败");
        }
        return ResponseUtil.ok();
    }


    public Object detail(Integer id) {
        LitemallInfo info = infoService.findById(id);
        return ResponseUtil.ok(info);
    }

}
