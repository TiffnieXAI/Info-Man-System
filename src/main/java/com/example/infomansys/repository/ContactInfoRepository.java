package com.example.infomansys.repository;

import com.example.infomansys.entity.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactInfoRepository extends JpaRepository<ContactInfo, String> {
    Optional<ContactInfo> findByMember_PinId(String pinId);
}
