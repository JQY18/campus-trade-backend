package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void likeComment(Integer commentId, Integer likerId,Integer to,boolean isLiked) {
        // 点完之后的状态
        if (isLiked){
            // 点赞
            commentMapper.likeComment(commentId,likerId);
        }else{
            //取消点赞
            commentMapper.deleteCommentLike(commentId,likerId);
        }
        // 数量变化
        commentMapper.addLikeCount(commentId,to);
    }
}
