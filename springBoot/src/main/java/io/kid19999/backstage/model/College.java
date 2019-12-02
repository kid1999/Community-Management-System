package io.kid19999.backstage.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @desc:  学院表
 * @auther: kid1999
 * @date: 2019/12/2 17:29
 **/

@Data
@Table
@Entity
public class College {
    @Id
    @GeneratedValue
    private int collegeId;
    private String collegeName;
}
