package com.ruoyi.api;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ab.domain.AbMap;
import com.ruoyi.project.ab.service.IAbMapService;
import com.ruoyi.project.ab.service.IAbPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aibal.He
 * @date 2019/12/26 1:37 PM
 */
@RestController
@RequestMapping("/game/base")
public class BaseController {

    @Autowired
    private IAbMapService abMapService;

    @Autowired
    private IAbPictureService abPictureService;
    //地图菜单数据
    @GetMapping("/menuList")
    public AjaxResult menuList(){
        AbMap abMap = new AbMap();
        abMap.setStatus(1);
        return AjaxResult.success(abMapService.selectAbMapList(abMap));
    }

    @GetMapping("/pictureInfo/{id}")
    public AjaxResult pictureInfo(@PathVariable("id") String id ){
        return AjaxResult.success(abPictureService.selectAbPictureById(id));
    }



}
