package com.ruoyi.project.ab.service;

import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.domain.req.GameAtlas;
import com.ruoyi.project.ab.domain.req.GamePicture;
import com.ruoyi.project.ab.domain.req.GameUser;

import java.util.List;

/**
 * @author Aibal.He
 * @date 2019/12/26 2:01 PM
 */
public interface IGameUserService {

    public GameUser info(String code);

    public List<GameAtlas> getAtlasList(String code , String mapId);

    public List<GamePicture> getPictureList(String code, String atlasId);

    AbPicture getPictureAboutLock(String code, String atlasId);

}
