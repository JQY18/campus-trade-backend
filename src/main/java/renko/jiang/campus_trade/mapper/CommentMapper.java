package renko.jiang.campus_trade.mapper;

import org.apache.ibatis.annotations.*;
import renko.jiang.campus_trade.pojo.dto.CommentDTO;
import renko.jiang.campus_trade.pojo.vo.CommentVO;

import java.util.List;

@Mapper
public interface CommentMapper {
    // 添加评论
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into comment set post_id = #{postId},commenter_id = #{commenterId},content = #{content}")
    void addComment(CommentDTO commentDTO);
    // 删除评论
    @Insert("delete from comment where id = #{id}")
    void deleteComment(Integer id);
    // 查询帖子下的所有评论
    List<CommentVO> getCommentsByPostId(Integer postId,Integer userId);

    @Select("select c.*,r.nickname commenter_nickname,r.avatar commenter_avatar from comment c inner join user r on c.commenter_id = r.id where c.id = #{id}")
    CommentVO getCommentVOById(Integer id);

    @Insert("insert into likes set comment_id = #{commentId},liker_id = #{likerId}")
    void likeComment(Integer commentId, Integer likerId);

    // 更新评论的点赞数
    @Update("update comment set `like` = #{to} where id = #{commentId}")
    void addLikeCount(Integer commentId,Integer to);

    // 取消点赞
    @Delete("delete from likes where comment_id = #{commentId} and liker_id = #{likerId}")
    void deleteCommentLike(Integer commentId, Integer likerId);

}
