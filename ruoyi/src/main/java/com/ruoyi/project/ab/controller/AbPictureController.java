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
import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.service.IAbPictureService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 图片Controller
 * 
 * @author aibal
 * @date 2019-12-23
 */
@RestController
@RequestMapping("/ab/picture")
public class AbPictureController extends BaseController
{
    @Autowired
    private IAbPictureService abPictureService;

    /**
     * 查询图片列表
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:list')")
    @GetMapping("/list")
    public TableDataInfo list(AbPicture abPicture)
    {
        startPage();
        List<AbPicture> list = abPictureService.selectAbPictureList(abPicture);
        return getDataTable(list);
    }

    /**
     * 导出图片列表
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:export')")
    @Log(title = "图片", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AbPicture abPicture)
    {
        List<AbPicture> list = abPictureService.selectAbPictureList(abPicture);
        ExcelUtil<AbPicture> util = new ExcelUtil<AbPicture>(AbPicture.class);
        return util.exportExcel(list, "picture");
    }

    /**
     * 获取图片详细信息
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(abPictureService.selectAbPictureById(id));
    }

    /**
     * 新增图片
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:add')")
    @Log(title = "图片", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AbPicture abPicture)
    {
        return toAjax(abPictureService.insertAbPicture(abPicture));
    }

    /**
     * 修改图片
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:edit')")
    @Log(title = "图片", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AbPicture abPicture)
    {
        return toAjax(abPictureService.updateAbPicture(abPicture));
    }

    /**
     * 删除图片
     */
    @PreAuthorize("@ss.hasPermi('ab:picture:remove')")
    @Log(title = "图片", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(abPictureService.deleteAbPictureByIds(ids));
    }
}
