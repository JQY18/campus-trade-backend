package renko.jiang.campus_trade.service;

import renko.jiang.campus_trade.pojo.dto.CommentDTO;
import renko.jiang.campus_trade.pojo.vo.CommentVO;

import java.util.List;

public interface CommentService {
    List<CommentVO> getCommentsByPostId(Integer postId,Integer userId);

    void addComment(CommentDTO commentDTO);
}
