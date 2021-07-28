package com.codingseahorse.tastylab.repository;

import com.codingseahorse.tastylab.model.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member,Integer> {

    Member getMemberByEmailAndFirstName(String email, String firstName);

    boolean existsMemberByEmail(String email); // Check if the email is already taken.*/

}
