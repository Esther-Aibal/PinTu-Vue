package com.ruoyi.project.ab.mapper;

import com.ruoyi.project.ab.domain.AbPicture;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 图片Mapper接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface AbPictureMapper 
{
    /**
     * 查询图片
     * 
     * @param id 图片ID
     * @return 图片
     */
    public AbPicture selectAbPictureById(Long id);

    /**
     * 查询图片列表
     * 
     * @param abPicture 图片
     * @return 图片集合
     */
    public List<AbPicture> selectAbPictureList(AbPicture abPicture);

    /**
     * 新增图片
     * 
     * @param abPicture 图片
     * @return 结果
     */
    public int insertAbPicture(AbPicture abPicture);

    /**
     * 修改图片
     * 
     * @param abPicture 图片
     * @return 结果
     */
    public int updateAbPicture(AbPicture abPicture);

    /**
     * 删除图片
     * 
     * @param id 图片ID
     * @return 结果
     */
    public int deleteAbPictureById(Long id);

    /**
     * 批量删除图片
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAbPictureByIds(Long[] ids);

    public int deleteAbPictureByAtlasId(@Param("atlasId") String atlasId);
}
