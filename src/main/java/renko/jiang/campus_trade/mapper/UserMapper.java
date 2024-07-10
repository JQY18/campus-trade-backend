package renko.jiang.campus_trade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.vo.UserInfoVO;

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

    @Select("select id,username,nickname,avatar,school from user where id = #{userId}")
    UserInfoVO getUserInfoById(Integer userId);

    // 修改用户信息
    void updateUserInfo(UserInfoVO userInfoVO);

    @Update("update user set avatar = #{avatar} where id = #{id}")
    void updateAvatar(Integer id, String avatar);

    @Update("update user set password = #{newPassword} where id = #{id} and password = #{currentPassword}")
    int updatePassword(Integer id, String currentPassword, String newPassword);
}
