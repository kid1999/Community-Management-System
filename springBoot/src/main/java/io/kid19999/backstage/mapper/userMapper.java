package io.kid19999.backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.kid19999.backstage.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper extends BaseMapper<User> {

}
