package io.kid19999.backstage.repository;

import io.kid19999.backstage.model.College;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @desc:
 * @auther: kid1999
 * @date: 2019/12/2 17:31
 **/
public interface collegeRepository extends JpaRepository<College,Integer> {
}
