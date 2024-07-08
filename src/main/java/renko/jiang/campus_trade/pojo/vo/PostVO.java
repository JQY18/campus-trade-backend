package renko.jiang.campus_trade.pojo.vo;

import lombok.Data;

@Data
public class PostVO {
    private Integer id;
    private String title;
    private String image;
    private String content;
    private String createTime;
    private String updateTime;
}
