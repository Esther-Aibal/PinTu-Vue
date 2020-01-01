package com.ruoyi.project.ab.service.impl;

import java.awt.image.BufferedImage;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.ImageUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.mapper.AbPictureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ab.mapper.AbAtlasMapper;
import com.ruoyi.project.ab.domain.AbAtlas;
import com.ruoyi.project.ab.service.IAbAtlasService;

/**
 * 图集Service业务层处理
 * 
 * @author aibal
 * @date 2019-12-23
 */
@Service
public class AbAtlasServiceImpl implements IAbAtlasService 
{
    @Autowired
    private AbAtlasMapper abAtlasMapper;
    @Autowired
    private AbPictureMapper abPictureMapper;

    /**
     * 查询图集
     * 
     * @param id 图集ID
     * @return 图集
     */
    @Override
    public AbAtlas selectAbAtlasById(String id)
    {
        return abAtlasMapper.selectAbAtlasById(id);
    }

    /**
     * 查询图集列表
     * 
     * @param abAtlas 图集
     * @return 图集
     */
    @Override
    public List<AbAtlas> selectAbAtlasList(AbAtlas abAtlas)
    {
        return abAtlasMapper.selectAbAtlasList(abAtlas);
    }

    /**
     * 新增图集
     * 
     * @param abAtlas 图集
     * @return 结果
     */
    @Override
    public int insertAbAtlas(AbAtlas abAtlas)
    {
        abAtlas.setCreateTime(DateUtils.getNowDate());
        abAtlasMapper.insertAbAtlas(abAtlas);
        if(abAtlas.getAutoGen() == 1){
            //根据图集图片生成子图
            String filePath =RuoYiConfig.getDownloadPath() + abAtlas.getImgUrl().substring(abAtlas.getImgUrl().indexOf("profile") + 7);

            List<String> list = ImageUtils.generate(filePath,abAtlas.getRow(),abAtlas.getCel());
            //保存图片并

            if(list.size()>0){
                abPictureMapper.deleteAbPictureByAtlasId(abAtlas.getId());
            }
            for (int i = 0;i<list.size();i++) {
                AbPicture abPicture = new AbPicture();
                abPicture.setStatus(1);
                abPicture.setAtlasId(abAtlas.getId());
                abPicture.setSerialNo(i);
                abPicture.setBlockNum(5);
                abPicture.setImgUrl(list.get(i));
                abPicture.setName(abAtlas.getName()+"-"+i);
                abPicture.setRgbs(ImageUtils.draw(abPicture.getImgUrl(),abPicture.getBlockNum(),abPicture.getBlockNum()));
                abPictureMapper.insertAbPicture(abPicture);
            }
        }

        return 1;
    }

    /**
     * 修改图集
     * 
     * @param abAtlas 图集
     * @return 结果
     */
    @Override
    public int updateAbAtlas(AbAtlas abAtlas)
    {
        abAtlas.setUpdateTime(DateUtils.getNowDate());
        if(abAtlas.getAutoGen() == 1){
            //根据图集图片生成子图
            String filePath =RuoYiConfig.getDownloadPath() + abAtlas.getImgUrl().substring(abAtlas.getImgUrl().indexOf("profile") + 7);

            List<String> list = ImageUtils.generate(filePath,abAtlas.getRow(),abAtlas.getCel());
            //保存图片并

            if(list.size()>0){
                abPictureMapper.deleteAbPictureByAtlasId(abAtlas.getId());
            }
            for (int i = 0;i<list.size();i++) {
                AbPicture abPicture = new AbPicture();
                abPicture.setStatus(1);
                abPicture.setAtlasId(abAtlas.getId());
                abPicture.setSerialNo(i);
                abPicture.setBlockNum(5);
                abPicture.setImgUrl(list.get(i));
                abPicture.setName(abAtlas.getName()+"-"+i);
                abPicture.setRgbs(ImageUtils.draw(abPicture.getImgUrl(),abPicture.getBlockNum(),abPicture.getBlockNum()));
                abPictureMapper.insertAbPicture(abPicture);
            }
        }
        return abAtlasMapper.updateAbAtlas(abAtlas);
    }

    /**
     * 批量删除图集
     * 
     * @param ids 需要删除的图集ID
     * @return 结果
     */
    @Override
    public int deleteAbAtlasByIds(String[] ids)
    {
        return abAtlasMapper.deleteAbAtlasByIds(ids);
    }

    /**
     * 删除图集信息
     * 
     * @param id 图集ID
     * @return 结果
     */
    @Override
    public int deleteAbAtlasById(String id)
    {
        return abAtlasMapper.deleteAbAtlasById(id);
    }

    @Override
    public int review(String id) {
        AbAtlas abAtlas = abAtlasMapper.selectAbAtlasById(id);
        if(abAtlas.getStatus() ==0){
            abAtlas.setStatus(1);
        }
        return abAtlasMapper.updateAbAtlas(abAtlas);
    }
}
