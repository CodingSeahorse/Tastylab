package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {
    Member getMemberByEmailAndFirstName(String email, String firstName);

    Member getMemberByEmail(String email);

    Member getMemberByMembercardUsername(String username);

    Member getMemberByMembercardUsernameAndMembercardPassword(String username,String password);

    boolean existsMemberByEmail(String email);
}
