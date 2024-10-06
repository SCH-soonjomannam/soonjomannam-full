package sch.soonjomannam.soonjmannamfull.domain.member.controller.model;

import jakarta.persistence.Column;
import lombok.*;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;

import java.io.File;
import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class MemberDto {
    private long id;
    private String email;
    private File profileImg;
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

   public MemberEntity toEntity(){
       return MemberEntity.builder()
               .id(id)
               .email(email)
               .profileImg(profileImg)
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
