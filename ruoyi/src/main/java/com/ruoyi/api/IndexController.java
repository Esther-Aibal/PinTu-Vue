package com.ruoyi.api;

import com.ruoyi.framework.web.domain.AjaxResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Aibal.He
 * @date 2019/12/26 4:10 PM
 */
@RestController
public class IndexController {

    @RequestMapping(value="/{appid}/{version}/page-frame.html",method = RequestMethod.GET)
    public AjaxResult apicheck(@PathVariable("appid") String appid, @PathVariable("version") String version) {
        return AjaxResult.success("Hello!");
    }
}
