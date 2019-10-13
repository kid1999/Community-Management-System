package io.kid19999.backstage.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kid19999.backstage.model.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
public interface adminMapper extends BaseMapper<Admin> {

	@Select("select * from admin where admin_id = #{id} and admin_pwd = #{pwd}")
	Admin toLogin(@Param("id") String adminId,@Param("pwd") String adminPwd);
}
