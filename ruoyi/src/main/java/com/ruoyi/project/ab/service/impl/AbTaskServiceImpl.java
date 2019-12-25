package com.ruoyi.project.ab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ab.mapper.AbTaskMapper;
import com.ruoyi.project.ab.domain.AbTask;
import com.ruoyi.project.ab.service.IAbTaskService;

/**
 * 任务Service业务层处理
 * 
 * @author aibal
 * @date 2019-12-23
 */
@Service
public class AbTaskServiceImpl implements IAbTaskService 
{
    @Autowired
    private AbTaskMapper abTaskMapper;

    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    @Override
    public AbTask selectAbTaskById(String id)
    {
        return abTaskMapper.selectAbTaskById(id);
    }

    /**
     * 查询任务列表
     * 
     * @param abTask 任务
     * @return 任务
     */
    @Override
    public List<AbTask> selectAbTaskList(AbTask abTask)
    {
        return abTaskMapper.selectAbTaskList(abTask);
    }

    /**
     * 新增任务
     * 
     * @param abTask 任务
     * @return 结果
     */
    @Override
    public int insertAbTask(AbTask abTask)
    {
        abTask.setCreateTime(DateUtils.getNowDate());
        return abTaskMapper.insertAbTask(abTask);
    }

    /**
     * 修改任务
     * 
     * @param abTask 任务
     * @return 结果
     */
    @Override
    public int updateAbTask(AbTask abTask)
    {
        abTask.setUpdateTime(DateUtils.getNowDate());
        return abTaskMapper.updateAbTask(abTask);
    }

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的任务ID
     * @return 结果
     */
    @Override
    public int deleteAbTaskByIds(String[] ids)
    {
        return abTaskMapper.deleteAbTaskByIds(ids);
    }

    /**
     * 删除任务信息
     * 
     * @param id 任务ID
     * @return 结果
     */
    @Override
    public int deleteAbTaskById(String id)
    {
        return abTaskMapper.deleteAbTaskById(id);
    }
}
