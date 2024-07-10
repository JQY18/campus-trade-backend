package renko.jiang.campus_trade.service;

import renko.jiang.campus_trade.pojo.dto.PostDTO;
import renko.jiang.campus_trade.pojo.dto.PostSearchDTO;
import renko.jiang.campus_trade.pojo.vo.PostVO;

import java.util.List;

public interface PostService {
    PostVO getPostById(Integer id);

    List<PostVO> getAllPosts();

    void addImages(Integer postId, List<String> url);

    void addPost(PostDTO postDTO);

    List<PostVO> searchPost(PostSearchDTO postSearchDTO);
}
