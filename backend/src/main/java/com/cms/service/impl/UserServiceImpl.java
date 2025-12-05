package com.cms.service.impl;

import com.cms.entity.User;
import com.cms.dao.UserMapper;
import com.cms.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 用户Service实现类
 * 实现用户管理的业务逻辑
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectUserByUsername(username);
    }

    @Override
    public User getUserByUserId(String userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.selectAllUsers();
    }

    @Override
    @Transactional
    public User addUser(User user) {
        logger.info("添加用户，用户名：{}", user.getUsername());
        user.setCreateTime(new Date());
        user.setStatus(1); // 默认启用
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insertUser(user);
        logger.info("用户添加成功，用户ID：{}", user.getUserId());
        return user;
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        logger.info("更新用户，用户ID：{}", user.getUserId());
        // 获取原用户信息
        User originalUser = userMapper.selectUserByUserId(user.getUserId());
        // 检查密码是否被修改，如果修改了则加密，否则保持原密码
        if (originalUser != null && !originalUser.getPassword().equals(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            logger.info("用户密码已更新，用户ID：{}", user.getUserId());
        }
        userMapper.updateUser(user);
        User updatedUser = userMapper.selectUserByUserId(user.getUserId());
        logger.info("用户更新成功，用户ID：{}", updatedUser.getUserId());
        return updatedUser;
    }

    @Override
    @Transactional
    public boolean deleteUser(String userId) {
        logger.info("删除用户，用户ID：{}", userId);
        boolean result = userMapper.deleteUser(userId) > 0;
        if (result) {
            logger.info("用户删除成功，用户ID：{}", userId);
        } else {
            logger.warn("用户删除失败，用户ID：{}", userId);
        }
        return result;
    }

    @Override
    public List<User> getUsersByRole(String role) {
        return userMapper.selectUsersByRole(role);
    }

    @Override
    public List<User> getUsersByDept(String dept) {
        return userMapper.selectUsersByDept(dept);
    }

    @Override
    @Transactional
    public boolean disableUser(String userId) {
        logger.info("禁用用户，用户ID：{}", userId);
        User user = userMapper.selectUserByUserId(userId);
        if (user != null) {
            user.setStatus(0);
            boolean result = userMapper.updateUser(user) > 0;
            if (result) {
                logger.info("用户禁用成功，用户ID：{}", userId);
            } else {
                logger.warn("用户禁用失败，用户ID：{}", userId);
            }
            return result;
        }
        logger.warn("用户不存在，无法禁用，用户ID：{}", userId);
        return false;
    }

    @Override
    @Transactional
    public boolean enableUser(String userId) {
        logger.info("启用用户，用户ID：{}", userId);
        User user = userMapper.selectUserByUserId(userId);
        if (user != null) {
            user.setStatus(1);
            boolean result = userMapper.updateUser(user) > 0;
            if (result) {
                logger.info("用户启用成功，用户ID：{}", userId);
            } else {
                logger.warn("用户启用失败，用户ID：{}", userId);
            }
            return result;
        }
        logger.warn("用户不存在，无法启用，用户ID：{}", userId);
        return false;
    }
}