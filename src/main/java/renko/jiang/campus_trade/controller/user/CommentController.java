package renko.jiang.campus_trade.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import renko.jiang.campus_trade.pojo.dto.CommentDTO;
import renko.jiang.campus_trade.pojo.result.Result;
import renko.jiang.campus_trade.pojo.vo.CommentVO;
import renko.jiang.campus_trade.service.CommentService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/{postId}")
    public Result<List<CommentVO>> getComments(@PathVariable Integer postId,Integer userId) {
        List<CommentVO> comments = commentService.getCommentsByPostId(postId,userId);
        return Result.success(comments);
    }

    @PostMapping("/add")
    public Result addComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);
        return Result.success();
    }
}
