package io.kid19999.backstage.repository;

import io.kid19999.backstage.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author kid1999
 * @title: ManagerRepository
 * @date 2019/11/24 10:07
 */

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	@Query(value = "select * from admin m where m.admin_id = ?1 and m.admin_pwd = ?2" ,nativeQuery = true)
	Admin login(String studentId, String passWord);

	Admin findByAdminId(String adminId);

}
