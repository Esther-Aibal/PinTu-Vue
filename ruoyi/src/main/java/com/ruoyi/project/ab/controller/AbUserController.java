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
import com.ruoyi.project.ab.domain.AbUser;
import com.ruoyi.project.ab.service.IAbUserService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author aibal
 * @date 2019-12-23
 */
@RestController
@RequestMapping("/ab/user")
public class AbUserController extends BaseController
{
    @Autowired
    private IAbUserService abUserService;

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('ab:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(AbUser abUser)
    {
        startPage();
        List<AbUser> list = abUserService.selectAbUserList(abUser);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('ab:user:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AbUser abUser)
    {
        List<AbUser> list = abUserService.selectAbUserList(abUser);
        ExcelUtil<AbUser> util = new ExcelUtil<AbUser>(AbUser.class);
        return util.exportExcel(list, "user");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('ab:user:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return AjaxResult.success(abUserService.selectAbUserById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('ab:user:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AbUser abUser)
    {
        return toAjax(abUserService.insertAbUser(abUser));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('ab:user:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AbUser abUser)
    {
        return toAjax(abUserService.updateAbUser(abUser));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('ab:user:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(abUserService.deleteAbUserByIds(ids));
    }
}
