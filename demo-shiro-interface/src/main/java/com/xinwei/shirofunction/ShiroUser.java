package com.xinwei.shirofunction;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import lombok.Data;


@Entity
@Table(name = "shiro_user")
@Data
public class ShiroUser implements java.io.Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    private String username;
     
    private String password;
    
   
    private Date createTime;
   
    private String status;//用户状态:0-注册用户未审核，1-注册用户已审核 ，2-锁定
    


    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "shiro_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {
            @JoinColumn(name = "role_id")})
    private List<ShiroRole> roleList;// 一个用户具有多个角色

    public ShiroUser() {
        super();
    }

    public ShiroUser(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public List<ShiroRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<ShiroRole> roleList) {
        this.roleList = roleList;
    }
    
    @Transient
    public Set<String> getRolesName() {
        List<ShiroRole> roles = getRoleList();
        Set<String> set = new HashSet<String>();
        for (ShiroRole role : roles) {
            set.add(role.getRoleName());
        }
        return set;
    }

	 

}