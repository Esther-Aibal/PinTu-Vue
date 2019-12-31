package com.ruoyi.project.ab.domain.req;

/**
 * @author Aibal.He
 * @date 2019/12/26 2:44 PM
 */
public class GameAtlas {

    private String id;
    private String imgUrl;
    private Integer unlockedNum;
    private Integer total;
    private Integer[] blockArr;
    private Integer row;

    private Integer cel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getUnlockedNum() {
        return unlockedNum;
    }

    public void setUnlockedNum(Integer unlockedNum) {
        this.unlockedNum = unlockedNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer[] getBlockArr() {
        return blockArr;
    }

    public void setBlockArr(Integer[] blockArr) {
        this.blockArr = blockArr;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCel() {
        return cel;
    }

    public void setCel(Integer cel) {
        this.cel = cel;
    }
}
