package renko.jiang.campus_trade.controller.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import renko.jiang.campus_trade.pojo.result.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class FileUploadController {
    @Value("${upload.path}")
    private String path;
    @PostMapping("/multiple")
    public Result handleMultipleFileUpload(@RequestParam("files") List<MultipartFile> files) {
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

                    System.out.println("File saved successfully at: " + path.toString());
                } catch (IOException e) {
                    return Result.error("File upload failed");
                }
            }
        }
        return Result.success();
    }
}