package sch.soonjomannam.soonjmannamfull.db.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<Long, MemberEntity> {
}
