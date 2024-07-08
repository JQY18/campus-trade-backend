package renko.jiang.campus_trade.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reply {
    private Integer id;
    // 父评论id
    private Integer fatherId;
    // 评论者id
    private Integer commenterId;
    // 评论者昵称
    private String commenterNickname;
    // 评论者头像
    private String commenterAvatar;
    // 评论内容
    private String content;
    // 回复者id
    private Integer replierId;
    // 回复者昵称
    private String replierNickname;
    // 回复者头像
    private String replierAvatar;
    // 回复时间
    private LocalDateTime createTime;
}
