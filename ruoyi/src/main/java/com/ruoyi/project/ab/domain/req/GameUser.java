package com.ruoyi.project.ab.domain.req;

/**
 * @author Aibal.He
 * @date 2019/12/26 1:58 PM
 */
public class GameUser {

    private String id;
    private Integer pictureNum;//解锁图片数
    private Integer atlasNum;//解锁图集数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPictureNum() {
        return pictureNum;
    }

    public void setPictureNum(Integer pictureNum) {
        this.pictureNum = pictureNum;
    }

    public Integer getAtlasNum() {
        return atlasNum;
    }

    public void setAtlasNum(Integer atlasNum) {
        this.atlasNum = atlasNum;
    }
}
