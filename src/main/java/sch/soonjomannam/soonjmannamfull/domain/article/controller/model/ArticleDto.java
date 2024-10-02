package sch.soonjomannam.soonjmannamfull.domain.article.controller.model;

import lombok.Getter;
import lombok.Setter;
import sch.soonjomannam.soonjmannamfull.db.article.entity.ArticleEntity;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDto {
    private long articleId;
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ArticleEntity toEntity(){
        return new ArticleEntity().builder()
                .articleId(articleId)
                .title(title)
                .author(author)
                .content(content)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }


}
