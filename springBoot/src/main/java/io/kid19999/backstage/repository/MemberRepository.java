package io.kid19999.backstage.repository;

import io.kid19999.backstage.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author kid1999
 * @title: MemberRepository
 * @date 2019/11/24 9:45
 */

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findMemberByMemId(String memberId);

    @Transactional
    @Modifying
    @Query(value = "delete from member where mem_id = ?1",nativeQuery = true)
    void deleteByMemId(String id);


    List<Member> findByMemIdLike(String memId);

    @Query(value = "SELECT u FROM Member u WHERE u.memName like ?1 ")
    List<Member> findMembersByMemNameLike(String memName);



}
