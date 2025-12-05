package com.cms.service.impl;

import com.cms.entity.User;
import com.cms.dao.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        testUser = new User();
        testUser.setUserId("U001");
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setRole("学生");
        testUser.setName("测试用户");
        testUser.setDept("计算机学院");
        testUser.setPhone("13800138000");
        testUser.setEmail("test@example.com");
        testUser.setStatus(1);
    }

    @Test
    void testGetUserByUsername() {
        when(userMapper.selectUserByUsername("testuser")).thenReturn(testUser);
        User user = userService.getUserByUsername("testuser");
        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        verify(userMapper, times(1)).selectUserByUsername("testuser");
    }

    @Test
    void testGetUserByUserId() {
        when(userMapper.selectUserByUserId("U001")).thenReturn(testUser);
        User user = userService.getUserByUserId("U001");
        assertNotNull(user);
        assertEquals("U001", user.getUserId());
        verify(userMapper, times(1)).selectUserByUserId("U001");
    }

    @Test
    void testAddUser() {
        when(userMapper.insertUser(any(User.class))).thenReturn(1);
        User createdUser = userService.addUser(testUser);
        assertNotNull(createdUser);
        assertEquals("U001", createdUser.getUserId());
        verify(userMapper, times(1)).insertUser(any(User.class));
    }

    @Test
    void testUpdateUser() {
        when(userMapper.updateUser(any(User.class))).thenReturn(1);
        when(userMapper.selectUserByUserId("U001")).thenReturn(testUser);
        testUser.setName("更新后的测试用户");
        User updatedUser = userService.updateUser(testUser);
        assertNotNull(updatedUser);
        assertEquals("更新后的测试用户", updatedUser.getName());
        verify(userMapper, times(1)).updateUser(any(User.class));
        verify(userMapper, times(1)).selectUserByUserId("U001");
    }

    @Test
    void testDeleteUser() {
        when(userMapper.deleteUser("U001")).thenReturn(1);
        boolean result = userService.deleteUser("U001");
        assertTrue(result);
        verify(userMapper, times(1)).deleteUser("U001");
    }

    @Test
    void testDisableUser() {
        when(userMapper.selectUserByUserId("U001")).thenReturn(testUser);
        when(userMapper.updateUser(any(User.class))).thenReturn(1);
        boolean result = userService.disableUser("U001");
        assertTrue(result);
        verify(userMapper, times(1)).selectUserByUserId("U001");
        verify(userMapper, times(1)).updateUser(any(User.class));
    }

    @Test
    void testEnableUser() {
        testUser.setStatus(0); // 设置为禁用状态
        when(userMapper.selectUserByUserId("U001")).thenReturn(testUser);
        when(userMapper.updateUser(any(User.class))).thenReturn(1);
        boolean result = userService.enableUser("U001");
        assertTrue(result);
        verify(userMapper, times(1)).selectUserByUserId("U001");
        verify(userMapper, times(1)).updateUser(any(User.class));
    }
}