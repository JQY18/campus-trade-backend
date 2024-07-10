package renko.jiang.campus_trade.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import renko.jiang.campus_trade.pojo.dto.PostSearchDTO;
import renko.jiang.campus_trade.pojo.entity.Post;
import renko.jiang.campus_trade.pojo.vo.PostVO;

import java.util.List;

@Mapper
public interface PostMapper {
    PostVO getById(Integer id);

    List<PostVO> getAllPosts();

    @Select("select url from images where post_id = #{id}")
    List<String> getImagesByPostId(Integer id);

    void addImages(Integer postId, List<String> images);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into post set category = #{category}, title = #{title},content = #{content},user_id = #{userId}")
    void addPost(Post post);

    List<PostVO> searchPost(PostSearchDTO postSearchDTO);
}
