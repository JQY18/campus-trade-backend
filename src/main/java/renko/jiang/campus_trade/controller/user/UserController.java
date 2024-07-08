package renko.jiang.campus_trade.controller.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import renko.jiang.campus_trade.pojo.dto.LoginDTO;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.result.Result;
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

    @PostMapping(value = "/login")
    public Result login(@RequestBody LoginDTO loginDTO) {

        User user = userService.login(loginDTO);

        if(user == null){
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

        System.out.println(token);
        return Result.success(userLoginVO);
    }

    @PostMapping("/register")
    public Result register(@RequestBody LoginDTO loginDTO){
        User user = userService.login(loginDTO);
        if(user != null){
            Result.error("用户已存在");
        }
        userService.register(loginDTO);
        return Result.success();
    }

}
