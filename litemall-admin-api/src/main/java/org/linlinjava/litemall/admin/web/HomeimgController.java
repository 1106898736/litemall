package org.linlinjava.litemall.admin.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.linlinjava.litemall.admin.annotation.RequiresPermissionsDesc;
import org.linlinjava.litemall.admin.service.AdminIntroduceService;
import org.linlinjava.litemall.admin.service.HomeimgService;
import org.linlinjava.litemall.core.util.ResponseUtil;
import org.linlinjava.litemall.core.validator.Order;
import org.linlinjava.litemall.core.validator.Sort;
import org.linlinjava.litemall.db.domain.LitemallHomeimg;
import org.linlinjava.litemall.db.domain.LitemallIntroduce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin/homeimg")
@Validated
public class HomeimgController {
    private final Log logger = LogFactory.getLog(HomeimgController.class);

    @Autowired
    private HomeimgService adminGoodsService;

    /**
     *
     */
    @RequiresPermissions("admin:goods:list")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "查询")
    @GetMapping("/list")
    public Object list() {
        return adminGoodsService.list();
    }

    @GetMapping("/catAndBrand")
    public Object list2() {
        return adminGoodsService.list2();
    }

    /**
     * 编辑商品n
     *
     * @param
     * @return
     */
    @RequiresPermissions("admin:goods:update")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody LitemallHomeimg homeimg) {
        if (adminGoodsService.update(homeimg) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        return ResponseUtil.ok(homeimg);
    }

    /**
     * 删除商品
     *
     * @param goods
     * @return
     */
    @RequiresPermissions("admin:goods:delete")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody LitemallIntroduce goods) {
        return adminGoodsService.delete(goods);
    }

    /**
     * 添加商品
     *
     * @param
     * @return
     */
    @RequiresPermissions("admin:introduce:create")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "上架")
    @PostMapping("/create")
    public Object create(@RequestBody LitemallIntroduce introduce) {
        adminGoodsService.add(introduce);
        return ResponseUtil.ok(introduce);
    }

    /**
     * 商品详情
     *
     * @param id
     * @return
     */
    @RequiresPermissions("admin:goods:read")
    @RequiresPermissionsDesc(menu = {"商品管理", "商品管理"}, button = "详情")
    @GetMapping("/detail")
    public Object detail(@NotNull Integer id) {
        return adminGoodsService.detail(id);

    }

}
