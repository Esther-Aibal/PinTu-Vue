package com.ruoyi.api;

import com.ruoyi.common.utils.WechatUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ab.domain.AbUser;
import com.ruoyi.project.ab.domain.req.GameUser;
import com.ruoyi.project.ab.service.IAbUserService;
import com.ruoyi.project.ab.service.IGameUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/info/{code}")
    public AjaxResult info(@PathVariable("code") String code){
        String openId = WechatUtils.getOpenId(code);
        GameUser gameUser = gameUserService.info(openId);
        if(gameUser == null){
            //需要先新增
            AbUser user = new AbUser();
            user.setId(openId);
            abUserService.insertAbUser(user);
            gameUser = new GameUser();
            gameUser.setId(openId);
            gameUser.setAtlasNum(0);
            gameUser.setPictureNum(0);
        }
        return AjaxResult.success(gameUser);
    }

    //获取图集数据
    @PostMapping("/getAtlasByMap")
    public AjaxResult getAtlasByMap(@RequestParam("mapId") String mapId,@RequestParam("code") String code){
        return AjaxResult.success( gameUserService.getAtlasList(code,mapId));
    }
    //根据图集获取图片数
    @PostMapping("/getPictureByAtlas")
    public AjaxResult getPictureByAtlas(@RequestParam("atlasId") String atlasId,@RequestParam("code") String code){
        return AjaxResult.success( gameUserService.getPictureList(code,atlasId));
    }
    @PostMapping("/pictureInfo")
    public AjaxResult pictureInfo(@RequestParam("atlasId") String atlasId,@RequestParam("code") String code){
        return AjaxResult.success(gameUserService.getPictureAboutLock(code,atlasId));
    }


}
