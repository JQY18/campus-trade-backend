package renko.jiang.campus_trade.controller.user;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.result.Result;
import renko.jiang.campus_trade.pojo.vo.UserInfoVO;
import renko.jiang.campus_trade.pojo.vo.UserLoginVO;
import renko.jiang.campus_trade.properties.JwtProperties;
import renko.jiang.campus_trade.service.UserService;
import renko.jiang.campus_trade.utils.JwtUtil;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param loginDTO
     * @return
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginDTO loginDTO, HttpServletRequest request) {

        User user = userService.login(loginDTO);

        if (user == null) {
            return Result.error("账号或密码错误!");
        }

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);

        UserLoginVO userLoginVO = new UserLoginVO();
        userLoginVO.setId(user.getId());
        userLoginVO.setUsername(user.getUsername());
        userLoginVO.setNickname(user.getNickname());
        userLoginVO.setToken(token);

        request.getSession().setAttribute("userId", user.getId());

        System.out.println("userId: " + user.getId());
        return Result.success(userLoginVO);
    }

    /**
     * 注册
     *
     * @param loginDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        if (user != null) {
            Result.error("用户已存在");
        }
        userService.register(loginDTO);
        return Result.success();
    }

    @GetMapping("/authentic")
    public Result<Integer> getAuthentic(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        if (userId == null) {
            return Result.error("用户未登录");
        }
        return Result.success(userId);
    }


    /**
     * 获取用户信息
     *
     * @param postUserId
     * @return
     */
    @GetMapping("/info")
    public Result<UserInfoVO> getUserInfoById(Integer postUserId) {
        UserInfoVO userInfo = userService.getUserInfoById(postUserId);
        return Result.success(userInfo);
    }

    /**
     * 更新用户信息
     *
     * @param userInfoVO
     * @return
     */
    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody UserInfoVO userInfoVO) {
        userService.updateUserInfo(userInfoVO);
        return Result.success();
    }

    /**
     * 修改用户头像
     *
     * @param id     用户id
     * @param avatar
     */
    @PostMapping("/avatar")
    public Result updateAvatar(Integer id, MultipartFile avatar) {
        userService.updateAvatar(id, avatar);
        return Result.success();
    }

    /**
     * 修改密码
     *
     * @param params
     */
    @PostMapping("/password")
    public Result updatePassword(@RequestBody Map<String, Object> params) {
        userService.updatePassword((Integer) params.get("id"), (String) params.get("currentPassword"), (String) params.get("newPassword"));
        return Result.success();
    }
}
