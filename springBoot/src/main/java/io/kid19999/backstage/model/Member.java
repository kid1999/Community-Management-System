package io.kid19999.backstage.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author kid1999
 * @title: 会员信息
 * @date 2019/11/24 9:33
 */

@Data
@Entity
public class Member {
	@Id
	@Column(nullable = false,unique = true)
	private int memId;
	private String memName;
	private String menGender;
	private String memPhone;
	private String memQq;
	private String memClass;
	private String memCollege;
	private String memJoinTime;
	private int memPayCheck;

}
