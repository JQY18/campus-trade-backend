package renko.jiang.campus_trade.service;

import renko.jiang.campus_trade.pojo.entity.User;

import java.util.List;

public interface AdminService {

    Integer login(String username, String password);

    List<User> getList();

    void delete(Integer id);
}
