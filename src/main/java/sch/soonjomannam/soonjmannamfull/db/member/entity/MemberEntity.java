package sch.soonjomannam.soonjmannamfull.db.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
//accountId, password, email, profileImg, description,
// major, grade, mbti, alcoholPower, homeLocation,
// hobby, createdAt, updatedAt, phoneNumber

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private  String username;
    private String email;
//    private File profileImg;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false, unique = true)
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

    public MemberEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
