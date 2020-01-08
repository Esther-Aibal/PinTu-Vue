package com.ruoyi.api;

import com.ruoyi.common.utils.IdUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.WechatUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ab.domain.AbAtlas;
import com.ruoyi.project.ab.domain.AbUser;
import com.ruoyi.project.ab.domain.req.GameAtlas;
import com.ruoyi.project.ab.domain.req.GameUser;
import com.ruoyi.project.ab.service.IAbUserService;
import com.ruoyi.project.ab.service.IGameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Aibal.He
 * @date 2019/12/26 1:38 PM
 */
@RestController
@RequestMapping("/game/user")
public class UserController {
    @Autowired
    private IGameUserService gameUserService;

    @Autowired
    private IAbUserService abUserService;
    //根据uuid获取用户数据
    @PostMapping("/info")
    @CrossOrigin
    public AjaxResult info(@RequestParam("code") String code,@RequestParam("serverType") String serverType){
        String id = null;
        if("wechat".equals(serverType)){
            id = WechatUtils.getOpenId(code);
        }else{
            id = code;
        }
        GameUser gameUser = null;
        if(StringUtils.isNotEmpty(code)){
            gameUser= gameUserService.info(id);
        }
        if(gameUser == null){
            //需要先新增
            AbUser user = new AbUser();
            if("wechat".equals(serverType)){
                user.setId(id);
            }else{
                user.setId(IdUtils.simpleUUID());
            }
            abUserService.insertAbUser(user);
            gameUser = new GameUser();
            gameUser.setId(id);
            gameUser.setAtlasNum(0);
            gameUser.setPictureNum(0);
        }
        return AjaxResult.success(gameUser);
    }

    //获取图集数据
    @PostMapping("/getAtlasByMap")
    @CrossOrigin
    public AjaxResult getAtlasByMap(@RequestParam("mapId") String mapId,@RequestParam("code") String code){
        return AjaxResult.success( gameUserService.getAtlasList(code,mapId));
    }
    //根据地图菜单获取图片 （适用于展开式）(不需要)
    @PostMapping("/getPicturesByMap")
    @CrossOrigin
    public AjaxResult getPictureByAtlas(@RequestParam("mapId") String mapId,@RequestParam("code") String code){
        List<GameAtlas> atlasList = gameUserService.getAtlasList(code,mapId);
        if(atlasList.size() == 1){
            return AjaxResult.success( gameUserService.getPictureList(code,atlasList.get(0).getId()));
        }
        return AjaxResult.error("失败");

    }
    @PostMapping("/pictureInfo")
    @CrossOrigin
    public AjaxResult pictureInfo(@RequestParam("atlasId") String atlasId,@RequestParam("code") String code){
        return AjaxResult.success(gameUserService.getPictureAboutLock(code,atlasId));
    }

    //用户胜利更新状态
    @PostMapping("/winPicture")
    @CrossOrigin
    public AjaxResult winPicture(@RequestParam("pictureId") Long pictureId,@RequestParam("code") String code){
        return AjaxResult.success(gameUserService.insertUserPicture(code,pictureId));
    }

}
