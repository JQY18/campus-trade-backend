package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.mapper.UserMapper;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.vo.UserInfoVO;
import renko.jiang.campus_trade.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Value("${upload.path}")
    private String path;

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
        try {
            userMapper.addUser(loginDTO);
        } catch(Exception e) {
            throw new RuntimeException("用户名已存在");
        }
    }

    @Override
    public UserInfoVO getUserInfoById(Integer userId) {
        UserInfoVO userInfo = userMapper.getUserInfoById(userId);
        return userInfo;
    }

    @Override
    public void updateUserInfo(UserInfoVO userInfoVO) {
        userMapper.updateUserInfo(userInfoVO);
    }

    @Override
    public void updateAvatar(Integer id, MultipartFile avatar) {
        String url = handleSingleFileUpload(avatar);
        userMapper.updateAvatar(id, url);
    }

    //修改密码
    @Override
    public void updatePassword(Integer id, String currentPassword, String newPassword) {
        int update = userMapper.updatePassword(id, currentPassword, newPassword);
        if (update == 0) {
            throw new RuntimeException("密码错误");
        }
    }

    //处理图片上传并且返回图片的静态资源映射地址
    public String handleSingleFileUpload(MultipartFile avatar) {
        try {
            // 生成UUID
            String uuid = UUID.randomUUID().toString();
            // 文件扩展名
            String originalFilename = avatar.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 构建新的文件名
            String newFileName = uuid + fileExtension;

            // 构建文件路径
            Path path = Paths.get(this.path, newFileName);
            // 将MultipartFile写入文件系统
            Files.write(path, avatar.getBytes());

            // 收集该帖子id对应的图片的静态资源映射地址
            return "http://localhost:8080/image/" + newFileName;

        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

}
