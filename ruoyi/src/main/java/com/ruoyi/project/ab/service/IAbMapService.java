package com.ruoyi.project.ab.service;

import com.ruoyi.project.ab.domain.AbMap;
import java.util.List;

/**
 * 地图菜单Service接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface IAbMapService 
{
    /**
     * 查询地图菜单
     * 
     * @param id 地图菜单ID
     * @return 地图菜单
     */
    public AbMap selectAbMapById(String id);

    /**
     * 查询地图菜单列表
     * 
     * @param abMap 地图菜单
     * @return 地图菜单集合
     */
    public List<AbMap> selectAbMapList(AbMap abMap);

    /**
     * 新增地图菜单
     * 
     * @param abMap 地图菜单
     * @return 结果
     */
    public int insertAbMap(AbMap abMap);

    /**
     * 修改地图菜单
     * 
     * @param abMap 地图菜单
     * @return 结果
     */
    public int updateAbMap(AbMap abMap);

    /**
     * 批量删除地图菜单
     * 
     * @param ids 需要删除的地图菜单ID
     * @return 结果
     */
    public int deleteAbMapByIds(String[] ids);

    /**
     * 删除地图菜单信息
     * 
     * @param id 地图菜单ID
     * @return 结果
     */
    public int deleteAbMapById(String id);

    public int review(String id);
}
