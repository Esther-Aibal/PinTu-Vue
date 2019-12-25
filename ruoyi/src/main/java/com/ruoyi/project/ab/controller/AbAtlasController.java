package com.ruoyi.project.ab.controller;

import java.util.List;
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
import com.ruoyi.project.ab.domain.AbAtlas;
import com.ruoyi.project.ab.service.IAbAtlasService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 图集Controller
 * 
 * @author aibal
 * @date 2019-12-23
 */
@RestController
@RequestMapping("/ab/atlas")
public class AbAtlasController extends BaseController
{
    @Autowired
    private IAbAtlasService abAtlasService;

    /**
     * 查询图集列表
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:list')")
    @GetMapping("/list")
    public TableDataInfo list(AbAtlas abAtlas)
    {
        startPage();
        List<AbAtlas> list = abAtlasService.selectAbAtlasList(abAtlas);
        return getDataTable(list);
    }

    /**
     * 导出图集列表
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:export')")
    @Log(title = "图集", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AbAtlas abAtlas)
    {
        List<AbAtlas> list = abAtlasService.selectAbAtlasList(abAtlas);
        ExcelUtil<AbAtlas> util = new ExcelUtil<AbAtlas>(AbAtlas.class);
        return util.exportExcel(list, "atlas");
    }

    /**
     * 获取图集详细信息
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(abAtlasService.selectAbAtlasById(id));
    }

    /**
     * 新增图集
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:add')")
    @Log(title = "图集", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AbAtlas abAtlas)
    {
        return toAjax(abAtlasService.insertAbAtlas(abAtlas));
    }

    /**
     * 修改图集
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:edit')")
    @Log(title = "图集", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AbAtlas abAtlas)
    {
        return toAjax(abAtlasService.updateAbAtlas(abAtlas));
    }

    /**
     * 删除图集
     */
    @PreAuthorize("@ss.hasPermi('ab:atlas:remove')")
    @Log(title = "图集", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(abAtlasService.deleteAbAtlasByIds(ids));
    }
}
