package sch.soonjomannam.soonjmannamfull.domain.review.controller.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sch.soonjomannam.soonjmannamfull.db.review.entity.ReviewEntity;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private long reviewId;
    private String author;
    private String title;
    private String content;

    public ReviewEntity toEntity() {
        return new ReviewEntity().builder()
                .reviewId(reviewId)
                .author(author)
                .title(title)
                .content(content)
                .build();
    }
}
