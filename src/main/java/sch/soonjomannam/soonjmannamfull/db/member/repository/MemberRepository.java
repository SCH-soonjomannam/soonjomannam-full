package sch.soonjomannam.soonjmannamfull.db.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sch.soonjomannam.soonjmannamfull.db.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUsername(String username);
    Optional<MemberEntity> findByEmail(String email);

}
