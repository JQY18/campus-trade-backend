package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renko.jiang.campus_trade.mapper.UserMapper;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(LoginDTO loginDTO) {
        User user = userMapper.queryUser(loginDTO.getUsername());
        if (user != null && user.getPassword().equals(loginDTO.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public void register(LoginDTO loginDTO) {
        userMapper.addUser(loginDTO);
    }

}
