package com.ruoyi.project.ab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务对象 ab_task
 * 
 * @author aibal
 * @date 2019-12-23
 */
public class AbTask extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String id;

    /** 任务内容 */
    private String content;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 规则 */
    @Excel(name = "规则")
    private String rule;

    /** $column.columnComment */
    @Excel(name = "规则")
    private Long status;

    /** $column.columnComment */
    private Long delFlag;

    /** 任务类型 1成长任务 2日常任务 */
    @Excel(name = "任务类型 1成长任务 2日常任务")
    private Long type;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setRule(String rule) 
    {
        this.rule = rule;
    }

    public String getRule() 
    {
        return rule;
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
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("title", getTitle())
            .append("rule", getRule())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .append("type", getType())
            .toString();
    }
}
