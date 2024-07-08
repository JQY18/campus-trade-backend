package renko.jiang.campus_trade.pojo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {
    private Integer id;
    private String productName;
    private String price;
    private String description;
    private String image;
    private Integer userId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
