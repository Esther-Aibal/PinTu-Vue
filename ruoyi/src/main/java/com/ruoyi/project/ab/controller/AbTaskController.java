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
import com.ruoyi.project.ab.domain.AbTask;
import com.ruoyi.project.ab.service.IAbTaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 任务Controller
 * 
 * @author aibal
 * @date 2019-12-23
 */
@RestController
@RequestMapping("/ab/task")
public class AbTaskController extends BaseController
{
    @Autowired
    private IAbTaskService abTaskService;

    /**
     * 查询任务列表
     */
    @PreAuthorize("@ss.hasPermi('ab:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(AbTask abTask)
    {
        startPage();
        List<AbTask> list = abTaskService.selectAbTaskList(abTask);
        return getDataTable(list);
    }

    /**
     * 导出任务列表
     */
    @PreAuthorize("@ss.hasPermi('ab:task:export')")
    @Log(title = "任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AbTask abTask)
    {
        List<AbTask> list = abTaskService.selectAbTaskList(abTask);
        ExcelUtil<AbTask> util = new ExcelUtil<AbTask>(AbTask.class);
        return util.exportExcel(list, "task");
    }

    /**
     * 获取任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('ab:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(abTaskService.selectAbTaskById(id));
    }

    /**
     * 新增任务
     */
    @PreAuthorize("@ss.hasPermi('ab:task:add')")
    @Log(title = "任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AbTask abTask)
    {
        return toAjax(abTaskService.insertAbTask(abTask));
    }

    /**
     * 修改任务
     */
    @PreAuthorize("@ss.hasPermi('ab:task:edit')")
    @Log(title = "任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AbTask abTask)
    {
        return toAjax(abTaskService.updateAbTask(abTask));
    }

    /**
     * 删除任务
     */
    @PreAuthorize("@ss.hasPermi('ab:task:remove')")
    @Log(title = "任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(abTaskService.deleteAbTaskByIds(ids));
    }
}
