package renko.jiang.campus_trade.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private Integer id;
    private String username;
    private String nickname;
    private String token;
}
