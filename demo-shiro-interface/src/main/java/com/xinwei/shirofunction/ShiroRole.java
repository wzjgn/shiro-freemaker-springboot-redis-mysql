package com.xinwei.shirofunction;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "shiro_role")
@Data
public class ShiroRole implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;
    private Date createTime;
   
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shiro_role_function", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {
            @JoinColumn(name = "function_id")})
    private List<Function> functionList;// 一个角色具有多个权限
    
    
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "shiro_user_role", joinColumns = {@JoinColumn(name = "role_id")}, inverseJoinColumns = {
//            @JoinColumn(name = "user_id")})
//    private List<ShiroUser> userList;// 一个角色对应多个用户

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

//    public List<ShiroRoleFunction> getPermissionList() {
//        return permissionList;
//    }
//
//    public void setPermissionList(List<ShiroRoleFunction> permissionList) {
//        this.permissionList = permissionList;
//    }

//    public List<ShiroUser> getUserList() {
//        return userList;
//    }
//
//    public void setUserList(List<ShiroUser> userList) {
//        this.userList = userList;
//    }

// 省略 get set 方法
//
    @Transient
    public List<String> getPermissionsName() {
        List<String> list = new ArrayList<String>();
        List<Function> perlist = getFunctionList();
        for (Function per : perlist) {
            list.add(per.getPermissionName());
        }
        return list;
    }
}