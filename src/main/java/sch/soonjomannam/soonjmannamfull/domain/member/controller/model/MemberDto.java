package sch.soonjomannam.soonjmannamfull.domain.member.controller.model;

import jakarta.persistence.Column;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartFile;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;


import java.io.File;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class MemberDto {
    private long id;
    private String username;
    private String email;
    private MultipartFile profileImg;
    private String description;
    private String nickname;
    private String password;
    private String major;
    private String grade;
    private String mbti;
    private String alcoholPower;
    private String homeLocation;
    private String hobby;
    private LocalDateTime createdAt ;
    private LocalDateTime updatedAt ;
    private String phoneNumber;


    public MemberDto(long id,String username, String email, String description, String nickname, String password, String major, String grade, String mbti, String alcoholPower, String homeLocation, String hobby, LocalDateTime createdAt, LocalDateTime updatedAt, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.email = email;
//        this.profileImg = profileImg;
        this.description = description;
        this.nickname = nickname;
        this.password = password;
        this.major = major;
        this.grade = grade;
        this.mbti = mbti;
        this.alcoholPower = alcoholPower;
        this.homeLocation = homeLocation;
        this.hobby = hobby;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.phoneNumber = phoneNumber;
    }

    public MemberEntity toEntity(){
       return MemberEntity.builder()
               .id(id)
               .email(email)
//               .profileImg(profileImg)
               .description(description)
               .nickname(nickname)
               .password(password)
               .major(major)
               .grade(grade)
               .mbti(mbti)
               .alcoholPower(alcoholPower)
               .homeLocation(homeLocation)
               .hobby(hobby)
               .createdAt(createdAt)
               .updatedAt(updatedAt)
               .phoneNumber(phoneNumber)
               .build();
   }

}
