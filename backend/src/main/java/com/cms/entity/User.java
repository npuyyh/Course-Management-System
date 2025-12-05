package com.cms.entity;

import jakarta.validation.constraints.*;

import java.util.Date;

public class User {
    @NotBlank(message = "用户ID不能为空")
    @Size(max = 10, message = "用户ID长度不能超过10个字符")
    private String userId;

    @NotBlank(message = "用户名不能为空")
    @Size(max = 10, message = "用户名长度不能超过10个字符")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, message = "密码长度不能少于6个字符")
    private String password;

    @NotBlank(message = "角色不能为空")
    @Pattern(regexp = "^(学生|教师|管理员)$", message = "角色必须是学生、教师或管理员")
    private String role;

    @NotBlank(message = "姓名不能为空")
    @Size(max = 10, message = "姓名长度不能超过10个字符")
    private String name;

    @NotBlank(message = "院系不能为空")
    @Size(max = 20, message = "院系长度不能超过20个字符")
    private String dept;

    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private Integer status;
    private Date createTime;

    // Getters and setters
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}