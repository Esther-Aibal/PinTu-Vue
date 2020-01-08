package com.ruoyi.project.ab.service.impl;

import com.ruoyi.project.ab.domain.*;
import com.ruoyi.project.ab.domain.req.GameAtlas;
import com.ruoyi.project.ab.domain.req.GamePicture;
import com.ruoyi.project.ab.domain.req.GameUser;
import com.ruoyi.project.ab.mapper.*;
import com.ruoyi.project.ab.service.IGameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Aibal.He
 * @date 2019/12/26 2:02 PM
 */
@Service
public class GameUserServiceImpl implements IGameUserService {

    @Autowired
    private AbUserMapper abUserMapper;
    @Autowired
    private AbUserAtlasMapper abUserAtlasMapper;
    @Autowired
    private AbUserPictureMapper abUserPictureMapper;

    @Autowired
    private AbPictureMapper abPictureMapper;
    @Autowired
    private AbAtlasMapper abAtlasMapper;


    @Override
    public GameUser info(String code) {
        AbUser abUser = abUserMapper.selectAbUserById(code);
        if(abUser == null){
            return null;
        }
        GameUser gameUser =new GameUser();
        gameUser.setId(abUser.getId());
        AbUserAtlas abUserAtlas = new AbUserAtlas();
        abUserAtlas.setUserId(code);
        List<AbUserAtlas> atlasList =  abUserAtlasMapper.selectAbUserAtlasList(abUserAtlas);
        gameUser.setAtlasNum(atlasList.size());
        AbUserPicture abUserPicture = new AbUserPicture();
        abUserPicture.setUserId(code);
        List<AbUserPicture> pictureList = abUserPictureMapper.selectAbUserPictureList(abUserPicture);
        gameUser.setPictureNum(pictureList.size());
        return gameUser;
    }

    @Override
    public List<GameAtlas> getAtlasList(String code, String mapId) {
        List<GameAtlas> list = abUserAtlasMapper.getAbUserAtlasListAboutUser(code,mapId);
        for(GameAtlas atlas :list){
            int unLockNum = 0;
            Integer[] arr = new Integer[atlas.getTotal()];
            //获取用户是否解锁集合
            List<GamePicture> pictures = abUserPictureMapper.getAbUserPicutreListAboutUser(code,atlas.getId());
            for(GamePicture gamePicture :pictures){
                arr[gamePicture.getSerialNo()] =gamePicture.getUnlocked();
                unLockNum +=gamePicture.getUnlocked();
            }
            atlas.setUnlockedNum(unLockNum);
            atlas.setBlockArr(arr);
        }
        return list;
    }

    @Override
    public List<GamePicture> getPictureList(String code, String atlasId) {
        return abUserPictureMapper.getAbUserPicutreListAboutUser(code,atlasId);
    }

    @Override
    public AbPicture getPictureAboutLock(String code, String atlasId) {
        List<AbPicture> list = abUserPictureMapper.selectLockedList(code,atlasId);
        return list.get(0);
    }

    @Override
    public int insertUserPicture(String code, Long pictureId){
        AbUserPicture abUserPicture = new AbUserPicture();
        abUserPicture.setUserId(code);
        abUserPicture.setPictureId(pictureId);
        abUserPictureMapper.insertAbUserPicture(abUserPicture);
        //根据图片获取图集
        AbPicture abPicture = abPictureMapper.selectAbPictureById(pictureId);
        AbAtlas abAtlas = abAtlasMapper.selectAbAtlasById(abPicture.getAtlasId());
        AbUserPicture search = new AbUserPicture();
        search.setUserId(code);
        List<AbUserPicture> list = abUserPictureMapper.selectAbUserPictureList(search);
        if(list.size() == abAtlas.getTotal()){
            // 如果图集图片集成则通关
            AbUserAtlas abUserAtlas = new AbUserAtlas();
            abUserAtlas.setUserId(code);
            abUserAtlas.setAtlasId(abAtlas.getId());
            abUserAtlasMapper.insertAbUserAtlas(abUserAtlas);
        }
        return 1;

    }
}
