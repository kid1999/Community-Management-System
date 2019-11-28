package io.kid19999.backstage.repository;

import io.kid19999.backstage.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kid1999
 * @title: MemberRepository
 * @date 2019/11/24 9:45
 */

public interface MemberRepository extends JpaRepository<Member, Integer> {

}
