package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member getMemberByEmailAndFirstName(String email, String firstName);

    Member getMemberByMembercardUsername(String username);

    Member getMemberByMembercardUsernameAndMembercardPassword(String username,String password);

    boolean existsMemberByEmail(String email);
}
