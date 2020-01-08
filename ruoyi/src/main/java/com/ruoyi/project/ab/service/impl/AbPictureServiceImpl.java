package com.ruoyi.project.ab.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ab.mapper.AbPictureMapper;
import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.service.IAbPictureService;

/**
 * 图片Service业务层处理
 * 
 * @author aibal
 * @date 2019-12-23
 */
@Service
public class AbPictureServiceImpl implements IAbPictureService 
{
    @Autowired
    private AbPictureMapper abPictureMapper;

    /**
     * 查询图片
     * 
     * @param id 图片ID
     * @return 图片
     */
    @Override
    public AbPicture selectAbPictureById(Long id){
        return abPictureMapper.selectAbPictureById(id);
    }
    @Override
    public AbPicture selectAbPictureByNo(Integer serialNo,String atlasId)
    {
        AbPicture abPicture = new AbPicture();
        abPicture.setSerialNo(serialNo);
        abPicture.setAtlasId(atlasId);
        abPicture.setStatus(1);
        List<AbPicture> list = abPictureMapper.selectAbPictureList(abPicture);
        if(list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    /**
     * 查询图片列表
     * 
     * @param abPicture 图片
     * @return 图片
     */
    @Override
    public List<AbPicture> selectAbPictureList(AbPicture abPicture)
    {
        return abPictureMapper.selectAbPictureList(abPicture);
    }

    /**
     * 新增图片
     * 
     * @param abPicture 图片
     * @return 结果
     */
    @Override
    public int insertAbPicture(AbPicture abPicture)
    {
        abPicture.setCreateTime(DateUtils.getNowDate());
        if(StringUtils.isNotEmpty(abPicture.getImgUrl())){
            //图片分块处理
            Map<String,String> map  =ImageUtils.draw(abPicture.getImgUrl(),abPicture.getBlockNum(),abPicture.getBlockNum());
            abPicture.setRgbs(map.get("lettersCommaSeparated"));
            abPicture.setChooseArray(map.get("choosArray"));
        }

        return abPictureMapper.insertAbPicture(abPicture);
    }

    /**
     * 修改图片
     * 
     * @param abPicture 图片
     * @return 结果
     */
    @Override
    public int updateAbPicture(AbPicture abPicture)
    {
        abPicture.setUpdateTime(DateUtils.getNowDate());
        if(StringUtils.isNotEmpty(abPicture.getImgUrl())){
            //图片分块处理
            Map<String,String> map  =ImageUtils.draw(abPicture.getImgUrl(),abPicture.getBlockNum(),abPicture.getBlockNum());
            abPicture.setRgbs(map.get("lettersCommaSeparated"));
            abPicture.setChooseArray(map.get("choosArray"));
        }
        return abPictureMapper.updateAbPicture(abPicture);
    }

    /**
     * 批量删除图片
     * 
     * @param ids 需要删除的图片ID
     * @return 结果
     */
    @Override
    public int deleteAbPictureByIds(Long[] ids)
    {
        return abPictureMapper.deleteAbPictureByIds(ids);
    }

    /**
     * 删除图片信息
     * 
     * @param id 图片ID
     * @return 结果
     */
    @Override
    public int deleteAbPictureById(Long id)
    {
        return abPictureMapper.deleteAbPictureById(id);
    }

    @Override
    public int review(Long id) {
        AbPicture abPicture = abPictureMapper.selectAbPictureById(id);
        if(abPicture.getStatus() ==0){
            abPicture.setStatus(1);
        }
        return abPictureMapper.updateAbPicture(abPicture);
    }
}
