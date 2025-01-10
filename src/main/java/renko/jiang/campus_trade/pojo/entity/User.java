package renko.jiang.campus_trade.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @TableName users
 */
@Data
public class User implements Serializable {

    private Integer id;

    private Integer age;

    private String username;

    private String password;

    private String nickname;

    private String gender;

    private String school;

    private String phoneNumber;

    private String email;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
