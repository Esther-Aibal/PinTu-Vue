package com.ruoyi.project.ab.service;

import com.ruoyi.project.ab.domain.AbUser;
import java.util.List;

/**
 * 用户Service接口
 * 
 * @author aibal
 * @date 2019-12-23
 */
public interface IAbUserService 
{
    /**
     * 查询用户
     * 
     * @param id 用户ID
     * @return 用户
     */
    public AbUser selectAbUserById(String id);

    /**
     * 查询用户列表
     * 
     * @param abUser 用户
     * @return 用户集合
     */
    public List<AbUser> selectAbUserList(AbUser abUser);

    /**
     * 新增用户
     * 
     * @param abUser 用户
     * @return 结果
     */
    public int insertAbUser(AbUser abUser);

    /**
     * 修改用户
     * 
     * @param abUser 用户
     * @return 结果
     */
    public int updateAbUser(AbUser abUser);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户ID
     * @return 结果
     */
    public int deleteAbUserByIds(String[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户ID
     * @return 结果
     */
    public int deleteAbUserById(String id);
}
