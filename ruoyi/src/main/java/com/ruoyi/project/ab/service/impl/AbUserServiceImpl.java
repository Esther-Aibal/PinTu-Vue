package com.ruoyi.project.ab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.ab.mapper.AbUserMapper;
import com.ruoyi.project.ab.domain.AbUser;
import com.ruoyi.project.ab.service.IAbUserService;

/**
 * 用户Service业务层处理
 * 
 * @author aibal
 * @date 2019-12-23
 */
@Service
public class AbUserServiceImpl implements IAbUserService 
{
    @Autowired
    private AbUserMapper abUserMapper;

    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    @Override
    public AbUser selectAbUserById(String id)
    {
        return abUserMapper.selectAbUserById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param abUser 用户
     * @return 用户
     */
    @Override
    public List<AbUser> selectAbUserList(AbUser abUser)
    {
        return abUserMapper.selectAbUserList(abUser);
    }

    /**
     * 新增用户
     * 
     * @param abUser 用户
     * @return 结果
     */
    @Override
    public int insertAbUser(AbUser abUser)
    {
        abUser.setCreateTime(DateUtils.getNowDate());
        return abUserMapper.insertAbUser(abUser);
    }

    /**
     * 修改用户
     * 
     * @param abUser 用户
     * @return 结果
     */
    @Override
    public int updateAbUser(AbUser abUser)
    {
        abUser.setUpdateTime(DateUtils.getNowDate());
        return abUserMapper.updateAbUser(abUser);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    @Override
    public int deleteAbUserByIds(String[] ids)
    {
        return abUserMapper.deleteAbUserByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    @Override
    public int deleteAbUserById(String id)
    {
        return abUserMapper.deleteAbUserById(id);
    }
}
