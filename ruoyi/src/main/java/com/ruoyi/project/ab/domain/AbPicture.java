package com.ruoyi.project.ab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 图片对象 ab_picture
 * 
 * @author aibal
 * @date 2019-12-23
 */
public class AbPicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 图片地址 */
    private String imgUrl;

    /** 图集id */
    @Excel(name = "图集id")
    private Long atlasId;

    /** 行列单元格数 */
    @Excel(name = "行列单元格数")
    private Long blockNum;

    /** $column.columnComment */
    @Excel(name = "行列单元格数")
    private String name;

    /** 需选择单元格数组 */
    private String chooseArray;

    /** $column.columnComment */
    @Excel(name = "行列单元格数")
    private Long status;

    /** $column.columnComment */
    private Long delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
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
    public void setAtlasId(Long atlasId) 
    {
        this.atlasId = atlasId;
    }

    public Long getAtlasId() 
    {
        return atlasId;
    }
    public void setBlockNum(Long blockNum) 
    {
        this.blockNum = blockNum;
    }

    public Long getBlockNum() 
    {
        return blockNum;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setChooseArray(String chooseArray) 
    {
        this.chooseArray = chooseArray;
    }

    public String getChooseArray() 
    {
        return chooseArray;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
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
            .append("atlasId", getAtlasId())
            .append("blockNum", getBlockNum())
            .append("name", getName())
            .append("chooseArray", getChooseArray())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
