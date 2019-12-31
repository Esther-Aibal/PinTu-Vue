package com.ruoyi.project.ab.domain.req;

/**
 * @author Aibal.He
 * @date 2019/12/26 2:44 PM
 */
public class GamePicture {

    private String imgUrl;
    private Integer unlocked;//1为解锁 0 为未解锁
    private Integer serialNo;//序号

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(Integer unlocked) {
        this.unlocked = unlocked;
    }

    public Integer getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Integer serialNo) {
        this.serialNo = serialNo;
    }
}
