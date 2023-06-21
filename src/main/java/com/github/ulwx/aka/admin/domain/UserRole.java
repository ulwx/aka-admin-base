package com.github.ulwx.aka.admin.domain;

public class UserRole {
    private int id;
    private String name;
    //角色类型编码，一般对应岗位，一般对此进行程序硬编码，处理数据权限，比如领导可以看到下级的数据，业务员只能看到自己的数据
    private int roleTypeCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoleTypeCode() {
        return roleTypeCode;
    }

    public void setRoleTypeCode(int roleTypeCode) {
        this.roleTypeCode = roleTypeCode;
    }
}
