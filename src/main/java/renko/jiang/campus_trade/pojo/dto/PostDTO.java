package renko.jiang.campus_trade.pojo.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostDTO {
    private Integer userId;
    private Integer category;
    private String title;
    private String content;
    private List<MultipartFile> images;
}
