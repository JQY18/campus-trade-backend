package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renko.jiang.campus_trade.mapper.AdminMapper;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.service.AdminService;
import renko.jiang.campus_trade.service.UserService;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private UserService userService;

    @Override
    public Integer login(String username, String password) {
        Integer id = adminMapper.login(username, password);
        if (id == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        return id;
    }

    @Override
    public List<User> getList() {
        List<User> list = adminMapper.getList();
        return list;
    }

    @Override
    public void delete(Integer id) {
        userService.deleteUser(id);
    }
}
