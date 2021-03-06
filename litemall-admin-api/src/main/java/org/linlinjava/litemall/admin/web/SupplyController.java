package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallAd;
import org.linlinjava.litemall.db.domain.LitemallSupply;
import org.linlinjava.litemall.db.service.LitemallAdService;
import org.linlinjava.litemall.db.service.LitemallSupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/admin/supply")
@Validated
public class SupplyController {
    private final Log logger = LogFactory.getLog(SupplyController.class);

    @Autowired
    private LitemallSupplyService spplyService;


    @RequiresPermissions("admin:ad:list")
    @RequiresPermissionsDesc(menu = {"推广管理", "供货管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String name, String content, String type,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "add_time") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<LitemallSupply> adList = spplyService.querySelective(name, content, page, limit, sort, order, type);
        return ResponseUtil.okList(adList);
    }

    private Object validate(LitemallSupply ad) {
        String name = ad.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        String content = ad.getContent();
        if (StringUtils.isEmpty(content)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    @RequiresPermissions("admin:ad:create")
    @RequiresPermissionsDesc(menu = {"推广管理", "供货管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallSupply ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
        spplyService.add(ad);
        return ResponseUtil.ok(ad);
    }

    @RequiresPermissions("admin:ad:read")
    @RequiresPermissionsDesc(menu = {"推广管理", "供货管理"}, button = "详情")
    @GetMapping("/read")
    public Object read(@NotNull Integer id) {
        LitemallSupply ad = spplyService.findById(id);
        return ResponseUtil.ok(ad);
    }

    @RequiresPermissions("admin:ad:update")
    @RequiresPermissionsDesc(menu = {"推广管理", "供货管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallSupply ad) {
        Object error = validate(ad);
        if (error != null) {
            return error;
        }
        if (spplyService.updateById(ad) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok(ad);
    }

    @RequiresPermissions("admin:ad:delete")
    @RequiresPermissionsDesc(menu = {"推广管理", "供货管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallSupply ad) {
        Integer id = ad.getId();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        spplyService.deleteById(id);
        return ResponseUtil.ok();
    }

}
