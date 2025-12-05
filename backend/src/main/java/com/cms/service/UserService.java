package com.cms.service;

import com.cms.entity.User;

import java.util.List;

/**
 * 用户Service接口
 * 定义用户管理的业务逻辑方法
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User getUserByUsername(String username);

    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 用户实体
     */
    User getUserByUserId(String userId);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> getAllUsers();

    /**
     * 新增用户
     * @param user 用户实体
     * @return 用户实体
     */
    User addUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     * @return 用户实体
     */
    User updateUser(User user);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean deleteUser(String userId);

    /**
     * 根据角色查询用户
     * @param role 角色
     * @return 用户列表
     */
    List<User> getUsersByRole(String role);

    /**
     * 根据院系查询用户
     * @param dept 院系
     * @return 用户列表
     */
    List<User> getUsersByDept(String dept);

    /**
     * 禁用用户
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean disableUser(String userId);

    /**
     * 启用用户
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean enableUser(String userId);
}