package renko.jiang.campus_trade.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import renko.jiang.campus_trade.pojo.dto.ReplyDTO;
import renko.jiang.campus_trade.pojo.result.Result;
import renko.jiang.campus_trade.service.ReplyService;

@CrossOrigin("*")
@RestController
@RequestMapping("/reply")
public class ReplyController {
    @Autowired
    private ReplyService replyService;
    @PostMapping("/add")
    public Result addReply(@RequestBody ReplyDTO replyDTO) {
        replyService.addReply(replyDTO);
        return Result.success();
    }
}
