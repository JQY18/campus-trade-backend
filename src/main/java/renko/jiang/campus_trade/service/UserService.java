package renko.jiang.campus_trade.service;

import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.vo.UserInfoVO;

public interface UserService {
    User login(LoginDTO loginDTO);

    void register(LoginDTO loginDTO);

    UserInfoVO getUserInfoById(Integer userId);

    void updateUserInfo(UserInfoVO userInfoVO);

    void updateAvatar(Integer id, MultipartFile avatar);

    void updatePassword(Integer id, String currentPassword, String newPassword);

    void deleteUser(Integer id);
}
