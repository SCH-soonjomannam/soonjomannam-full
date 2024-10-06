package sch.soonjomannam.soonjmannamfull.db.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.soonjomannam.soonjmannamfull.db.article.entity.ArticleEntity;

public interface ArticleRepository extends JpaRepository<Long, ArticleEntity> {
}
