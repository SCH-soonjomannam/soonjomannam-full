package sch.soonjomannam.soonjmannamfull.domain.member.service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sch.soonjomannam.soonjmannamfull.common.CustomException;
import sch.soonjomannam.soonjmannamfull.common.TokenErrorCode;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;
import sch.soonjomannam.soonjmannamfull.db.member.repository.MemberRepository;
import sch.soonjomannam.soonjmannamfull.domain.member.controller.model.MemberDto;
import sch.soonjomannam.soonjmannamfull.domain.token.Token;
import sch.soonjomannam.soonjmannamfull.domain.token.service.TokenService;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.Random;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepository memberRepository;




    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final RedisTemplate<String, Token> redisTemplate;
    private final TokenService tokenService;


    public MemberEntity findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Member not found with username: " + username));
    }

    @Transactional
    public Long signUp(MemberDto memberDto) {
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        MemberEntity member = memberDto.toEntity();
        return memberRepository.save(member).getId();
    }


    @Transactional
    public String login(String username, String password) {
        // 1. 사용자 찾기
        MemberEntity member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(TokenErrorCode.TOKEN_NOT_FOUND, "Member not found with username: " + username));

        // 2. 비밀번호 검증
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new CustomException(TokenErrorCode.TOKEN_NOT_VALID, "Invalid password");
        }

        // 3. 토큰 생성
        String accessToken = tokenService.createAccessToken(username);
        String refreshToken = tokenService.createRefreshToken(username);

        // 4. Redis에 Refresh Token 저장
        redisTemplate.opsForHash().put("member_refresh_token", String.valueOf(member.getId()), refreshToken);

        // 5. Access Token 반환
        return accessToken;
    }





}
