package com.java.medtrach.model;

public class RoleModel {
    private String roleId, userId;
    private int roleType;

    public RoleModel() {
    }

    public RoleModel(String roleId, String userId, int roleType) {
        this.roleId = roleId;
        this.userId = userId;
        this.roleType = roleType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getRoleType() {
        return roleType;
    }

    public void setRoleType(int roleType) {
        this.roleType = roleType;
    }
}
