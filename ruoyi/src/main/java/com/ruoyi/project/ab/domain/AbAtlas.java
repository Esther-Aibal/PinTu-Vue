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
    private Long mapId;

    /** $column.columnComment */
    @Excel(name = "图集id")
    private String name;

    /** $column.columnComment */
    @Excel(name = "图集id")
    private Long status;

    /** 图片总数 */
    @Excel(name = "图片总数")
    private Long total;

    /** $column.columnComment */
    private Long delFlag;

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
    public void setMapId(Long mapId) 
    {
        this.mapId = mapId;
    }

    public Long getMapId() 
    {
        return mapId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setTotal(Long total) 
    {
        this.total = total;
    }

    public Long getTotal() 
    {
        return total;
    }
    public void setDelFlag(Long delFlag) 
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag() 
    {
        return delFlag;
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
