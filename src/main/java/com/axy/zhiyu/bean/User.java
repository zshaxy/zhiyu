package com.axy.zhiyu.bean;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;

@Data
public class User {
  @TableId(type = IdType.ASSIGN_ID)
  private String id;

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
