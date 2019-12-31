package com.ruoyi.project.ab.service.impl;

import com.ruoyi.project.ab.domain.AbPicture;
import com.ruoyi.project.ab.domain.AbUser;
import com.ruoyi.project.ab.domain.AbUserAtlas;
import com.ruoyi.project.ab.domain.AbUserPicture;
import com.ruoyi.project.ab.domain.req.GameAtlas;
import com.ruoyi.project.ab.domain.req.GamePicture;
import com.ruoyi.project.ab.domain.req.GameUser;
import com.ruoyi.project.ab.mapper.AbUserAtlasMapper;
import com.ruoyi.project.ab.mapper.AbUserMapper;
import com.ruoyi.project.ab.mapper.AbUserPictureMapper;
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
}
