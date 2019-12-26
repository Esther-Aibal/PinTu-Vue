package com.ruoyi.project.ab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户解锁图集对象 ab_user_atlas
 * 
 * @author aibal
 * @date 2019-12-26
 */
public class AbUserAtlas extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String userId;

    /** $column.columnComment */
    private String atlasId;

    /** 解锁图片数 */
    private Long unlockedNum;

    public void setUserId(String userId) 
    {
        this.userId = userId;
    }

    public String getUserId() 
    {
        return userId;
    }
    public void setAtlasId(String atlasId) 
    {
        this.atlasId = atlasId;
    }

    public String getAtlasId() 
    {
        return atlasId;
    }
    public void setUnlockedNum(Long unlockedNum) 
    {
        this.unlockedNum = unlockedNum;
    }

    public Long getUnlockedNum() 
    {
        return unlockedNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("atlasId", getAtlasId())
            .append("createTime", getCreateTime())
            .append("unlockedNum", getUnlockedNum())
            .toString();
    }
}
