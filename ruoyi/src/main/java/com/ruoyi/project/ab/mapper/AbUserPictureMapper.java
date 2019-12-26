package com.ruoyi.project.ab.mapper;

import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.domain.AbUserPicture;
import com.ruoyi.project.ab.domain.req.GamePicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户解锁图Mapper接口
 * 
 * @author aibal
 * @date 2019-12-26
 */
public interface AbUserPictureMapper 
{
    /**
     * 查询用户解锁图
     * 
     * @param userId 用户解锁图ID
     * @return 用户解锁图
     */
    public AbUserPicture selectAbUserPictureById(String userId);

    /**
     * 查询用户解锁图列表
     * 
     * @param abUserPicture 用户解锁图
     * @return 用户解锁图集合
     */
    public List<AbUserPicture> selectAbUserPictureList(AbUserPicture abUserPicture);

    /**
     * 新增用户解锁图
     * 
     * @param abUserPicture 用户解锁图
     * @return 结果
     */
    public int insertAbUserPicture(AbUserPicture abUserPicture);

    /**
     * 修改用户解锁图
     * 
     * @param abUserPicture 用户解锁图
     * @return 结果
     */
    public int updateAbUserPicture(AbUserPicture abUserPicture);

    /**
     * 删除用户解锁图
     * 
     * @param userId 用户解锁图ID
     * @return 结果
     */
    public int deleteAbUserPictureById(String userId);

    /**
     * 批量删除用户解锁图
     * 
     * @param userIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAbUserPictureByIds(String[] userIds);

    public List<GamePicture> getAbUserPicutreListAboutUser(@Param("userId") String userId, @Param("atlasId") String atlasId);

    public List<AbPicture> selectLockedList(@Param("userId") String userId, @Param("atlasId") String atlasId);
}
