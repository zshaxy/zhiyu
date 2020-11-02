package com.axy.zhiyu.bean;


import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
public class User {
  private String id;
  private String username;
  private String nickName;
  private String password;
  private String avatar;
  private java.sql.Timestamp lastLoginTime;
  private double balance;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp modifiedTime;
  private long isDelete;
  private long status;
  private long inviteCode;

  @TableField(exist = false)
  private String token;

  @TableField(exist = false)
  private String sign;
}
