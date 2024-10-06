package sch.soonjomannam.soonjmannamfull.db.article.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;
    private String title;
    private String author;

    @Column(length = 1000)
    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

