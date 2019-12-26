package com.ruoyi.project.ab.mapper;

import com.ruoyi.project.ab.domain.AbUserAtlas;
import com.ruoyi.project.ab.domain.req.GameAtlas;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户解锁图集Mapper接口
 * 
 * @author aibal
 * @date 2019-12-26
 */
public interface AbUserAtlasMapper 
{
    /**
     * 查询用户解锁图集
     * 
     * @param userId 用户解锁图集ID
     * @return 用户解锁图集
     */
    public AbUserAtlas selectAbUserAtlasById(String userId);

    /**
     * 查询用户解锁图集列表
     * 
     * @param abUserAtlas 用户解锁图集
     * @return 用户解锁图集集合
     */
    public List<AbUserAtlas> selectAbUserAtlasList(AbUserAtlas abUserAtlas);

    /**
     * 新增用户解锁图集
     * 
     * @param abUserAtlas 用户解锁图集
     * @return 结果
     */
    public int insertAbUserAtlas(AbUserAtlas abUserAtlas);

    /**
     * 修改用户解锁图集
     * 
     * @param abUserAtlas 用户解锁图集
     * @return 结果
     */
    public int updateAbUserAtlas(AbUserAtlas abUserAtlas);

    /**
     * 删除用户解锁图集
     * 
     * @param userId 用户解锁图集ID
     * @return 结果
     */
    public int deleteAbUserAtlasById(String userId);

    /**
     * 批量删除用户解锁图集
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAbUserAtlasByIds(String[] userIds);

    public List<GameAtlas> getAbUserAtlasListAboutUser(@Param("userId") String userId, @Param("mapId") String mapId);
}
