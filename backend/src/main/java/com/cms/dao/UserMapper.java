package com.cms.dao;

import com.cms.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户DAO接口
 * 定义用户表的数据库操作方法
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户实体
     */
    User selectUserByUsername(@Param("username") String username);

    /**
     * 根据用户ID查询用户
     * @param userId 用户ID
     * @return 用户实体
     */
    User selectUserByUserId(@Param("userId") String userId);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    List<User> selectAllUsers();

    /**
     * 插入用户
     * @param user 用户实体
     * @return 影响行数
     */
    int insertUser(User user);

    /**
     * 更新用户
     * @param user 用户实体
     * @return 影响行数
     */
    int updateUser(User user);

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 影响行数
     */
    int deleteUser(@Param("userId") String userId);

    /**
     * 根据角色查询用户
     * @param role 角色
     * @return 用户列表
     */
    List<User> selectUsersByRole(@Param("role") String role);

    /**
     * 根据院系查询用户
     * @param dept 院系
     * @return 用户列表
     */
    List<User> selectUsersByDept(@Param("dept") String dept);
}