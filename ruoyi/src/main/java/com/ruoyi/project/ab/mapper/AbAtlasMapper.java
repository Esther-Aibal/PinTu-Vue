package com.ruoyi.project.ab.mapper;

import com.ruoyi.project.ab.domain.AbAtlas;
import java.util.List;

/**
 * 图集Mapper接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface AbAtlasMapper 
{
    /**
     * 查询图集
     * 
     * @param id 图集ID
     * @return 图集
     */
    public AbAtlas selectAbAtlasById(String id);

    /**
     * 查询图集列表
     * 
     * @param abAtlas 图集
     * @return 图集集合
     */
    public List<AbAtlas> selectAbAtlasList(AbAtlas abAtlas);

    /**
     * 新增图集
     * 
     * @param abAtlas 图集
     * @return 结果
     */
    public int insertAbAtlas(AbAtlas abAtlas);

    /**
     * 修改图集
     * 
     * @param abAtlas 图集
     * @return 结果
     */
    public int updateAbAtlas(AbAtlas abAtlas);

    /**
     * 删除图集
     * 
     * @param id 图集ID
     * @return 结果
     */
    public int deleteAbAtlasById(String id);

    /**
     * 批量删除图集
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAbAtlasByIds(String[] ids);
}
