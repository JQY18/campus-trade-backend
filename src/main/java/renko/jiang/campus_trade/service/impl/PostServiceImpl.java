package renko.jiang.campus_trade.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.mapper.PostMapper;
import renko.jiang.campus_trade.pojo.dto.PostDTO;
import renko.jiang.campus_trade.pojo.dto.PostSearchDTO;
import renko.jiang.campus_trade.pojo.entity.Post;

import renko.jiang.campus_trade.pojo.vo.PostVO;
import renko.jiang.campus_trade.service.PostService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    // 文件上传路径
    @Value("${upload.path}")
    private String path;

    /**
     * 根据id获取帖子
     * @param id
     * @return
     */
    @Override
    public PostVO getPostById(Integer id) {
        PostVO postVO = postMapper.getById(id);
        return postVO;
    }

    /**
     * 获取所有帖子
     * @return
     */
    @Override
    public List<PostVO> getAllPosts() {
        List<PostVO> postVOS = postMapper.getAllPosts();
        System.out.println(postVOS.get(0));
        return postVOS;
    }

    /**
     * 添加帖子图片
     * @param postId
     * @param images
     */
    @Override
    public void addImages(Integer postId, List<String> images) {
        postMapper.addImages(postId,images);
    }

    /**
     * 添加帖子
     * @param postDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addPost(PostDTO postDTO) {
        // 转换图片为静态资源映射地址
        List<String> images = handleMultipleFileUpload(postDTO.getImages());

        Post post = new Post();
        BeanUtils.copyProperties(postDTO,post);
        // 存储帖子信息，并生成主键
        postMapper.addPost(post);

        // 存储图片
        postMapper.addImages(post.getId(),images);
    }

    /**
     * 搜索帖子
     * @param postSearchDTO
     * @return
     */
    @Override
    public List<PostVO> searchPost(PostSearchDTO postSearchDTO) {
        List<PostVO> postVOS = postMapper.searchPost(postSearchDTO);
        return postVOS;
    }


    /**
     * 处理多文件上传
     * @param files
     * @return
     */
    public List<String> handleMultipleFileUpload(List<MultipartFile> files) {
        // 存储上传的图片的路径
        List<String> images = new ArrayList<>();
        if (files == null || files.isEmpty()) {
            throw new RuntimeException("No files uploaded");
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                try {
                    // 生成UUID
                    String uuid = UUID.randomUUID().toString();
                    // 文件扩展名
                    String originalFilename = file.getOriginalFilename();
                    String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
                    // 构建新的文件名
                    String newFileName = uuid + fileExtension;

                    // 构建文件路径
                    Path path = Paths.get(this.path, newFileName);
                    // 将MultipartFile写入文件系统
                    Files.write(path, file.getBytes());

                    // 收集该帖子id对应的图片的静态资源映射地址
                    images.add("http://localhost:8080/image/"+newFileName);

                    System.out.println("File saved successfully at: " + path.toString());
                } catch (IOException e) {
                    throw new RuntimeException("Failed to save file", e);
                }
            }
        }
        // 将生成的静态资源映射地址返回
        return images;
    }
}
