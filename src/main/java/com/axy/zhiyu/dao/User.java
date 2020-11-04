package com.axy.zhiyu.dao;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;



@Data
@TableName(value = "zf_user")
public class User {
  @Id
  private String id;

@TableField(value = "user_name")
  private String username;

  private String nickName;

  private String password;

  private String avatar;

  private Date lastLoginTime;

  private double balance;

  private Date createTime;

  private Date modifiedTime;

  private long isDelete;

  private long status;

  private long inviteCode;

  @TableField(exist = false)
  private String token;

  @TableField(exist = false)
  private String sign;
}
