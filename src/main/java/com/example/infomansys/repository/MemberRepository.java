package com.example.infomansys.repository;

import com.example.infomansys.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findByCivilStatus(String civilStatus);
    List<Member> findBySex(String sex);
    List<Member> findByDirectContributorIsNotNull();
    boolean existsByPinId(String pinId);
}
