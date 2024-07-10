package renko.jiang.campus_trade.pojo.dto;

import lombok.Data;

@Data
public class PostSearchDTO {
    private Integer userId;
    private String category;
    private String title;
    private String content;
}
