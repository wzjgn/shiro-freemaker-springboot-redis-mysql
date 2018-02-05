package com.xinwei.shirofunction;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 系统功能目录
 * @author ouburikou
 *
 */
@Entity
@Table(name = "shiro_function")
@Data
public class Function implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
	private String functionName;
	private Date createTime;
	
	private Integer pid;
	
	private String permissionName;//securityApply:templetUpload
	
}
