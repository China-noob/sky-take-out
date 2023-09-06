package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author lpl
 * @date 2023-09-05 15:45
 * @description:.
 * @version: 1.0.0
 */
@Mapper
public interface UserMapper {
    @Select("select * from user where openid = #{openid}")
    public User getByOpenid(String openid) ;

    void insert(User user);
}
