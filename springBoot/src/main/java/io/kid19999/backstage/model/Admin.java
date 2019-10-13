package io.kid19999.backstage.model;

import lombok.Data;

import java.sql.Date;

@Data
public class Admin {
  private String adminId;
  private String adminName;
  private String adminPwd;
  private String adminEmail;
  private String adminPhone;
  private String adminQq;
  private String invitationCode;
  private Date adminCreateTime;
}
