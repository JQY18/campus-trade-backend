package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import renko.jiang.campus_trade.mapper.ReplyMapper;
import renko.jiang.campus_trade.pojo.dto.ReplyDTO;
import renko.jiang.campus_trade.service.ReplyService;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private ReplyMapper replyMapper;
    @Override
    public void addReply(ReplyDTO replyDTO) {
        replyMapper.addReply(replyDTO);
    }
}
