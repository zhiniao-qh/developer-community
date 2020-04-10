package cn.treeshell.article.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.treeshell.common.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JWT 拦截器
 * @Author: panjing
 * @Date: 2020/4/9 22:35
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 过滤掉请求头中不包含 token 的请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String header = request.getHeader("Authorization");

        if (StrUtil.isNotEmpty(header)) {
            if (header.startsWith("Bearer ")) {
                // 获取到 token
                String token = header.substring(7);
                // 对 token 进行验证
                try {
                    Claims claims = jwtUtil.parseJWT(token);
                    String roles = (String) claims.get("roles");
                    // 如果是管理员
                    if (StrUtil.equals("admin", roles)) {
                        // 将 token 信息放入 request 中
                        request.setAttribute("claims_admin", token);
                    }
                    // 如果是普通用户
                    if (StrUtil.equals("user", roles)) {
                        request.setAttribute("claims_user", token);
                    }
                } catch (Exception e) {
                    throw new RuntimeException("令牌不正确");
                }
            }
        }

        return true;
    }
}
