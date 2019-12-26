package com.ruoyi.project.ab.service;

import com.ruoyi.project.ab.domain.AbPicture;
import java.util.List;

/**
 * 图片Service接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface IAbPictureService 
{
    /**
     * 查询图片
     * 
     * @param id 图片ID
     * @return 图片
     */
    public AbPicture selectAbPictureById(String id);

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
     * 批量删除图片
     * 
     * @param ids 需要删除的图片ID
     * @return 结果
     */
    public int deleteAbPictureByIds(Long[] ids);

    /**
     * 删除图片信息
     * 
     * @param id 图片ID
     * @return 结果
     */
    public int deleteAbPictureById(Long id);

    public int review(String id);
}
