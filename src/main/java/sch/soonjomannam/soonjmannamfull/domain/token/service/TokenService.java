package sch.soonjomannam.soonjmannamfull.domain.token.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sch.soonjomannam.soonjmannamfull.common.CustomException;
import sch.soonjomannam.soonjmannamfull.common.TokenErrorCode;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;
import sch.soonjomannam.soonjmannamfull.db.member.repository.MemberRepository;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final Key signingKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);  // 강력한 키 생성
    private final long accessTokenValidity = 1000 * 60 * 15;  // 15분
    private final long refreshTokenValidity = 1000 * 60 * 60 * 24 * 7;  // 1주일
    private final MemberRepository memberRepository;

    public String createAccessToken(String username) {
        return createToken(username, accessTokenValidity);
    }

    public String createRefreshToken(String username) {
        return createToken(username, refreshTokenValidity);
    }

    private String createToken(String username, long validity) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + validity);
        MemberEntity memberEntity = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(TokenErrorCode.TOKEN_NOT_VALID, "Member not found"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", memberEntity.getId());

        return Jwts.builder()
                .setSubject(username)
                .addClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }


    public String getUsernameFromToken(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(signingKey) // 토큰 생성 시 사용한 키와 동일한 키 사용
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (ExpiredJwtException e) {
            // 만료된 토큰 예외 처리
            throw new CustomException(TokenErrorCode.TOKEN_EXPIRED, "Token has expired");
        } catch (JwtException e) {
            // 그 외의 토큰 오류 처리
            throw new CustomException(TokenErrorCode.TOKEN_NOT_VALID, "Invalid token");
        }
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
