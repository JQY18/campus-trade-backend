package renko.jiang.campus_trade.pojo.dto;

import lombok.Data;

@Data
public class ReplyDTO {
    //父评论id
    private Integer fatherId;
    //评论者id
    private Integer commenterId;
    //回复者id
    private Integer replierId;
    //回复内容
    private String content;
}
