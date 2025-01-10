package renko.jiang.campus_trade.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import renko.jiang.campus_trade.pojo.entity.User;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("select id from admin where username = #{username} and password = #{password}")
    Integer login(String username, String password);

    @Select("select * from user")
    List<User> getList();

}
