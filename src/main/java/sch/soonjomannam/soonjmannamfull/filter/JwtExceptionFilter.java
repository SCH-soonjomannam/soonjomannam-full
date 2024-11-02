package sch.soonjomannam.soonjmannamfull.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import sch.soonjomannam.soonjmannamfull.common.CustomException;
import sch.soonjomannam.soonjmannamfull.common.TokenErrorCode;

import java.io.IOException;

public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response); // 올바른 호출
        } catch (CustomException e) {
            // ErrorCustom에서 코드 가져오기
            if (e.getErrorCustom() != null && e.getErrorCustom().getCode() == TokenErrorCode.TOKEN_NOT_VALID.getCode()) {
                response.setStatus(400);
                response.getWriter().write("Token Not Valid");
            } else if (e.getErrorCustom() != null && e.getErrorCustom().getCode() == TokenErrorCode.TOKEN_EXPIRED.getCode()) {
                response.setStatus(401);
                response.getWriter().write("Token Expired");
            } else {
                response.setStatus(500);
                response.getWriter().write("An unknown error occurred");
            }
            response.getWriter().flush();
            response.getWriter().close();
        }
    }
}
