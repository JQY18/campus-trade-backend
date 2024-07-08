package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renko.jiang.campus_trade.mapper.CommentMapper;
import renko.jiang.campus_trade.pojo.dto.CommentDTO;
import renko.jiang.campus_trade.pojo.vo.CommentVO;
import renko.jiang.campus_trade.service.CommentService;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<CommentVO> getCommentsByPostId(Integer postId,Integer userId) {
        List<CommentVO> comments = commentMapper.getCommentsByPostId(postId,userId);

        return comments;
    }

    @Override
    public void addComment(CommentDTO commentDTO) {
        commentMapper.addComment(commentDTO);
    }
}
