package io.kid19999.backstage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author kid1999
 * @title: 登录日志
 * @date 2019/11/24 14:24
 */

@Entity
@Data
public class SignLog {
	@Id
	@GeneratedValue
	private int signId;
	private String signName;
	private String signIp;
	private String signUa;
	private Timestamp signTime;
}
