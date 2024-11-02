package sch.soonjomannam.soonjmannamfull.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import sch.soonjomannam.soonjmannamfull.common.CustomException;
import sch.soonjomannam.soonjmannamfull.common.TokenErrorCode;
import sch.soonjomannam.soonjmannamfull.domain.token.service.TokenService;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        // "Authorization" 헤더가 없거나 올바르지 않은 형식이면 요청을 그대로 통과시킴
        if (token == null || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // "Bearer " 접두사를 제거
        token = token.substring(7);

        try {
            // 토큰 검증 및 사용자 이름 확인
            boolean isValid = tokenService.validateToken(token);
            if (!isValid) {
                throw new CustomException(TokenErrorCode.TOKEN_NOT_VALID, "Token is not valid");
            }

            // 유효한 경우 사용자 정보 확인 (로그에 출력하거나, 이후 인증 객체 설정 가능)
            String username = tokenService.getUsernameFromToken(token);
            // 로깅이나 인증 설정 관련 추가 로직을 여기에 추가할 수 있습니다.
            System.out.println("Authenticated user: " + username);

            // 필터 체인 계속 진행
            filterChain.doFilter(request, response);
        } catch (CustomException e) {
            // 예외는 JwtExceptionFilter에서 처리하도록 던짐
            throw e;
        } catch (Exception e) {
            // 기타 예외 발생 시 기본적인 처리
            throw new CustomException(TokenErrorCode.TOKEN_NOT_VALID, "Unexpected error during token validation");
        }
    }
}
