package io.kid19999.backstage.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Admin {
  private String admin_id;
  private String admin_name;
  private String admin_pwd;
  private String admin_email;
  private String admin_phone;
  private String admin_qq;
  private String invitation_code;
  private Date admin_create_time;
}
