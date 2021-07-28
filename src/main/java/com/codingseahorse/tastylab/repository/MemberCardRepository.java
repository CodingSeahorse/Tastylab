package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.MemberCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCardRepository extends JpaRepository<MemberCard,Long> {
    MemberCard getMemberCardByUsername (String username);
}
