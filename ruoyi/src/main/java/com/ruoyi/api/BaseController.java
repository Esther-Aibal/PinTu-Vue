package com.ruoyi.api;

import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.ab.domain.AbMap;
import com.ruoyi.project.ab.service.IAbMapService;
import com.ruoyi.project.ab.service.IAbPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

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
    @CrossOrigin
    public AjaxResult menuList(){
        AbMap abMap = new AbMap();
        abMap.setStatus(1);
        return AjaxResult.success(abMapService.selectAbMapList(abMap));
    }

    @GetMapping("/pictureInfo/{id}")
    @CrossOrigin
    public AjaxResult pictureInfo(@PathVariable("id") String id ){
        return AjaxResult.success(abPictureService.selectAbPictureById(id));
    }

    @GetMapping("/show")
    @CrossOrigin
    public void show(String fileName, HttpServletResponse response) throws IOException {

        ServletOutputStream out = null;
        FileInputStream ips = null;
        try {
            String filePath = RuoYiConfig.getDownloadPath() + fileName.substring(fileName.indexOf("profile") + 7);
            //获取图片存放路径
            ips = new FileInputStream(filePath);
            response.setContentType("image/jpeg");
            out = response.getOutputStream();
            //读取文件流
            int len = 0;
            byte[] buffer = new byte[1024 * 10];
            while ((len = ips.read(buffer)) != -1){
                out.write(buffer,0,len);
            }
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
            ips.close();
        }

    }



}
