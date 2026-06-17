package com.example.infomansys.repository;

import com.example.infomansys.entity.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DependentRepository extends JpaRepository<Dependent, Integer> {
    List<Dependent> findByMember_PinId(String pinId);
    List<Dependent> findByRelationshipToMember(String relationship);
    void deleteByMember_PinId(String pinId);
}
