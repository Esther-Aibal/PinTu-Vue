package com.ruoyi.project.ab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 图集对象 ab_atlas
 * 
 * @author aibal
 * @date 2019-12-23
 */
public class AbAtlas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 图片地址 */
    private String imgUrl;

    /** 图集id */
    @Excel(name = "图集id")
    private String mapId;

    /** $column.columnComment */
    @Excel(name = "图集id")
    private String name;

    /** $column.columnComment */
    @Excel(name = "图集id")
    private Integer status;

    /** 图片总数 */
    @Excel(name = "图片总数")
    private Integer total;

    /** $column.columnComment */
    private Integer delFlag;

    private Integer row;

    private Integer cel;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setImgUrl(String imgUrl) 
    {
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() 
    {
        return imgUrl;
    }

    public String getMapId() {
        return mapId;
    }

    public void setMapId(String mapId) {
        this.mapId = mapId;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("imgUrl", getImgUrl())
            .append("mapId", getMapId())
            .append("name", getName())
            .append("status", getStatus())
            .append("total", getTotal())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
