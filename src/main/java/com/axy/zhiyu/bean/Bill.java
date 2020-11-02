package com.axy.zhiyu.bean;


import lombok.Data;

@Data
public class Bill {

  private String id;
  private String userId;
  private String categoryId;
  private String categoryName;
  private double money;
  private java.sql.Timestamp createTime;

}
