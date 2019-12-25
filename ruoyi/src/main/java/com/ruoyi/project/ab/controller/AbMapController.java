package com.ruoyi.project.ab.controller;

import java.util.List;

import com.ruoyi.common.utils.IdUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.ab.domain.AbMap;
import com.ruoyi.project.ab.service.IAbMapService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 地图菜单Controller
 * 
 * @author aibal
 * @date 2019-12-23
 */
@RestController
@RequestMapping("/ab/map")
public class AbMapController extends BaseController
{
    @Autowired
    private IAbMapService abMapService;

    /**
     * 查询地图菜单列表
     */
    @PreAuthorize("@ss.hasPermi('ab:map:list')")
    @GetMapping("/list")
    public TableDataInfo list(AbMap abMap)
    {
        startPage();
        List<AbMap> list = abMapService.selectAbMapList(abMap);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('ab:map:list')")
    @GetMapping("/all")
    public AjaxResult all()
    {
        AbMap abMap = new AbMap();
        List<AbMap> list = abMapService.selectAbMapList(abMap);
        return AjaxResult.success(list);
    }

    /**
     * 导出地图菜单列表
     */
    @PreAuthorize("@ss.hasPermi('ab:map:export')")
    @Log(title = "地图菜单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AbMap abMap)
    {
        List<AbMap> list = abMapService.selectAbMapList(abMap);
        ExcelUtil<AbMap> util = new ExcelUtil<AbMap>(AbMap.class);
        return util.exportExcel(list, "map");
    }

    /**
     * 获取地图菜单详细信息
     */
    @PreAuthorize("@ss.hasPermi('ab:map:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(abMapService.selectAbMapById(id));
    }

    /**
     * 新增地图菜单
     */
    @PreAuthorize("@ss.hasPermi('ab:map:add')")
    @Log(title = "地图菜单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AbMap abMap)
    {
        abMap.setId(IdUtils.fastUUID());
        return toAjax(abMapService.insertAbMap(abMap));
    }

    /**
     * 修改地图菜单
     */
    @PreAuthorize("@ss.hasPermi('ab:map:edit')")
    @Log(title = "地图菜单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AbMap abMap)
    {
        return toAjax(abMapService.updateAbMap(abMap));
    }

    /**
     * 删除地图菜单
     */
    @PreAuthorize("@ss.hasPermi('ab:map:remove')")
    @Log(title = "地图菜单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(abMapService.deleteAbMapByIds(ids));
    }
}
