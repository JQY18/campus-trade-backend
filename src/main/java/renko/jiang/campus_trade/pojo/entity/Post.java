package renko.jiang.campus_trade.pojo.entity;

import lombok.Data;

@Data
public class Post {
    private Integer id;
    private Integer userId;
    private Integer category;
    private String title;
    private String content;
    private String createTime;
    private String updateTime;
}
