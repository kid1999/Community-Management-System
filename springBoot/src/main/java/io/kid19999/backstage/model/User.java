package io.kid19999.backstage.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
  private String userId;
  private String userName;
  private String userClass;
  private String userEmail;
  private String userPhone;
  private String userQq;
  private Timestamp userCreateTime;
}
