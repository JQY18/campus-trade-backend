package renko.jiang.campus_trade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;

@Mapper
public interface UserMapper {
    //根据用户名查询用户信息
    @Select("select * from user where username = #{username}")
    User queryUser(String username);

    //添加用户
    @Select("insert into user (username, password, nickname) values (#{username}, #{password}, #{nickname})")
    void addUser(LoginDTO loginDTO);

    //根据uerId查询用户信息
    @Select("select * from user where id = #{userId}")
    User queryUserById(Integer userId);

    //修改用户信息
    void updateUser(User user);
}
