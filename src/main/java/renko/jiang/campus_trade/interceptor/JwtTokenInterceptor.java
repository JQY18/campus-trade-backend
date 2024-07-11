package renko.jiang.campus_trade.interceptor;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import renko.jiang.campus_trade.properties.JwtProperties;
import renko.jiang.campus_trade.utils.JwtUtil;

@Component
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * 校验jwt
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }

        //1、从请求头中获取令牌
        String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            // 验证和使用token...
        }

        // System.out.println("token: " + token);
        //2、校验令牌
        try {
            //解析jwt令牌
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            //获取数据
            Integer id = claims.get("userId", Integer.class);
            //将userId放入session
            request.getSession().setAttribute("userId", id);
            //3、通过，放行
            return true;
        } catch (Exception ex) {
            //4、不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
