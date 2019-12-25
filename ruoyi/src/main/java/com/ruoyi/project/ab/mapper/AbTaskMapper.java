package com.ruoyi.project.ab.mapper;

import com.ruoyi.project.ab.domain.AbTask;
import java.util.List;

/**
 * 任务Mapper接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface AbTaskMapper 
{
    /**
     * 查询任务
     * 
     * @param id 任务ID
     * @return 任务
     */
    public AbTask selectAbTaskById(String id);

    /**
     * 查询任务列表
     * 
     * @param abTask 任务
     * @return 任务集合
     */
    public List<AbTask> selectAbTaskList(AbTask abTask);

    /**
     * 新增任务
     * 
     * @param abTask 任务
     * @return 结果
     */
    public int insertAbTask(AbTask abTask);

    /**
     * 修改任务
     * 
     * @param abTask 任务
     * @return 结果
     */
    public int updateAbTask(AbTask abTask);

    /**
     * 删除任务
     * 
     * @param id 任务ID
     * @return 结果
     */
    public int deleteAbTaskById(String id);

    /**
     * 批量删除任务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAbTaskByIds(String[] ids);
}
