package com.cms.controller;

import com.cms.entity.User;
import com.cms.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 用户控制器
 * 处理用户管理的RESTful API请求
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取所有用户
     * @return 用户列表
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * 根据用户ID获取用户
     * @param userId 用户ID
     * @return 用户实体
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable String userId) {
        User user = userService.getUserByUserId(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 创建用户
     * @param user 用户实体
     * @return 创建的用户
     */
    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.addUser(user);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * 更新用户
     * @param userId 用户ID
     * @param user 用户实体
     * @return 更新后的用户
     */
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable String userId, @Valid @RequestBody User user) {
        user.setUserId(userId);
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * 删除用户
     * @param userId 用户ID
     * @return 删除结果
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String userId) {
        boolean success = userService.deleteUser(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户删除成功"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "用户删除失败"));
        }
    }

    /**
     * 根据角色获取用户列表
     * @param role 角色
     * @return 用户列表
     */
    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable String role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }

    /**
     * 根据院系获取用户列表
     * @param dept 院系
     * @return 用户列表
     */
    @GetMapping("/dept/{dept}")
    public ResponseEntity<List<User>> getUsersByDept(@PathVariable String dept) {
        List<User> users = userService.getUsersByDept(dept);
        return ResponseEntity.ok(users);
    }

    /**
     * 禁用用户
     * @param userId 用户ID
     * @return 操作结果
     */
    @PutMapping("/{userId}/disable")
    public ResponseEntity<Map<String, String>> disableUser(@PathVariable String userId) {
        boolean success = userService.disableUser(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户禁用成功"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "用户禁用失败"));
        }
    }

    /**
     * 启用用户
     * @param userId 用户ID
     * @return 操作结果
     */
    @PutMapping("/{userId}/enable")
    public ResponseEntity<Map<String, String>> enableUser(@PathVariable String userId) {
        boolean success = userService.enableUser(userId);
        if (success) {
            return ResponseEntity.ok(Map.of("message", "用户启用成功"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("message", "用户启用失败"));
        }
    }

    /**
     * 获取当前用户信息
     * @return 当前用户信息
     */
    @GetMapping("/profile")
    public ResponseEntity<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 更新当前用户信息
     * @param user 用户实体
     * @return 更新后的用户
     */
    @PutMapping("/profile")
    public ResponseEntity<User> updateCurrentUser(@RequestBody User user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userService.getUserByUsername(username);
        if (currentUser == null) {
            return ResponseEntity.notFound().build();
        }
        user.setUserId(currentUser.getUserId());
        user.setUsername(currentUser.getUsername());
        user.setPassword(currentUser.getPassword());
        user.setRole(currentUser.getRole());
        User updatedUser = userService.updateUser(user);
        return ResponseEntity.ok(updatedUser);
    }
}