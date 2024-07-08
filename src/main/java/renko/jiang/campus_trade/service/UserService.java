package renko.jiang.campus_trade.service;

import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;

public interface UserService {
    User login(LoginDTO loginDTO);

    void register(LoginDTO loginDTO);
}
