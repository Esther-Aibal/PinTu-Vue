package com.ruoyi.project.ab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ab.mapper.AbMapMapper;
import com.ruoyi.project.ab.domain.AbMap;
import com.ruoyi.project.ab.service.IAbMapService;

/**
 * 地图菜单Service业务层处理
 * 
 * @author aibal
 * @date 2019-12-23
 */
@Service
public class AbMapServiceImpl implements IAbMapService 
{
    @Autowired
    private AbMapMapper abMapMapper;

    /**
     * 查询地图菜单
     * 
     * @param id 地图菜单ID
     * @return 地图菜单
     */
    @Override
    public AbMap selectAbMapById(String id)
    {
        return abMapMapper.selectAbMapById(id);
    }

    /**
     * 查询地图菜单列表
     * 
     * @param abMap 地图菜单
     * @return 地图菜单
     */
    @Override
    public List<AbMap> selectAbMapList(AbMap abMap)
    {
        return abMapMapper.selectAbMapList(abMap);
    }

    /**
     * 新增地图菜单
     * 
     * @param abMap 地图菜单
     * @return 结果
     */
    @Override
    public int insertAbMap(AbMap abMap)
    {
        abMap.setCreateTime(DateUtils.getNowDate());
        return abMapMapper.insertAbMap(abMap);
    }

    /**
     * 修改地图菜单
     * 
     * @param abMap 地图菜单
     * @return 结果
     */
    @Override
    public int updateAbMap(AbMap abMap)
    {
        abMap.setUpdateTime(DateUtils.getNowDate());
        return abMapMapper.updateAbMap(abMap);
    }

    /**
     * 批量删除地图菜单
     * 
     * @param ids 需要删除的地图菜单ID
     * @return 结果
     */
    @Override
    public int deleteAbMapByIds(String[] ids)
    {
        return abMapMapper.deleteAbMapByIds(ids);
    }

    /**
     * 删除地图菜单信息
     * 
     * @param id 地图菜单ID
     * @return 结果
     */
    @Override
    public int deleteAbMapById(String id)
    {
        return abMapMapper.deleteAbMapById(id);
    }
}
