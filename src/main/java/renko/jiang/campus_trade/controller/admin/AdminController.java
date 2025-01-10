package renko.jiang.campus_trade.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import renko.jiang.campus_trade.pojo.entity.User;
import renko.jiang.campus_trade.pojo.result.Result;
import renko.jiang.campus_trade.service.AdminService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private HttpSession session;

    //todo: 登录验证(bug)
    Integer adminId;

    /**
     * 登录
     */
    @GetMapping("/login")
    public Result login(String username, String password,HttpServletRequest request) {
        Integer id = adminService.login(username, password);
        adminId = id;
        session.setAttribute("adminId", id);
        System.out.println(session);

        return Result.success();
    }

    /**
     * 验证
     */
    @GetMapping("authentic")
    public Result<Integer> getAuthentic(HttpServletRequest request) {
        //Integer adminId = (Integer) session.getAttribute("adminId");

        System.out.println("adminId: " + adminId);
        if (adminId == null) {
            return Result.error("未登录");
        }
        return Result.success(adminId);
    }

    @GetMapping("/list")
    public Result<List<User>> getList() {
        return Result.success(adminService.getList());
    }
    @GetMapping("/delete")
    public Result<String> delete(Integer id) {
        adminService.delete(id);
        System.out.println(id);
        return Result.success();
    }

}
