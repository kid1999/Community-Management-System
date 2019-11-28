package io.kid19999.backstage.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * @author kid1999
 * @title: Manager
 * @date 2019/11/24 9:53
 */

@Entity
@Data
public class Admin {

	@Id
	private String adminId;
	@Column(nullable = false,unique = true)
	private String adminName;
	@Column(nullable = false,unique = true)
	private String adminPwd;
	@Column(nullable = false,unique = true)
	private String adminEmail;
	private String adminPhone;
	private String adminAvatar;
	private String adminQq;
	@CreationTimestamp
	private Timestamp adminCreateTime;
	private String adminRole;
}
