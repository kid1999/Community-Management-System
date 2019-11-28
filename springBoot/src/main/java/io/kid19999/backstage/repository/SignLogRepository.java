package io.kid19999.backstage.repository;

import io.kid19999.backstage.model.SignLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author kid1999
 * @title: SignLogRepository
 * @date 2019/11/24 14:29
 */

public interface SignLogRepository extends JpaRepository<SignLog, Integer> {
}
