package renko.jiang.campus_trade.pojo.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentVO {
    // 评论主键id
    private Integer id;
    // 帖子id
    private Integer postId;
    // 评论者id
    private Integer commenterId;
    // 评论者昵称
    private String commenterNickname;
    // 评论者头像
    private String commenterAvatar;
    // 内容
    private String content;
    // 喜欢数
    private Integer like;
    // 评论时间
    private LocalDateTime createTime;
    // 回复数组
    private List<Reply> reply;
    // 当前用户是否点赞过
    private Boolean isLiked;
}
