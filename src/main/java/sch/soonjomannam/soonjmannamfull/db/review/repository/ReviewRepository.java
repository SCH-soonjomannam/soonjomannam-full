package sch.soonjomannam.soonjmannamfull.db.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.soonjomannam.soonjmannamfull.db.review.entity.ReviewEntity;

public interface ReviewRepository extends JpaRepository<Long, ReviewEntity> {
}
